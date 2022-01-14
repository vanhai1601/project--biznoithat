create database myproject;
use myproject;

create table category
(
	id int not null auto_increment,
    name_category varchar(100) not null,
    code_category varchar(100) not null unique,
    primary key(id)
);


create table product(
	id int not null auto_increment,
    name_product varchar(100) not null,
    code_product varchar(50) not null unique,
    category_id int not null,
    img text not null,
    quantity int not null,
    quantity_sold int,
    price double not null,
    sale double not null,
    descriptions text not null,
    primary key(id),
    foreign key(category_id) references category(id)
);

create table user(
	id int not null auto_increment,
    username varchar(100) not null unique,
    email varchar(100) not null unique,
    phone varchar(20) not null unique,
    password text not null,
    roles int,
    address text,
    active boolean,
    primary key(id)
);
