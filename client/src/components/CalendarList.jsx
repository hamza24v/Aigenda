import React, { useEffect } from "react";
import { AddCalendar } from '../components/AddCalendar';
import { useCalendars } from "../contexts/CalendarsContext";
import EditOutlinedIcon from "@mui/icons-material/EditOutlined";
import DeleteOutlinedIcon from "@mui/icons-material/DeleteOutlined";
import { PopupModal } from "../components/PopupModal";
import { useState } from "react";
import { Form } from "../components/Form";
import { EDIT_CALENDAR_FORM } from "../constants";

export const CalendarList = ({ title, calendars }) => {

  const {deleteCalendar, updateCalendar} = useCalendars()
  const [showModal, setShowModal] = useState(false);
  const [calendarToEdit,setCalendarToEdit] = useState()


  const handleSubmit = async (formData, calendar) => {
    const {title, type} = formData;
    const updatedTitle = title ? title : calendar.title;
    const updatedType = type ? type : calendar.type;
    const updatedCalendar = {...calendar, title : updatedTitle, type: updatedType}
    console.log(updatedCalendar)
    await updateCalendar(updatedCalendar);
    setShowModal(false);

  };

  const handleCalendarToEdit = (calendar) => {
    setCalendarToEdit(calendar);
    setShowModal(true);
  }


  
  return (
    <div className="p-4">
      <div className="flex justify-between">
        <h2 className="font-bold text-lg">{title}</h2>
        <AddCalendar />
      </div>

      {calendars?.map((calendar, index) => (
        <div key={index} className="flex justify-between">
          <div>
            <input
              type="checkbox"
              id={`calendar-${index}`}
              name="title"
              value={calendar.title}
            />
            <label htmlFor={`calendar-${index}`} className="pl-4">
              {calendar.title}
            </label>
          </div>
          <div className="flex space-x-4 justify-center items-center ml-auto">
            <div>
              <EditOutlinedIcon className="text-gray-600 cursor-pointer hover:text-blue-600 hover:bg-gray-200 rounded-full  transition-all ease-in-out"
              onClick={()=> handleCalendarToEdit(calendar)} />
              {showModal && (
              <PopupModal
                title="Edit Calendar"
                open={showModal}
                onClose={() => setShowModal(false)}
                >
                  <Form
                    fields={EDIT_CALENDAR_FORM}
                    onSubmit={(formData)=> {handleSubmit(formData, calendar)}}
                    submitText="Submit Changes"
                    defaultValues={calendarToEdit}
                  />
                </PopupModal>
            )}
            </div>
            <DeleteOutlinedIcon
              className="text-gray-600 cursor-pointer hover:text-red-600 hover:bg-gray-200 rounded-full  transition-all ease-in-out"
              onClick={() => deleteCalendar(calendar.calendarId)}
            />
          </div>
          <br />
        </div>
      ))}


    </div>
  );
};
