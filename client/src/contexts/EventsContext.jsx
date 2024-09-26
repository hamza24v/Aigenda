import React, { createContext, useState, useEffect, useContext } from "react";
import apiService from "../apiService";
import { useUser } from "./UserContext";
export const EventsContext = createContext();

export const EventsProvider = ({ children }) => {
  const [events, setEvents] = useState([]);
  const [eventErrors, setEventErrors] = useState([]);
  const {user} = useUser();
  const [selectedEvent, setSelectedEvent] = useState(null)
  // retreve user jwt info

  useEffect(() => {
    if (user) {
      fetchEvents();
    }
    
  }, [user]);

  // CRUD
  const fetchEvents = async () => {
    await apiService
      .getAll("events/user", localStorage.getItem("jwt_token"), user.appUserId)
      .then((data) => {
        setEvents(data)
        console.log(data)
      })
      .catch(console.log);
  };

  const createEvent = async (event) => {
    event["status"] = "Pending"
    event["calendarId"] = parseInt(event["calendarId"]);
    event["appUserId"] = user.appUserId;
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
    event["eventId"] = id;
    event["status"] = "Pending";
    event["calendarId"] = parseInt(event["calendarId"]);
    event["appUserId"] = user.appUserId;
    apiService.update(`events/update/${id}`, event)
      .then((data) => {
        if (data) {
          setEventErrors(data);
        }
      })
      .catch(console.log);
      fetchEvents();
  };

  const deleteEvent = async (id) => {
    console.log("id:")
    console.log(id)
    apiService
    .remove( `events/delete/${id}`)
      .then(() => {
        setEvents((prevEvents) => prevEvents.filter((e) => e.eventId !== id));
      })
      .then((data) => {
        if (data) {
          setEventErrors(data);
        }
      })
      .catch(console.log);
    fetchEvents();
  };

  return (
    <EventsContext.Provider
      value={{ setSelectedEvent,selectedEvent,events, createEvent, updateEvent, deleteEvent, eventErrors }}
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
