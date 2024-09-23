import { useState } from 'react'
import { Calendar, dateFnsLocalizer } from "react-big-calendar";
import format from "date-fns/format";
import parse from "date-fns/parse";
import startOfWeek from "date-fns/startOfWeek";
import getDay from "date-fns/getDay";
import addHours from 'date-fns/addHours';
import enUS from "date-fns/locale/en-US";
import 'react-big-calendar/lib/css/react-big-calendar.css';

const locales = {
  "en-US": enUS,
};

const localizer = dateFnsLocalizer({
  format,
  parse,
  startOfWeek,
  getDay,
  locales,
});

const events = [
  {
    title: 'Meeting',
    start: new Date(),
    end: addHours(new Date(), 2),
  },
  {
    title: 'Conference',
    start: addHours(new Date(), 3),
    end: addHours(new Date(), 5),
  },
];

export const MyCalendar = () => {
  const [myEvents, setMyEvents] = useState(events);

  const handleSelectSlot = ({ start, end }) => {
    const title = window.prompt("New Event name");
    if (title) {
      setMyEvents([
        ...myEvents,
        {
          start,
          end,
          title,
        },
      ]);
    }
  };

  return (
    <div className="h-screen p-4">
      <Calendar
        localizer={localizer}
        events={myEvents}
        startAccessor="start"
        endAccessor="end"
        style={{ height: "100%" }}
        onSelectSlot={handleSelectSlot}
        selectable
      />
    </div>
  );
};
