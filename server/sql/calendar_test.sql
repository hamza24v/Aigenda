drop database if exists calendar_db;
create database calendar_db;
use calendar_db;

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
    `name` varchar(50) not null,

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
    constraint pk_app_user_role
        primary key (app_user_id, role_id, calendar_id),
    constraint fk_cal_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
	constraint fk_cal_user_role_cal_id
        foreign key (calendar_id)
        references calendar(calendar_id),
    constraint fk_cal_user_role_role_id
        foreign key (role_id)
        references role(role_id)
);



