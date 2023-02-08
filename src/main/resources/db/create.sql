create table sensors(
id int generated by default as identity primary key ,
name varchar(100) not null unique,
created_at timestamp not null,
updated_at timestamp,
created_who varchar
);

create table measurements(
id int generated by default as identity primary key ,
sensor_id int references sensors(id),
value float not null check (value<=100 and value >=-100),
is_raining boolean not null,
created_at timestamp not null
);
