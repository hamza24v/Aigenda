# Class Details

## App

### `public static void main(String[])`
- Instantiate all required classes with valid arguments, dependency injection.
- Run controller.

## Controllers

### `controller.UserController`
- **Private Service:** `UserService`
- **Methods:**
  - `public List<User> findAll` - **GET** all users
  - `public ResponseEntity<Object> findById` - **GET** user by ID
  - `public ResponseEntity<Object> add` - Add a user (**POST**)
  - `public ResponseEntity<Object> edit` - Edit a user (**PUT**)
  - `public ResponseEntity<Object> deleteById` - Delete a user (**DELETE**)

### `controller.EventController`
- **Private Service:** `EventService`
- **Methods:**
  - `public List<Event> findAll` - **GET** all Events
  - `public ResponseEntity<Object> findById` - **GET** Event by ID
  - `public ResponseEntity<Object> add` - Add an Event (**POST**)
  - `public ResponseEntity<Object> edit` - Edit an Event (**PUT**)
  - `public ResponseEntity<Object> deleteById` - Delete an Event (**DELETE**)

### `controller.CalendarController`
- **Private Service:** `CalendarService`
- **Methods:**
  - `public List<Calendar> findAll` - **GET** all Calendars
  - `public ResponseEntity<Object> findById` - **GET** Calendar by ID
  - `public ResponseEntity<Object> add` - Add a Calendar (**POST**)
  - `public ResponseEntity<Object> edit` - Edit a Calendar (**PUT**)
  - `public ResponseEntity<Object> deleteById` - Delete a Calendar (**DELETE**)

### `controller.RoleController`
- **Private Service:** `RoleService`
- **Methods:**
  - `public List<Role> findAll` - **GET** all Roles
  - `public ResponseEntity<Object> findById` - **GET** Role by ID
  - `public ResponseEntity<Object> add` - Add a Role (**POST**)
  - `public ResponseEntity<Object> edit` - Edit a Role (**PUT**)
  - `public ResponseEntity<Object> deleteById` - Delete a Role (**DELETE**)

### `controller.AttendeeController`
- **Private Service:** `AttendeeService`
- **Methods:**
  - `public List<Attendee> findAll` - **GET** all Attendees
  - `public ResponseEntity<Object> findById` - **GET** Attendee by ID
  - `public ResponseEntity<Object> add` - Add an Attendee (**POST**)
  - `public ResponseEntity<Object> edit` - Edit an Attendee (**PUT**)
  - `public ResponseEntity<Object> deleteById` - Delete an Attendee (**DELETE**)

### `controller.InviteController`
- **Private Service:** `InviteService`
- **Methods:**
  - `public List<Invite> findAll` - **GET** all Invites
  - `public ResponseEntity<Object> findById` - **GET** Invite by ID
  - `public ResponseEntity<Object> add` - Add an Invite (**POST**)
  - `public ResponseEntity<Object> edit` - Edit an Invite (**PUT**)
  - `public ResponseEntity<Object> deleteById` - Delete an Invite (**DELETE**)

### `controller.GlobalExceptionHandler`
- **Methods:**
  - `public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException ex)` - Handler for DataIntegrityViolationException errors
  - `public ResponseEntity<ErrorResponse> handleException(Exception ex)` - Generic handler for all other exceptions

### `controller.ErrorResponse`
- **Private fields:**
  - `private final LocalDateTime timestamp` – Tracks the exact time the error occurred
  - `private final String message` – Error message
- **Methods:**
  - `public String getMessage()` – Gets the error message
  - `public ErrorResponse()` – Constructs the error response
  - `public static ResponseEntity<ErrorResponse> build(String message)` - Creates & returns an error response with the passed-in message

---

## Data

### `data.UserJdbcTemplateRepository`
- **Private field:** `private final JdbcTemplate jdbcTemplate`
- **Constructor:** `public UserJdbcTemplateRepository(JdbcTemplate jdbcTemplate)`
- **Methods:**
  - `public List<User> findById(String)` – Finds a user by ID
  - `public User add(User user)` – Create a User
  - `public boolean update(User user)` – Update a User
  - `public boolean deleteById(int)` – Delete a User by ID
  - `private List<User> findAll()` – Finds all Users

### `data.UserRepository (interface)`
- **Contract for:** `UserJdbcTemplateRepository`
- **Methods:**
  - `List<User> findByAll()` – Finds all users
  - `User findById(int id)` – Finds a user by ID
  - `User add(User user)` – Adds a user
  - `Boolean update(User user)` – Updates a user
  - `Boolean delete(int id)` – Deletes a user

