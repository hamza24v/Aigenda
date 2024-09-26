import React, { useState } from "react";
import { Form } from "../components/Form";
import { REGISTRATION_FORM } from "../constants";
import Typography from "@mui/material/Typography";
import { useUser } from "../contexts/UserContext";
import { Link, useNavigate } from "react-router-dom";
function Register() {
  const { registerUser,user} = useUser(); // fetching register route from UserContext
  const navigate = useNavigate();

  const handleSubmit = async (formData) => {
    await registerUser(formData);
    console.log("String registered");
    console.log(user);
    navigate("/login")
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
          Create Your Account
        </Typography>

          <Form
            fields={REGISTRATION_FORM}
            onSubmit={handleSubmit}
            submitText="Register"
          />
        <Typography className="text-gray-500 text-sm mt-4 w-full text-center">
          Already have an account?{" "}
          <Link
            to="/login"
            className="text-indigo-600 hover:text-indigo-800 text-center"
          >
            Login here
          </Link>
        </Typography>
      </div>
    </div>
  );
}

export default Register;
