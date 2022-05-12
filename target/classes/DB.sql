DROP DATABASE IF EXISTS delivery_company;
CREATE DATABASE delivery_company;


USE delivery_company;
CREATE TABLE USERS
(
    id           bigint AUTO_INCREMENT PRIMARY KEY,
    email        VARCHAR(60) NOT NULL UNIQUE,
    phone_number VARCHAR(70) NOT NULL UNIQUE,
    name         VARCHAR(70) NOT NULL,
    surname      VARCHAR(70) NOT NULL,
    login        VARCHAR(30) NOT NULL UNIQUE,
    password     VARCHAR(60) NOT NULL,
    role         VARCHAR(15) NOT NULL
);
CREATE TABLE ORDERS
(
    id                   bigint AUTO_INCREMENT PRIMARY KEY,
    user_id              BIGINT REFERENCES USERS(id),
    cargo_name           VARCHAR(50) NOT NULL,
    cargo_mass           DOUBLE      NOT NULL,
    tariff_id            BIGINT REFERENCES TARIFFS (id) ,
    route_id             BIGINT REFERENCES ROUTES (id) ON DELETE CASCADE,
    delivery_address     VARCHAR(60) NOT NULL,
    order_placement_time DATETIME    NOT NULL,
    pick_up_date         DATE        NOT NULL,
    date_of_arrival      DATE        NOT NULL,
    order_status         VARCHAR(40) not null
);
CREATE TABLE ROUTES
(
    id          bigint AUTO_INCREMENT PRIMARY KEY,
    start       VARCHAR(15),
    destination VARCHAR(15),
    length      INT
);

CREATE TABLE TARIFFS
(
    id                bigint AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50),
    cargo_hold_height DOUBLE,
    cargo_hold_length DOUBLE,
    cargo_hold_width  DOUBLE,
    cargo_mass_cap    INTEGER,
    price_per_km      INTEGER,
    delivery_range    VARCHAR(50)
);

