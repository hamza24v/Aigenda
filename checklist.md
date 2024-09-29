# Capstone Assessment


## Tasks


### Part 1: Calendar Database
* [ ] Create a calendar database & create the user, calendar, events, attendee, role and invitation tables based on the table schema 
* [ ] Populate the tables with test & production data to be used for the backend


**Commit all changes and push to GitHub**


### Part 2: Java Backend 
* [ ] Create the models for user, calendar, events, attendee, role and invitation
* [ ] Work on the data layer, creating repo classes for every model with add, delete, edit & view all methods. Also create mapper classes for each model. 
* [ ] Create test classes to verify the functionality of the repo classes, test it using the test data from the table calendar-schema-test.sql.
* [ ] Work on the domain layer, creating service classes for user, calendar, events, attendee , role & invitation. 
* [ ] Create Response, Result & ResultType to be used in the service classes to store the results 
of
* [ ] Create test classes to test the methods in the service classes. Use mocks for testing. 
* [ ] In the controller layer, create GlobalExceptionHandler & ErrorResponse classes for error handling. GlobalExceptionHandler should have a specific exception written for DataIntegrityViolationExceptions & then a more generic exception handler case. 
* [ ] Write the controller classes for the 6 models & service classes. 
* [ ] Create a http package & http files for each model name. Each http file should have happy & unhappy paths for each method being tested.
* [ ] Test each controller using the corresponding http classes to verify that the CRUD operations for each controller are working. 


**Commit all changes and push to GitHub**


### Part 3: Client-Side (React)


  * [ ] Create a react project with CRA (create-react-app) & remove the unneeded classes 
  * [ ] Install `react-router-dom`
  * [ ] Install clerk for security verification 
  * [ ] Add bootstrap & tailwind css to the html
  * [ ] Create the components Navbar, ChatBot, PopupModal, the pages Invites, Home, services: apiService, openaiService & the the context class CalenderContext
* [ ] Implement CalenderContext for managing the states of the different components 
 * [ ] Implement the Navbar & PopupModal components 
* [ ] Implement the required client-side routes (#.# hours)
  * [ ] Define the necessary client-side routes:
	* [ ] Home Calendar page
	* [ ] Event invites page
  * [ ] Implement the Home, Invite & Not Found pages for the site 
  * [ ] Display a "Not Found" message if a route doesn't match one of the defined routes
  * [ ] Implement the calendar display with react-big-calendar
  * [ ] Implement event & calendar filtering with checkboxes to allow the user to toggle from multiple calendar & event types
  * [ ] Set up Fetch API for CRUD operations & verify that app is probably integrated with the backend 
  * [ ] Implement the AI assistant helper with OpenAI’s API in the chatbot class
  * [ ] Implement the invitations page that would allow member users to accept invites to events from admin 
  * [ ] Set up routing with react routing to add routing between the calendar & invitations page 
  * [ ] Verify the functionality of calendar app, users should be able to use the AI api to add, delete, update & view events on their calendar as well as answer some basic questions regarding event scheduling, verify users are able to be invited to events from the backend & either accept or decline them 
  * [ ] Set up routing with react routing to add routing between the calendar & invitations page 
  * [ ] Use tailwind & bootstrap to clean up the app with some styling 
