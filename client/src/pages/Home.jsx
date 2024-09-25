import React, { useEffect } from "react";
import { MyCalendar } from "../components/MyCalendar";
import { CalendarList } from "../components/CalendarList";
import { AddEvent } from '../components/AddEvent';
import { useEvents } from "../contexts/EventsContext";
import { useCalendars } from '../contexts/CalendarsContext';
import { useUser } from '../contexts/UserContext';
import { useNavigate } from "react-router-dom";

function Home() {
  const { events } = useEvents();
  const { calendars } = useCalendars();
  const personalCalendars = calendars.filter(({ type }) => type.toLowerCase() === "personal");
  const orgCalendars = calendars.filter(({ type }) => type.toLowerCase() === "organization");
  const navigate = useNavigate();
  const { user } = useUser();
  useEffect(() => {
    if (!user) {
      navigate("/login");
     
    }
  },[user, calendars])
  //console.log(user);
  return (
    <div className="flex flex-col sm:flex-row  min-h-screen">
      <div className="sm:w-1/4">
        <CalendarList title="My Calendars" calendars={personalCalendars} />
        <CalendarList title="Joined Calendars" calendars={orgCalendars} />
      </div>
      <div className="sm:w-3/4">
        <MyCalendar events={events} />
      </div>
      <AddEvent />
    </div>
  );
}

export default Home;
