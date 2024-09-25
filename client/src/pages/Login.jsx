import React from "react";
import { Form } from "../components/Form";
import { LOGIN_FORM } from "../constants";
import Typography from "@mui/material/Typography";
import { useUser } from "../contexts/UserContext";
import { Link, useNavigate } from "react-router-dom";

function Login() {

  const { loginUser, user } = useUser(); // fetching login route from UserContext
  const navigate = useNavigate();

  const handleSubmit = async (formData) => {
    await loginUser(formData);
    if(user){
     navigate("/home");
    }
  };

  return (
    <div className="flex justify-center min-h-screen  bg-gray-100">
      <div className="flex flex-col justify-center my-auto w-full max-w-md p-6 bg-white rounded-lg shadow-xl space-y-4">
        <Typography
          className="text-center text-2xl font-semibold"
          id="modal-modal-title"
          variant="h5"
          component="h2"
        >
          Login
        </Typography>

          <Form
            fields={LOGIN_FORM}
            onSubmit={handleSubmit}
            submitText="Login"
          />
        <Typography className="text-gray-500 text-sm mt-4 w-full text-center">
          Don't have an account?{" "}
          <Link
            to="/register"
            className="text-indigo-600 hover:text-indigo-800 text-center"
          >
            Register here
          </Link>
        </Typography>
      </div>
    </div>
  )
}

export default Login