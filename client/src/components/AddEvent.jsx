import { useState} from 'react'
import { EVENT_FORM } from "../constants";
import { Button } from "@mui/material";
import { PopupModal } from "../components/PopupModal";
import { Form } from "../components/Form";
import { useEvents } from "../contexts/EventsContext";

export const AddEvent = () => {
    const [showModal, setShowModal] = useState(false);
  const { createEvent } = useEvents();

  const handleSubmit = async (formData) => {
    await createEvent(formData);
    setShowModal(false);
  };


  return (
    <div className="fixed right-10 bottom-10 z-50 ">
        <Button variant="contained" onClick={() => setShowModal(true)}>
          Add Event
        </Button>

        {showModal && (
          <PopupModal
            title="Add an Event"
            open={showModal}
            onClose={() => setShowModal(false)}
          >
            <Form fields={EVENT_FORM} onSubmit={handleSubmit} submitText='Create Event'/>
          </PopupModal>
        )}
      </div>
  );
}