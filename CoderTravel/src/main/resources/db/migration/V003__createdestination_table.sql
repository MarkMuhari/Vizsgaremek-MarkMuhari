create table destination
(
    id               int auto_increment,
    description      varchar(255),
    price_per_day    int not null,
    title            varchar(255),
    location_id      int,
    travel_agency_id int,
    primary key (id),
    foreign key (location_id) references location (id),
    foreign key (travel_agency_id) references travel_agency (id)
);