drop database if exists calendar_db_test;
create database calendar_db_test;
use calendar_db_test;

create table app_user (
	app_user_id int primary key auto_increment,
	first_name varchar(60) not null,
    last_name varchar(60) not null,
    email varchar(100) not null,
	username varchar(100) not null,
	disabled boolean not null default(0),
	password varchar(2048) not null
);

create table calendar (
    calendar_id int primary key auto_increment,
    title varchar(100) not null,
    type varchar(50) not null,
    app_user_id int,
    foreign key (app_user_id) references app_user(app_user_id)
);

create table event (
    event_id int primary key auto_increment,
    title varchar(100) not null,
    description varchar(255) not null,
    calendar_id int,
    app_user_id int,
    type varchar(50) not null,
    start_time datetime not null,
    end_time datetime not null,
    status varchar(50) not null,
    foreign key (calendar_id) references calendar(calendar_id),
    foreign key (app_user_id) references app_user(app_user_id)
);

create table attendee (
    attendee_id int primary key auto_increment,
    event_id int,
    app_user_id int,
    status varchar(50) not null,
    foreign key (event_id) references event(event_id),
    foreign key (app_user_id) references app_user(app_user_id)
);

create table role (
    role_id int primary key auto_increment,
    `name` varchar(50) not null
);

create table invitation (
	invitation_id int primary key auto_increment,
    event_id int,
    calendar_id int,
    app_user_id int,
    status varchar(50) not null,
    foreign key (event_id) references event(event_id),
    foreign key (calendar_id) references calendar(calendar_id),
    foreign key (app_user_id) references app_user(app_user_id)
);


create table calendar_user_role (
    id int primary key auto_increment,
    app_user_id int not null,
    role_id int not null,
    calendar_id int not null,
    foreign key (app_user_id) references app_user(app_user_id),
    foreign key (calendar_id) references calendar(calendar_id),
    foreign key (role_id) references role(role_id)
);

insert into app_user (first_name, last_name, email, username, disabled, password)
values 
('John', 'Doe', 'john.doe@gmail.com', 'testuser', false, 'password123'),
('Jane', 'Smith', 'jane.smith@gmail.com', 'usertest', false, 'password456');

insert into calendar (title, type, app_user_id)
values 
('Work Cal', 'Broadcast', 1),
('Personal Calendar', 'Personal', 2);

insert into event (title, description, calendar_id, app_user_id, type, start_time, end_time, status)
values
('Project Meeting', 'Discuss project updates', 1, 1, 'Organization', 
    '2024-10-25 10:00:00', '2024-10-25 11:00:00', 'Scheduled'),
('Get dinner', 'Go get dinner', 2, 2, 'Personal', 
    '2024-09-30 18:00:00', '2024-09-30 19:00:00', 'Scheduled');
    
select * from event;


