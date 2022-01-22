DELETE from vote;
DELETE from user_roles;
DELETE from meals;
DELETE from users;
DELETE from restaurant;
INSERT INTO users (ID, email, password)
VALUES (1, 'user@user.com', 'user'),
       (2, 'admin@admin.com', 'admin'),
       (3, 'superadmin@admin.com', 'superadmin');
INSERT INTO user_roles(user_id, role)
VALUES (1, 'USER'),
       (2, 'ADMIN'),
       (2, 'USER'),
       (3,'ADMIN'),
       (3,'USER');
INSERT INTO restaurant (ID, NAME, ADDRESS)
VALUES (1, 'Ресторан', 'Адрес Ресторана'),
       (2, 'Кафе', 'Адрес Кафе');
INSERT INTO meals (ID, NAME, PRICE, RESTAURANT_ID)
VALUES (1, 'Суп', 200, 1),
       (2, 'Паста', 300, 1),
       (3, 'Кофе', 100, 1),
       (4, 'Борщ', 250, 2),
       (5, 'Курица с грибами', 250, 2),
       (6, 'Чай', 80, 2);
INSERT INTO vote (ID, RESTAURANT_ID, USER_ID, DATE_VOTE)
VALUES (1, 1, 2, '2021-12-29'),
       (2, 2, 1, '2021-12-29');
DELETE from vote;
DELETE from user_roles;
DELETE from meals;
DELETE from users;
DELETE from restaurant;
INSERT INTO users (ID, email, password)
VALUES (1, 'user@user.com', 'user'),
       (2, 'admin@admin.com', 'admin'),
       (3, 'superadmin@admin.com', 'superadmin');
INSERT INTO user_roles(user_id, role)
VALUES (1, 'USER'),
       (2, 'ADMIN'),
       (2, 'USER'),
       (3,'ADMIN'),
       (3,'USER');
INSERT INTO restaurant (ID, NAME, ADDRESS)
VALUES (1, 'Ресторан', 'Адрес Ресторана'),
       (2, 'Кафе', 'Адрес Кафе');
INSERT INTO meals (ID, NAME, PRICE, RESTAURANT_ID)
VALUES (1, 'Суп', 200, 1),
       (2, 'Паста', 300, 1),
       (3, 'Кофе', 100, 1),
       (4, 'Борщ', 250, 2),
       (5, 'Курица с грибами', 250, 2),
       (6, 'Чай', 80, 2);
INSERT INTO vote (ID, RESTAURANT_ID, USER_ID, DATE_VOTE)
VALUES (1, 1, 2, '2021-12-29'),
       (2, 2, 1, '2021-12-29');