create table if not exists log (
    id bigint auto_increment primary key,
    endpoint varchar(100),
    message varchar(1000),
    ip varchar(100),
    correlation_id varchar(100),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp on update current_timestamp
);