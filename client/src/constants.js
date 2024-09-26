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



export { EDIT_CALENDAR_FORM, EVENT_FORM, CALENDAR_FORM, CALENDARS, REGISTRATION_FORM, LOGIN_FORM }