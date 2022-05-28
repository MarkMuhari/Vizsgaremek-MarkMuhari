create table travel_agency
(
    id          int auto_increment,
    name        varchar(255),
    location_id int,
    primary key (id),
    foreign key (location_id) references location (id)
);