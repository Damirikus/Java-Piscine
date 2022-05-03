
insert into users (login, password) VALUES ('damirikus', '1234');
insert into users (login, password) VALUES ('damirikus1', '2345');
insert into users (login, password) VALUES ('damirikus2', '3456');
insert into users (login, password) VALUES ('damirikus3', '4567');
insert into users (login, password) VALUES ('damirikus4', '5678');
insert into users (login, password) VALUES ('damirikus5', '6789');

insert into rooms (owner, chat_name) VALUES (1, 'chat-1');
insert into rooms (owner, chat_name) VALUES (2, 'chat-2');
insert into rooms (owner, chat_name) VALUES (3, 'chat-3');
insert into rooms (owner, chat_name) VALUES (4, 'chat-4');
insert into rooms (owner, chat_name) VALUES (5, 'chat-5');

insert into messages (author, room, message, send_date) VALUES (1, 2, 'i am a 1', current_timestamp);
insert into messages (author, room, message, send_date) VALUES (1, 2, 'i am a 1', current_timestamp);
insert into messages (author, room, message, send_date) VALUES (2, 3, 'i am a 2', current_timestamp);
insert into messages (author, room, message, send_date) VALUES (3, 4, 'i am a 3', current_timestamp);
insert into messages (author, room, message, send_date) VALUES (1, 5, 'i am a 1', current_timestamp);
insert into messages (author, room, message, send_date) VALUES (5, 2, 'i am a 5', current_timestamp);