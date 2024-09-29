import { useState } from "react";
import { CALENDAR_FORM } from "../constants";
import { PopupModal } from "../components/PopupModal";
import { Form } from "../components/Form";
import { useCalendars } from "../contexts/CalendarsContext";

export const AddCalendar = () => {
  const [showModal, setShowModal] = useState(false);
  const { createCalendar } = useCalendars();

  const handleSubmit = async (formData) => {
    await createCalendar(formData);
    setShowModal(false);

  };

  return (
    <div>
      <span
        className="text-lg cursor-pointer px-2 hover:rounded-full hover:bg-gray-50"
        onClick={() => setShowModal(true)}
      >
        +
      </span>
      {showModal && (
        <PopupModal
          title="Add a Calendar"
          open={showModal}
          onClose={() => setShowModal(false)}
        >
          <Form
            fields={CALENDAR_FORM}
            onSubmit={handleSubmit}
            submitText="Create Calendar"
          />
        </PopupModal>
      )}
    </div>
  );
};
