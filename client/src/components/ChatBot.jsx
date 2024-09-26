import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import { sendMessage } from "../services/chatService";
import { Form } from "./Form";
import { PopupModal } from "./PopupModal";
import { CALENDAR_FORM, EVENT_FORM } from "../constants";
import { Button } from "@mui/material";
export const ChatBot = () => {
  const [message, setMessage] = useState("");
  const [botResponse, setBotResponse] = useState(null);
  const [showModal, setShowModal] = useState(false);
  let formType;
  if (botResponse) {
    if (botResponse?.calendar) {
      formType = CALENDAR_FORM;
    } else {
      formType = EVENT_FORM;
    }
  }
  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await sendMessage(message);
    const { event, calendar } = response;
    setBotResponse(event ? event : calendar);
    setShowModal(true);
  };
  const handleFormSubmit = (formData) => {};
  return (
    <Box
      component="form"
      sx={{ "& .MuiTextField-root": { m: 3, width: "90%", } }}
      noValidate
      autoComplete="off"
    >
      <div className="flex flex-col justify-center items-center">
        <TextField
          id="outlined-multiline-static"
          multiline
          rows={4}
          placeholder="How can I help you?"
          variant="outlined"
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          className="bg-gray-100 shadow-md"
        />
        <Button type="submit" variant="contained" onClick={handleSubmit}>
          Send
        </Button>
      </div>
      {botResponse && (
        <PopupModal
          onClose={() => setShowModal(false)}
          open={showModal}
          title="Review"
        >
          <Form
            fields={formType}
            defaultValues={botResponse}
            onSubmit={handleFormSubmit}
            submitText="Save"
          />
        </PopupModal>
      )}
    </Box>
  );
};