create table destination (id integer generated by default as identity, description varchar(255),
 price_per_day integer not null, title varchar(255), location_id integer, travel_agency_id integer, primary key (id));
alter table destination add constraint FKrb0jonw01s7bog2ssbitr6fj6
    foreign key (location_id) references location
alter table destination add constraint FK8sx2pry418efxqk0j4q8xrwjm
    foreign key (travel_agency_id) references travel_agency