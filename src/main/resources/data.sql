insert into roles(rolename)values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password)values ('erin', 'password321');

insert into user_roles(username, rolename)values('erin', 'ROLE_USER');

insert into profile (id, firstname, lastname, address, phone_number, email, profile_photo, username)values (10, 'Erin', 'Dogan', 'Street 123', '0623456789', 'erin@gmail.com', 'photo', 'erin');

insert into menus(id, name)values ( 10, 'mocktails');

insert into drinks (name, price, ingredients, menu_id) values ('aardbei mocktail', 12.99, '5 aardbeien, rietsuiker, 1 glas water', 10);

insert into non_alcoholic_drinks (id) values (1);

insert into reservations (id, reservation_name, reservation_time, table_number, guests, phone_number, username)values  (10, 'Erin', '2024-12-17T18:00:00', 5, 8, '0623456789', 'erin');

