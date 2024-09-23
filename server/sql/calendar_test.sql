drop database if exists calendar_db;
create database calendar_db;
use calendar_db;

create table user (
	user_id int primary key auto_increment,
	first_name varchar(60) not null,
    last_name varchar(60) not null,
    email varchar(100) not null,
	password varchar(100) not null
);

create table calendar (
    calendar_id int primary key auto_increment,
    title varchar(100) not null,
    type varchar(50) not null,
    user_id int,
    foreign key (user_id) references user(user_id)
);

create table event (
    event_id int primary key auto_increment,
    title varchar(100) not null,
    description varchar(255) not null,
    calendar_id int,
    user_id int,
    type varchar(50) not null,
    start_time datetime not null,
    end_time datetime not null,
    status varchar(50) not null,
    foreign key (calendar_id) references calendar(calendar_id),
    foreign key (user_id) references user(user_id)
);

create table attendee (
    attendee_id int primary key auto_increment,
    event_id int,
    user_id int,
    status varchar(50) not null,
    foreign key (event_id) references event(event_id),
    foreign key (user_id) references user(user_id)
);

create table role (
    role_id int primary key auto_increment,
    calendar_id int,
    user_id int,
    role_type varchar(50) not null,
    foreign key (calendar_id) references calendar(calendar_id),
    foreign key (user_id) references user(user_id)
);

create table invitation (
    event_id int,
    calendar_id int,
    user_id int,
    status varchar(50) not null,
    foreign key (event_id) references event(event_id),
    foreign key (calendar_id) references calendar(calendar_id),
    foreign key (user_id) references user(user_id),
    primary key (event_id, calendar_id, user_id)
);