import React, { createContext, useState, useContext } from "react";
import apiService from "../apiService";

export const UserContext = createContext();

// This is used to manage user session accross our app.
// We can fetch the jwt anywhere in our app without the need
// to  repeatedly passing down through components (prop drilling; bad react practice)
export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null); 
  const [jwtToken, setJwtToken] = useState(null);
  const [userErrors, setUserErrors] = useState([]);
  const { postLogin, remove } = apiService;

  const registerUser = async (user) => {
    user.roles = [];
    postLogin("user/register", user)
      .then((data) => {
        if (data.appUserId) {
          setUser(data);
          console.log("User registered:", data);
        } else {
          setUserErrors(data);
        }
      })
      .catch(console.log);
  };

  const loginUser = async (credentials) => {
    postLogin("user/authenticate", credentials)
      .then((data) => {
        if (data.jwt_token) {
          setUser({...user, ...data, credentials});
          localStorage.setItem("jwt_token", data.jwt_token) 
          setJwtToken(data.jwt_token); 
          console.log("User logged in:", data);
        } else {
          setUserErrors(data); 
        }
      })
      .catch(console.log);
  };

  const deleteUser = async (id) => {
    remove("user", id, user.roles)
      .then(() => {
        setUser((prevUsers) => prevUsers.filter((u) => u.appUserId !== id)); 
      })
      .catch(console.log);
  };

  return (
    <UserContext.Provider
      value={{ user, jwtToken, loginUser, registerUser, deleteUser, userErrors }}
    >
      {children}
    </UserContext.Provider>
  );
};

export const useUser = () => {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error("useUser must be used within an UserProvider");
  }
  return context;
};