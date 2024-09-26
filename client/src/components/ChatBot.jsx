import React, { useState } from "react";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import { sendMessage } from "../services/chatService";
import { Form } from "./Form";
import { PopupModal } from "./PopupModal";
import { useCalendars } from "../contexts/CalendarsContext";
import { CALENDAR_FORM, EVENT_FORM } from "../constants";
import { Button } from "@mui/material";
import { useEvents } from "../contexts/EventsContext";

export const ChatBot = () => {
  const [message, setMessage] = useState("");
  const [botResponse, setBotResponse] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const { createEvent } = useEvents();
  const { createCalendar, calendars } = useCalendars();
  const [eventFormFields, setEventFormFields] = useState(EVENT_FORM);


  let formType;
  let fullBotResponse;

  // useEffect(() => {
  //   // Update the event form with calendar options once fetched
  //   const calendarOptions = calendars.map(({title, calendarId}) => {
  //     return {value: calendarId, label: title}
  //   })
  //   const updatedEventForm = eventFormFields.map((field) =>
  //     field.name === 'calendarId' ? { ...field, options: calendarOptions } : field
  //   );
  //   setEventFormFields(updatedEventForm);
  // }, [calendars]);

  if (botResponse) {
    if (Object.keys(botResponse).length === 2) {
      console.log("bot called cal "); 
      console.log(botResponse);
      formType = CALENDAR_FORM;
      // const secondTypeOption = botResponse.type.includes("personal")
      //   ? { value: "ORGANIZATION", label: "Organization" }
      //   : { value: "PERSONAL", label: "Personal" };
      // formType[formType.length - 1] = {
      //   label: "Type",
      //   name: "type",
      //   type: "select",
      //   options: [
      //     {
      //       value: botResponse.type,
      //       label:
      //         botResponse.type.charAt(0).toUpperCase() +
      //         botResponse.type.slice(1),
      //     },
      //     secondTypeOption,
      //   ],
      //   placeholder: "Select Type",
      //   required: true,
      // };
      
    } else {
      console.log("bot called"); 
      console.log(botResponse);
      formType = EVENT_FORM;
      const calendarOptions = calendars.map(({title, calendarId}) => {
        return {value: calendarId, label: title}
      })
      formType[formType.length - 1] = {
        label: 'Calendars',
        name: 'calendarId',
        type: 'select',
        options: calendarOptions,
        placeholder: 'Select Calendars',
        required: true
    }

      fullBotResponse = {...botResponse, options : calendarOptions}
      console.log("full bot response")
      console.log(fullBotResponse);
    }
  }


  const handleSubmit = async (e) => {
    e.preventDefault();
    if(message.trim()){
      const response = await sendMessage(message);
      const { event, calendar } = response;
      setBotResponse(event ? event : calendar);
      setShowModal(true);
    } else {
      alert("Please enter a valid message");
    }
   
  };

  const handleFormSubmit = async (formData) => {
    if (Object.keys(botResponse).length === 2) {
      await createCalendar(formData);
    }
    else{
    await createEvent(formData);}
    setShowModal(false);
    console.log("event added");
    console.log(formData);
  };

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
