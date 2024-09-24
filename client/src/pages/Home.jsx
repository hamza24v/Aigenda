import React, { useState } from "react";
import { MyCalendar } from "../components/MyCalendar";
import { CalendarList } from '../components/CalendarList';
import { Button } from "@mui/material";
import { PopupModal } from '../components/PopupModal';
const CALENDERS = [
  {
    title: "John Smith",
  },
];

function Home() {
  const [showModal, setShowModal] = useState(false);

  return (
    <div className="flex flex-col sm:flex-row  min-h-screen">
      <div className="sm:w-1/4">
        <CalendarList title="My Calendars" calendars={CALENDERS} />
        <CalendarList title="Joined Calendars" calendars={CALENDERS} />
      </div>
      <div className="sm:w-3/4">
        <MyCalendar />
      </div>
      <div className="fixed right-10 bottom-10 z-50 ">
      <Button
      variant='contained'
        onClick={() => setShowModal(true)}
      >
        Add Event
        {showModal && <PopupModal open={showModal} onClose={() => setShowModal(false)} />}
      </Button>
      </div>
      
    </div>
  );
}

export default Home;
