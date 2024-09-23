import React from "react";

function CalendarList({ title, calendars }) {
  return (
    <div className="p-4">
      <div className="flex justify-between">
        <h2 className="font-bold text-lg">{title}</h2>
        <span className="text-lg">+</span>
      </div>

      {calendars.map((calendar, index) => (
        <div key={index} className="">
          <input
            type="checkbox"
            id="title"
            name="title"
            value={calendar.title}
            
          />
          <label for="title" className="pl-4">{calendar.title}</label>
          <br />
        </div>
      ))}
    </div>
  );
}

export default CalendarList;
