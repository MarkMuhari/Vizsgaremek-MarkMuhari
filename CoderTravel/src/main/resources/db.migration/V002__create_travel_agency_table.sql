create table travel_agency (id integer generated by default as identity, name varchar(255),
 location_id integer, primary key (id));
alter table travel_agency add constraint FKiy5kki56pg7n04jqna4wf2l6m foreign key (location_id) references location