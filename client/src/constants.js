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
    }
]

const CALENDER_FORM = [
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
            { value: 'personal', label: 'Personal' },
            { value: 'organization', label: 'Organization'}
        ],
        placeholder: 'Select Type',
        required: true
    }
]

const CALENDERS = [
    {
      title: "John Smith",
      type: "personal"
    },
  ];



export { EVENT_FORM, CALENDER_FORM, CALENDERS }