insert into roles(rolename)values ('ROLE_USER'), ('ROLE_ADMIN');

insert into users (username, password)values ('erin', 'password321');

insert into profile (id, firstname, lastname, address, phone_number, email, profile_photo, username)values (1, 'Erin', 'Dogan', 'Street 123', '0623456789', 'erin@gmail.com', 'photo', 'erin');

insert into menus(id, name)values ( 1, 'mocktails');

insert into drinks (id, name, price, ingredients, alcohol, menu_id)values (1, 'aardbei mocktail', 12.99, '5 aardbeien, rietsuiker, 1 glas water', false, 1);

insert into reservations (id, reservation_name, reservation_time, table_number, guests, phone_number, username)values  (20, 'Erin', '2024-12-17T18:00:00', 5, 8, '0623456789', 'erin');

