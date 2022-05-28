create table location
(
    id                int auto_increment,
    city              varchar(255),
    country           varchar(255),
    iso               varchar(255),
    street_and_number varchar(255),
    primary key (id)
);