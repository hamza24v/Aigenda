import React, { createContext, useState, useEffect, useContext } from "react";
import apiService from "../apiService";

export const EventsContext = createContext();

export const EventsProvider = ({ children }) => {
  const [events, setEvents] = useState([]);
  const [eventErrors, setEventErrors] = useState([]);
  // retreve user jwt info

  useEffect(() => {
    fetchEvents();
  }, [user]);

  // CRUD
  const fetchEvents = async () => {
    await apiService
      .getAll("events")
      .then((data) => setEvents(data))
      .catch(console.log);
  };

  const createEvent = async (event) => {
    apiService
      .post("events", event)
      .then((data) => {
        if (!data.eventId) {
          setEventErrors(data);
        }
      })
      .catch(console.log);
    fetchEvents();
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
  };

  const deleteEvent = async (id) => {
    apiService
      .remove("events", eventId)
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
