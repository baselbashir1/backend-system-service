create table if not exists bundle (
    id bigint auto_increment primary key,
    name varchar(100) not null,
    description varchar(300),
    price decimal(10,2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp
);