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

export const MyCalendar = ({ events }) => {

  const eventData = events

  const convertToDate = (dateString) => new Date(dateString);

  const transformedEvents = eventData.map(event => ({
    title: event.title || event.description,  // Use the title, or fall back to the description
    start: convertToDate(event.startDate),    // Convert the start date string to a Date object
    end: convertToDate(event.endDate)         // Convert the end date string to a Date object
  }));

  console.log(transformedEvents)

  return (
    <div className="w-full h-full">
      <Calendar
        localizer={localizer}
        events={transformedEvents}
        startAccessor="start"
        endAccessor="end"
        selectable
        style={{ height: "100vh" }}
      />
    </div>
  );
};
