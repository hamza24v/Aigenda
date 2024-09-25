import React from "react";
import { AddCalendar } from '../components/AddCalendar';

export const CalendarList = ({ title, calendars }) => {

  return (
    <div className="p-4">
      <div className="flex justify-between">
        <h2 className="font-bold text-lg">{title}</h2>
        <AddCalendar/>
      </div>

      {calendars?.map((calendar, index) => (
        <div key={index} className="">
          <input
            type="checkbox"
            id={`calendar-${index}`}
            name="title"
            value={calendar.title}
          />
          <label htmlFor={`calendar-${index}`} className="pl-4">
            {calendar.title}
          </label>
          <br />
        </div>
      ))}

     
    </div>
  );
};
