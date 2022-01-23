DELETE from vote;
DELETE from user_roles;
DELETE from DISH;
DELETE FROM MENU;
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

INSERT INTO DISH (ID, NAME, PRICE,MENU_ID,DATE)
VALUES (1, 'Суп', 200, 1,now()),
       (2, 'Паста', 300, 1,now()),
       (3, 'Кофе', 100, 1,now()),
       (4, 'Борщ', 250, 2,now()),
       (5, 'Курица с грибами', 250, 2,now()),
       (6, 'Чай', 80, 2,now());

INSERT INTO MENU (ID,RESTAURANT_ID, DISH_ID, DATE)
VALUES (1,1,1,now()),
       (2,1,2,now()),
       (3,1,3,now()),
       (4,2,4,now()),
       (5,2,5,now()),
       (6,2,6,now());

INSERT INTO vote (ID, RESTAURANT_ID, USER_ID, DATE_VOTE)
VALUES (1, 1, 2, '2021-12-29'),
       (2, 2, 1, '2021-12-29');