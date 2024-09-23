import React from "react";
import { MyCalendar } from "../components/MyCalendar";
import CalendarList from "../components/CalendarList";

const CALENDERS = [
    {
        title: "John Smith"
    }
]

function Home() {
  return (
    <div className="flex flex-row min-h-screen">
      <div className="w-1/4">
        <CalendarList title="My Calendars" calendars={CALENDERS} />
        <CalendarList title="Joined Calendars" calendars={CALENDERS} />
      </div>
      <div className="w-3/4">
        <MyCalendar />
      </div>
    </div>
  );
}

export default Home;
