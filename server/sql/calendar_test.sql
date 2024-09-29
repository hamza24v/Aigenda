drop database if exists calendar_db_test;
create database calendar_db_test;
use calendar_db_test;

create table app_user (
	app_user_id int primary key auto_increment,
	first_name varchar(60) not null,
    last_name varchar(60) not null,
    email varchar(100) not null,
	username varchar(100) not null unique,
	disabled boolean not null default(0),
	`password` varchar(2048) not null
);

create table calendar (
    calendar_id int primary key auto_increment,
    title varchar(100) not null,
    `type` varchar(50) not null,
    app_user_id int,
    foreign key (app_user_id) references app_user(app_user_id)
);

create table event (
    event_id int primary key auto_increment,
    title varchar(100) not null,
    `description` varchar(255) not null,
    calendar_id int,
    app_user_id int,
    `type` varchar(50) not null,
    start_time datetime not null,
    end_time datetime not null,
    `status` varchar(50) not null,
    foreign key (calendar_id) references calendar(calendar_id),
    foreign key (app_user_id) references app_user(app_user_id)
);

create table attendee (
    attendee_id int primary key auto_increment,
    event_id int,
    app_user_id int,
    `status` varchar(50) not null,
    foreign key (event_id) references event(event_id),
    foreign key (app_user_id) references app_user(app_user_id)
);

create table `role` (
    role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table invite (
	invite_id int primary key auto_increment,
    event_id int,
    calendar_id int,
    app_user_id int,
    `status` varchar(50) not null,
    foreign key (event_id) references event(event_id),
    foreign key (calendar_id) references calendar(calendar_id),
    foreign key (app_user_id) references app_user(app_user_id)
);

create table user_role (
	role_id int not null,
    app_user_id int not null
);


create table user_calendar_role (
    ucr_id int primary key auto_increment,
    app_user_id int not null,
    role_id int not null,
    calendar_id int not null,
    foreign key (app_user_id) references app_user(app_user_id),
    foreign key (calendar_id) references calendar(calendar_id),
    foreign key (role_id) references role(role_id)
);

delimiter //

create procedure set_known_good_state()
begin
    -- Delete from child tables first to prevent foreign key constraint violations
    delete from user_calendar_role;
    delete from attendee;
    delete from invite;
    delete from `event`;        -- Delete from event before calendar

    -- Now delete from calendar (which references app_user)
    delete from calendar;

    -- Finally, delete from app_user (after all child records are deleted)
    delete from app_user;

    -- Delete from role as it's independent of other tables
    delete from `role`;

    -- Reset auto-increment values
    alter table user_calendar_role auto_increment = 1;
    alter table attendee auto_increment = 1;
    alter table invite auto_increment = 1;
    alter table `event` auto_increment = 1;
    alter table calendar auto_increment = 1;
    alter table app_user auto_increment = 1;
    alter table `role` auto_increment = 1;

	insert into app_user (app_user_id, first_name, last_name, email, username, disabled, `password`)
	values 
	(1, 'John', 'Doe', 'john.doe@gmail.com', 'testuser', false, '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa'),
	(2, 'Jane', 'Smith', 'jane.smith@gmail.com', 'usertest', false, '$2a$10$ntB7CsRKQzuLoKY3rfoAQen5nNyiC/U60wBsWnnYrtQQi8Z3IZzQa');

	insert into calendar (calendar_id, title, `type`, app_user_id)
	values 
	(1, 'Work Cal', 'Broadcast', 1),
	(2, 'Personal Calendar', 'Personal', 2);

	insert into `event` (event_id, title, `description`, calendar_id, app_user_id, `type`, start_time, end_time, `status`)
	values
	(1, 'Project Meeting', 'Discuss project updates', 1, 1, 'Organization', 
		'2024-10-25 10:00:00', '2024-10-25 11:00:00', 'Scheduled'),
	(2, 'Get dinner', 'Go get dinner', 2, 2, 'Personal', 
		'2024-09-30 18:00:00', '2024-09-30 19:00:00', 'Scheduled');
		
	insert into `role` (role_id, `name`)
	values 
	(1, 'Admin'),
	(2, 'User');

	insert into attendee (event_id, app_user_id, `status`)
	values
	(1, 1, 'Confirmed'), 
	(2, 2, 'Pending');  
    
    insert into invite (invite_id, `status`, app_user_id, event_id)
    values
    (1, 'Pending', 1, 1),
    (2, 'Confirmed', 2, 2);
    
    insert into user_calendar_role (ucr_id, app_user_id, role_id, calendar_id)
    values
    (1,1,2,1),
    (2,2,1,1);
    
end //

delimiter ;


