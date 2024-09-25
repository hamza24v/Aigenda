import React, { createContext, useState, useContext } from "react";
import apiService from "../apiService";

export const UserContext = createContext();

// This is used to manage user session accross our app.
// We can fetch the jwt anywhere in our app without the need
// to  repeatedly passing down through components (prop drilling; bad react practice)
export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [userErrors, setUserErrors] = useState([]);
  const { post, remove } = apiService;

  const registerUser = async (user) => {
    user.roles = [];
    post("user/register", user)
      .then(data => {
        console.log(data);
        if (data.appUserId) {
          setUser(data);
          console.log(data);
        } else {
          setUserErrors(data);
        }
      })
      .catch(console.log);
  };

  const loginUser = async (user) => {
    post("user/authenticate", user)
      .then((data) => {
        if (data.jwt_token) {
            setUser({user, ...data});
            console.log("data:");
            console.log(data);
          } else {
            setUserErrors(data);
          }
      })
      .catch(console.log);
  };

  const deleteUser = async (id) => {
    remove("user", eventId, user.roles) // only 'owner' can delete users
      .then(() => {
        setUser((prevEvents) => prevEvents.filter((a) => a.appUserId !== id));
      })
      .catch(console.log);
  };

  return (
    <UserContext.Provider
      value={{ user, loginUser, registerUser, deleteUser, userErrors }}
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
