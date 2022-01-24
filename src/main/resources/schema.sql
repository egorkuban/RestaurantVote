DROP TABLE user_roles IF EXISTS;
DROP TABLE vote IF EXISTS;
DROP TABLE menu IF EXISTS;
DROP TABLE dish IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE restaurant IF EXISTS;

CREATE TABLE restaurant
(
    id      INTEGER IDENTITY PRIMARY KEY NOT NULL,
    name    VARCHAR(255)                 NOT NULL,
    address VARCHAR(255)                 NOT NULL
);
CREATE UNIQUE INDEX restaurant_unique_index on restaurant (name, address);

CREATE TABLE menu
(
    id            INTEGER IDENTITY PRIMARY KEY NOT NULL,
    restaurant_id BIGINT                       NOT NULL,
    date          DATE      DEFAULT now()      NOT NULL,
    date_create   TIMESTAMP DEFAULT now()      NOT NULL,
    is_actual     boolean                      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE dish
(
    id      INTEGER IDENTITY PRIMARY KEY NOT NULL,
    name    VARCHAR(255)                 NOT NULL,
    price   DECIMAL                      NOT NULL,
    menu_id INTEGER                      NOT NULL,
    FOREIGN KEY (menu_id) REFERENCES menu (id) ON DELETE CASCADE
);


CREATE TABLE users
(
    id       INTEGER IDENTITY PRIMARY KEY NOT NULL,
    email    VARCHAR(255)                 NOT NULL,
    password VARCHAR(255)                 NOT NULL
);
CREATE UNIQUE INDEX user_unique_index on users (email);
CREATE TABLE user_roles
(
    user_id INTEGER NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);
CREATE TABLE vote
(
    id            INTEGER IDENTITY PRIMARY KEY NOT NULL,
    restaurant_id BIGINT                       NOT NULL,
    user_id       BIGINT                       NOT NULL,
    date_vote     DATE DEFAULT now()           NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
)