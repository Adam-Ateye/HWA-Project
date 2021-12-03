create table if not exists `login` (
	`id` int(40) not null auto_increment,
    `username` varchar(40) default null,
	`password` varchar(40) default null,
    primary key(`id`)
);

select*from login;

create table if not exists `country` (
	`id` int(40) not null auto_increment,
    `name` varchar(40) default null,
    `date` varchar(40) default null,
    `length` int(40) not null,
	primary key(`id`)
);

select*from country;