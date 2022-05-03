create schema if not exists chat;

drop table if exists chat.users, chat.rooms, chat.messages;


create table if not exists chat.users (
    user_id          bigserial primary key ,
    login       text not null ,
    password    varchar
);

create table if not exists chat.rooms (
    chatroom_id        bigserial primary key ,
    owner       bigint not null references chat.users(user_id),
    chat_name   text not null
);

create table if not exists chat.messages (
    message_id          bigserial primary key,
    author      bigint not null references chat.users(user_id),
    room        bigint not null references chat.rooms(chatroom_id),
    message     text not null ,
    send_date   timestamp
);