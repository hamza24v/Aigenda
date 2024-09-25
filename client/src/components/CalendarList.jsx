import React, { useState } from "react";
import { CALENDER_FORM } from "../constants";
import { PopupModal } from "../components/PopupModal";
import { Form } from "../components/Form";
import { CALENDERS } from "../constants";

export const CalendarList = ({ title }) => {
  const [calendarForm, setCalendarForm] = useState({});
  const [showModal, setShowModal] = useState(false);
  const [calendarList, setCalendarList] = useState(CALENDERS);

  const handleSubmit = (formData) => {
    setCalendarForm(formData);
    console.log("calendar: ")
    console.log(formData)
    setCalendarList([...calendarList, formData])
    setShowModal(false);
  };

  return (
    <div className="p-4">
      <div className="flex justify-between">
        <h2 className="font-bold text-lg">{title}</h2>
        <span
          className="text-lg cursor-pointer px-2 hover:rounded-full hover:bg-gray-50"
          onClick={() => setShowModal(true)}
        >
          +
        </span>
      </div>

      {calendarList.map((calendar, index) => (
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

      {showModal && (
        <PopupModal
          title="Add a Calendar"
          open={showModal}
          onClose={() => setShowModal(false)}
        >
          <Form fields={CALENDER_FORM} onSubmit={handleSubmit} submitText="Create Calendar"/>
        </PopupModal>
      )}
    </div>
  );
};
