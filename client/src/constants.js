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
        name:'start_date',
        type: 'datetime-local',
        placeholder: 'Select Start Date',
        required: true
    },
    {
        label: 'End Date',
        name: 'end_date',
        type: 'datetime-local',
        placeholder: 'Select End Date',
        required: true
    },
    { // TODO: add a way to take in multiple emails
        label: 'Guests',
        name: 'guests',
        type: 'email',
        placeholder: 'Enter Guests Emails',
        required: false
    },
    {
        label: 'Calendars',
        name: 'calendars',
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



export { EVENT_FORM, CALENDAR_FORM, CALENDARS, REGISTRATION_FORM, LOGIN_FORM }