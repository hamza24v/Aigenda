const EVENT_FORM = [
    {
        label: 'Title',
        name: 'title',
        placeholder: 'Enter Title',
        required: true
    },
    {
        label: 'Description',
        name: 'description',
        placeholder: 'Enter Description',
        required: false
    },
    {
        label: 'Start Date',
        name:'startDate',
        type: 'datetime-local',
        placeholder: 'Select Start Date',
        required: true
    },
    {
        label: 'End Date',
        name: 'endDate',
        type: 'datetime-local',
        placeholder: 'Select End Date',
        required: true
    },
    {
        label: 'Type',
        name: 'eventType',
        type: 'select',
        options: [
            { value: 'PERSONAL', label: 'Personal' },
            { value: 'ORGANIZATION', label: 'Organization'}
        ],
        placeholder: 'Select Type',
        required: true
    },
    {
        label: 'Calendars',
        name: 'calendarId',
        type: 'select',
        options: [],
        placeholder: 'Select Calendars',
        required: true
    }
]

const CALENDAR_FORM = [
    {
        label: 'Title',
        name: 'title',
        placeholder: 'Enter Title',
        required: true
    },
    {
        label: 'Type',
        name: 'type',
        type: 'select',
        options: [
            { value: 'PERSONAL', label: 'Personal' },
            { value: 'ORGANIZATION', label: 'Organization'}
        ],
        placeholder: 'Select Type',
        required: true
    }
]
const EDIT_CALENDAR_FORM = [
    {
        label: 'Title',
        name: 'title',
        placeholder: 'Enter Title',
        required: true
    },
    {
        label: 'Type',
        name: 'type',
        type: 'select',
        options: [
            { value: 'PERSONAL', label: 'Personal' },
            { value: 'ORGANIZATION', label: 'Organization'}
        ],
        placeholder: 'Select Type',
        required: true
    }
]

const CALENDARS = [
    {
      title: "John Smith",
      type: "personal"
    },
  ];

const REGISTRATION_FORM = [
    {
        label: 'First Name',
        name: 'firstName',
        placeholder: 'Enter First Name',
        required: true
    },
    {
        label: 'Last Name',
        name: 'lastName',
        placeholder: 'Enter Last Name',
        required: true
    },
    {
        label: 'User Name',
        name: 'username',
        placeholder: 'Enter a New User Name',
        required: true,
    },
    {
        label: 'Email',
        name: 'email',
        type: 'email',
        placeholder: 'Enter Email',
        required: true
    },
    {
        label: 'Password',
        name: 'password',
        type: 'password',
        placeholder: 'Enter Password',
        required: true
    }
]

const LOGIN_FORM = [
    {
        label: 'User Name',
        name: 'username',
        placeholder: 'Enter User Name',
        required: true,
    },
    {
        label: 'Password',
        name: 'password',
        type: 'password',
        placeholder: 'Enter Password',
        required: true
    }
]

const features = [ 
    {
      "image": "/ai_schedule.jpg",
      "title": "AI-Powered Event Scheduling",
      "description":
        "No more manual input—ask AiGenda to create events for you! Whether it's setting a meeting, scheduling a task, or booking an appointment, the AI handles it all in seconds, saving you time and effort."
    },
    {
      "image": "/reminder.png",
      "title": "Smart Reminders & Notifications",
      "description":
        "AiGenda provides intelligent reminders that adjust based on your schedule changes and priorities. Never miss an important event, with notifications that adapt to real-time updates."
    },
    {
      "image": "/integration.jpg",
      "title": "Seamless Calendar Integration",
      "description":
        "AiGenda syncs effortlessly with your favorite calendar tools like Google Calendar and Outlook, ensuring all your events are in one place while benefiting from AI-driven automation."
    }
];



export { EDIT_CALENDAR_FORM, EVENT_FORM, CALENDAR_FORM, CALENDARS, REGISTRATION_FORM, LOGIN_FORM, features }