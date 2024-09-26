import React, { createContext, useState, useEffect, useContext } from "react";
import apiService from "../apiService";
import { useUser } from "./UserContext";
export const EventsContext = createContext();

export const EventsProvider = ({ children }) => {
  const [events, setEvents] = useState([]);
  const [eventErrors, setEventErrors] = useState([]);
  const {user} = useUser();
  // retreve user jwt info

  useEffect(() => {
    fetchEvents();
  }, []);

  // CRUD
  const fetchEvents = async () => {
    await apiService
      .getAll("events")
      .then((data) => setEvents(data))
      .catch(console.log);
  };

  const createEvent = async (event) => {
    event["status"] = "Pending"
    event["calendarId"] = parseInt(event["calendarId"]);
    event["appUserId"] = user.appUserId;
    console.log(event)
    console.log(user.jwt_token)
    apiService
      .post("events/create", event, user.jwt_token)
      .then((data) => {
        if (!data.eventId) {
          setEventErrors(data);
        } else {
          setEvents((prevEvents) => (
            [...prevEvents, data]
          ))
        }
      })
      .catch(console.log);
    console.log("event added", event)
  };

  const updateEvent = (event, id) => {
    event.eventId = id;
    apiService
      .update("events", id, event)
      .then((data) => {
        if (data) {
          setEventErrors(data);
        }
      })
      .catch(console.log);
      fetchEvents();
  };

  const deleteEvent = async (id) => {
    apiService
    .remove( `events/${eventId}`)
      .then(() => {
        setEvents((prevEvents) => prevEvents.filter((e) => e.eventId !== id));
      })
      .catch(console.log);
    fetchEvents();
  };

  return (
    <EventsContext.Provider
      value={{ events, createEvent, updateEvent, deleteEvent, eventErrors }}
    >
      {children}
    </EventsContext.Provider>
  );
};

export const useEvents = () => {
  const context = useContext(EventsContext);
  if (!context) {
    throw new Error("useEvents must be used within an EventsProvider");
  }
  return context;
};