### `data.CalendarJdbcTemplateRepository`
- **Private field:** `private final JdbcTemplate jdbcTemplate`
- **Constructor:** `public CalendarJdbcTemplateRepository(JdbcTemplate jdbcTemplate)`
- **Methods:**
  - `public List<Calendar> findById(String)` – Finds a Calendar by ID
  - `public Calendar add(Calendar calendar)` – Creates a Calendar
  - `public boolean update(Calendar calendar)` – Updates a Calendar
  - `public boolean deleteById(int)` – Deletes a Calendar by ID
  - `private List<Calendar> findAll()` – Finds all Calendars

### `data.CalendarRepository (interface)`
- **Contract for:** `CalendarJdbcTemplateRepository`
- **Methods:**
  - `List<Calendar> findByAll()` – Finds all Calendars
  - `Calendar findById(int id)` – Finds a Calendar by ID
  - `Calendar add(Calendar calendar)` – Adds a Calendar
  - `Boolean update(Calendar calendar)` – Updates a Calendar
  - `Boolean delete(int id)` – Deletes a Calendar

### `data.EventJdbcTemplateRepository`
- **Private field:** `private final JdbcTemplate jdbcTemplate`
- **Constructor:** `public EventJdbcTemplateRepository(JdbcTemplate jdbcTemplate)`
- **Methods:**
  - `public List<Event> findById(String)` – Finds an Event by ID
  - `public Event add(Event event)` – Creates an Event
  - `public boolean update(Event event)` – Updates an Event
  - `public boolean deleteById(int)` – Deletes an Event by ID
  - `private List<Event> findAll()` – Finds all Events

### `data.EventRepository (interface)`
- **Contract for:** `EventJdbcTemplateRepository`
- **Methods:**
  - `List<Event> findByAll()` – Finds all Events
  - `Event findById(int id)` – Finds an Event by ID
  - `Event add(Event event)` – Adds an Event
  - `Boolean update(Event event)` – Updates an Event
  - `Boolean delete(int id)` – Deletes an Event

### `data.AttendeeJdbcTemplateRepository`
- **Private field:** `private final JdbcTemplate jdbcTemplate`
- **Constructor:** `public AttendeeJdbcTemplateRepository(JdbcTemplate jdbcTemplate)`
- **Methods:**
  - `public List<Attendee> findById(String)` – Finds an Attendee by ID
  - `public Attendee add(Attendee attendee)` – Creates an Attendee
  - `public boolean update(Attendee attendee)` – Updates an Attendee
  - `public boolean deleteById(int)` – Deletes an Attendee by ID
  - `private List<Attendee> findAll()` – Finds all Attendees

### `data.AttendeeRepository (interface)`
- **Contract for:** `AttendeeJdbcTemplateRepository`
- **Methods:**
  - `List<Attendee> findByAll()` – Finds all Attendees
  - `Attendee findById(int id)` – Finds an Attendee by ID
  - `Attendee add(Attendee attendee)` – Adds an Attendee
  - `Boolean update(Attendee attendee)` – Updates an Attendee
  - `Boolean delete(int id)` – Deletes an Attendee

### `data.RoleJdbcTemplateRepository`
- **Private field:** `private final JdbcTemplate jdbcTemplate`
- **Constructor:** `public RoleJdbcTemplateRepository(JdbcTemplate jdbcTemplate)`
- **Methods:**
  - `public List<Role> findById(String)` – Finds a Role by ID
  - `public Role add(Role role)` – Creates a Role
  - `public boolean update(Role role)` – Updates a Role
  - `public boolean deleteById(int)` – Deletes a Role by ID
  - `private List<Role> findAll()` – Finds all Roles

### `data.RoleRepository (interface)`
- **Contract for:** `RoleJdbcTemplateRepository`
- **Methods:**
  - `List<Role> findByAll()` – Finds all Roles
  - `Role findById(int id)` – Finds a Role by ID
  - `Role add(Role role)` – Adds a Role
  - `Boolean update(Role role)` – Updates a Role
  - `Boolean delete(int id)` – Deletes a Role

### `data.InviteJdbcTemplateRepository`
- **Private field:** `private final JdbcTemplate jdbcTemplate`
- **Constructor:** `public InviteJdbcTemplateRepository(JdbcTemplate jdbcTemplate)`
- **Methods:**
  - `public List<Invite> findById(String)` – Finds an Invite by ID
  - `public Invite add(Invite invite)` – Creates an Invite
  - `public boolean update(Invite invite)` – Updates an Invite
  - `public boolean deleteById(int)` – Deletes an Invite by ID
  - `private List<Invite> findAll()` – Finds all Invites

### `data.InviteRepository (interface)`
- **Contract for:** `InviteJdbcTemplateRepository`
- **Methods:**
  - `List<Invite> findByAll()` – Finds all Invites
  - `Invite findById(int id)` – Finds an Invite by ID
  - `Invite add(Invite invite)` – Adds an Invite
  - `Boolean update(Invite invite)` – Updates an Invite
  - `Boolean delete(int id)` – Deletes an Invite
