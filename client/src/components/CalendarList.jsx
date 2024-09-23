import React from 'react'

function CalendarList({ title, calendars }) {

  return (
    <div className='w-full'>
        <h2>{title} <span>+</span></h2>
        <li>
            
        </li>
        {calendars.map((calendar, index) => (
          <div key={index}>
            <h3>{calendar.name}</h3>
          </div>
        ))}
      </div>
  )
}

export default CalendarList