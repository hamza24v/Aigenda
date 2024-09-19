# Capstone proposal

## Problem Statement
In today's fast paced world, many things are being improved and innovated. However, people struggle to manage their time effectively. Current calendar  solutions aren't keeping up with the needs of people's increasingly busy schedules.


## Technical Solution
Create a calendar application with a built-in ai assistant that managing a user's schedules. It can add,edit,delete events and answer related to the user's schedules

### Example

#### Scenario 1
John was invited to camping trip next July. Its currently September, and instead of manually navigating all the way to next July, he asks the chatbot to outline what other events has for that month and it responds accordingly (e.g., Here is your schedule for July: ....)

#### Scenario 2
Sally is a CEO of a company and is swamped with new and changed meetings daily. She uses the ai powered calendar app to automatically add, update, or delete events without her ever needing to manually do them. 

## Glossary
### User
 everyone who signs up is a user. They are members when they join a calendar. Each user is an admin of their personal calendar.
### Event
 anyone can add onetime/recurring events. An event has a date,time and attendees. Two kinds of events: broadcast events (all members and admins can view) need approval if created by members, but not needed if admin. Personal events don't need any approval. All events can be shared.
### Calendar
 calendar containing mapped out events. Calendar can either be organization/club or personal
### Admin
They have elevated privileges in managing calendars. All admins are users but not all users are admin


## High Level Requirement
- create a calendar (admin)
- create personal events (user, admin)
- edit personal events (user, admin)
- delete personal events (user, admin)
- create broadcast events (admin, user with approval)
- edit broadcast events (admin, user with approval)
- delete broadcast events (admin, user with approval)
- delete a calendar (admin)
- remove user from calendar (admin)
- view all events (admin)
- view invite/personal events (admin, user)


## User stories

### Create a calendar
    create an invite-only calendar
    have core events mapped out (these can't be edited/delete by members)
    name of organization/club
    precondition: user must be logged as member or admin.
    postcondition: 
### Edit a calendar 
    edit organization name
    precondition: user must be logged as admin.
    postcondition: calendar is edited

### delete a calendar
    deleting a calendar
    precondition: user must be logged as admin.
    post-condition: calendar is deleted


### Creating an event
    anyone can create an event but members have to be approved for broadcast events (events shared to everyone in org)
    precondition: user must be logged in as admin or member
    post-condition: if member and events are non-broadcast automatically approve. Need approval for broadcast events unless user is admin

### Edit an event 
    precondition: user must be logged in as admin or member. Event has to be in future
    post-condition: if user is member and event to edited are non-broadcast automatically approve. Need approval for broadcast events unless user is admin

### delete an event
    precondition: user must be logged in as admin or member. Event has to be in future
    post-condition: if user is member and event to deleted are non-broadcast automatically approve. If event is broadcast, event is deleted from user's view but not from admin's view and other member view (not attending). 
