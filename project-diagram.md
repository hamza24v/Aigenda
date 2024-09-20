Project Structure Diagram: 
server
│   │       └───sql
│   │           │   calendar-schema-production.sql
│   │           │   calendar-schema-test.sql
│   │       └───http
│   │           │   calendar-schema-production.sql
│   │           │   calendar-schema-test.sql
├───src
├───main
│   ├───java
│   │   └───learn

│   │       └───calendar
│   │           │   App.java
│   │           │
│   │           ├───controller
│   │           │      UserController.java
│   │           │      EventController.java
│   │           │      CalendarController.java
│   │           │      InviteController.java
│   │           │      ErrorResponse.java
│   │           │      GlobalExceptionHandler.java
│   │           │      AttendeeControllerjava
│   │           │      RoleController.java
│   │           ├───data
            │   │           │            ├───mappers
	│   │           │                              UserMapper.java
	│   │           │                              EventMapper.java
            │   │           │                              CalendarMapper.java
            │   │           │                              AtendeeMapper.java
	│   │           │			     RoleMapper.java
            │   │           │			     InviteMapper.java
│   │           │      UserJdbcTemplateRepository.java
│   │           │      UserRepository.java
│   │           │      CalendarJdbcTemplateRepository.java
│   │           │      CalendarRepository.java
│   │           │      EventJdbcTemplateRepository.java
│   │           │      EventRepository.java
│   │           │	    AttendeeRepository.java
│   │           │	    AttendeeJdbcTemplateRepository.java
│   │           │	     RoleJdbcTemplateRepository.java
│   │           │	     RoleRepository.java
│   │           │	    InviteRepository.java
│   │           │	    InviteJdbcTemplateRepository.java
│   │           │
│   │           ├───domain
│   │           │      UserService.java
│   │           │      EventService.java
│   │           │      CalendarService.java
│   │           │      AttendeeService.java
│   │           │      InviteService.java
│   │           │      RoleService.java
│   │           │      Response.java
│   │           │      Result.java
│   │           │      ResultType.java
│   │           │
│   │           ├───models
│   │           │       User.java
│   │           │       Calendar.java   
│   │           │       Event.java   
│   │           │       Attendee.java   
│   │           │       Role.java 
│   │           │       Attendee.java   
│   │           │       RoleType.java   
│   │           │       EventType.java   
│   │           │       CalType.java     
│   │           │       Invite.java   
│   │           │
│   │App.java
│   └───resources
└───test
    └───java
        └───learn
            └───calendar
                ├───data
                │       UserJDBCRepositoryTestt.java
                │       CalendarJDBCRepositoryTest.java
                │       EventJDBCRepositoryTest.java
                │       AttendeeJDBCRepositoryTestt.java
                │       RoleJDBCRepositoryTest.java
                │	  InviteJDBCRepositoryTest.java

                │
                └───domain
                        UserServiceTest.java
                        CalendarServiceTest.java
InviteServiceTest.java
EventServiceTest.java
                        AttendeeServiceTest.java
                        RoleServiceTest.java
	
Client
├───	
calendar
├───src
│   ├───components
│   │   		Navbar.jsx
            │   │		ChatBot.jsx
            │   │               PopupModal.jsx
│   ├───pages
│   ├   		Invites
│   ├		Home
│   ├		NotFound
            │   ├───services
│   │		apiService.jsx
│   │		openaiService.jsx
│   ├───Context
│   │		CalenderContext.jsx		
