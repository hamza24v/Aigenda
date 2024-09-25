import React, { createContext, useState, useEffect, useContext } from "react";
import apiService from "../apiService";

export const CalendarsContext = createContext();

export const CalendarsProvider = ({ children }) => {
  const [calendars, setCalendars] = useState([]);
  const [calendarErrors, setCalendarErrors] = useState([]);

  // retreve user jwt info

  useEffect(() => {
    fetchCalendars();
  }, []);

  // CRUD
  const fetchCalendars = async () => {
    await apiService
      .getAll("calendars")
      .then((data) => setCalendars(data))
      .catch(console.log);
  };

  const createCalendar = async (calendar) => {
    setCalendars([...calendars, calendar]);
    // apiService
    //   .post("calendars", calendar)
    //   .then((data) => {
    //     if (!data.calendarsId) {
    //       setCalendarErrors(data);
    //     }
    //   })
    //   .catch(console.log);

    // // temporary add calendar
    // fetchCalendars();

    console.log("calender added: ", calendar);
  };

  const updateCalendar = (calendar, id) => {
    calendar.calendarId = id;
    apiService.update("calendars", id, calendar).then((data) => {
      if (data) {
        setCalendarErrors(data);
      }
    })
    .catch(console.log);
  };

  const deleteCalendar = async (id) => {
    apiService
      .remove("calendars", id)
      .then(() => {
        setCalendars((prevcalendars) =>
          prevcalendars.filter((e) => e.calendarsId !== id)
        );
      })
      .then((data) => {
        if (data) {
          setCalendarErrors(data);
        }
      })
      .catch(console.log);
    fetchCalendars();
  };

  return (
    <CalendarsContext.Provider
      value={{
        calendars,
        createCalendar,
        updateCalendar,
        deleteCalendar,
        calendarErrors,
      }}
    >
      {children}
    </CalendarsContext.Provider>
  );
};

export const useCalendars = () => {
  const context = useContext(CalendarsContext);
  if (!context) {
    throw new Error("usecalendars must be used within an CalendarsProvider");
  }
  return context;
};
