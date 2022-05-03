
drop table if exists users, rooms, messages;

create table if not exists users (
    user_id          bigserial primary key ,
    login       text not null ,
    password    varchar
);

create table if not exists rooms (
    chatroom_id        bigserial primary key ,
    owner       bigint not null,
    foreign key (owner) references users(user_id),
    chat_name   text not null
);

create table if not exists messages (
    message_id          bigserial primary key,
    author      bigint not null,
    room        bigint not null,
    foreign key (author) references users(user_id),
    foreign key (room) references rooms(chatroom_id),
    message     text not null ,
    send_date   timestamp
);