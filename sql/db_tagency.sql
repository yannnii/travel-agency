/*users*/
CREATE TABLE users
(
    id_user        INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    login          VARCHAR(16) UNIQUE                  NOT NULL,
    password       CHAR(89)                            NOT NULL,
    email          VARCHAR(64)                         NOT NULL,
    role           ENUM ('admin', 'moderator', 'user') NOT NULL DEFAULT 'user',
    status         boolean                             NOT NULL DEFAULT true,
    email_approved boolean                             NOT NULL DEFAULT false
);

INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Admin1',
        'lYXtVOqKBU/I6cKkJks4emYZT9krdwSU50PTvFxrsZc=$HZx8n/SWCpKdsF9Mhxvw+1ABnIFu0dbT3EVvfzA68AU=', 'admin1@mail.trag',
        'admin', true, true);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Admin2',
        'lYXtVOqKBU/I6cKkJks4emYZT9krdwSU50PTvFxrsZc=$HZx8n/SWCpKdsF9Mhxvw+1ABnIFu0dbT3EVvfzA68AU=', 'Admin2@mail.trag',
        'admin', false, false);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('User1',
        'lYXtVOqKBU/I6cKkJks4emYZT9krdwSU50PTvFxrsZc=$HZx8n/SWCpKdsF9Mhxvw+1ABnIFu0dbT3EVvfzA68AU=',
        'Raccoon10@mail.trag',
        'user', true, false);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('User2',
        'lYXtVOqKBU/I6cKkJks4emYZT9krdwSU50PTvFxrsZc=$HZx8n/SWCpKdsF9Mhxvw+1ABnIFu0dbT3EVvfzA68AU=',
        'Snake222@mail.trag',
        'user', false, false);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('User3',
        'lYXtVOqKBU/I6cKkJks4emYZT9krdwSU50PTvFxrsZc=$HZx8n/SWCpKdsF9Mhxvw+1ABnIFu0dbT3EVvfzA68AU=',
        'Hedgehog32@mail.trag',
        'user', true, true);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Moderator1',
        'lYXtVOqKBU/I6cKkJks4emYZT9krdwSU50PTvFxrsZc=$HZx8n/SWCpKdsF9Mhxvw+1ABnIFu0dbT3EVvfzA68AU=',
        'Sprat32@mail.trag',
        'moderator', true, true);
INSERT INTO users(login, password, email, role, status, email_approved)
VALUES ('Moderator2',
        'lYXtVOqKBU/I6cKkJks4emYZT9krdwSU50PTvFxrsZc=$HZx8n/SWCpKdsF9Mhxvw+1ABnIFu0dbT3EVvfzA68AU=',
        'Woodpecker0@mail.trag',
        'moderator', false, false);

/* tours*/
create table tours
(
    id_tour          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    tour_purpose     ENUM ('rest', 'excursion','shopping' )   NOT NULL,
    country          VARCHAR(64)                              NOT NULL,
    hotel_name       VARCHAR(64)                              NOT NULL,
    hotel_stars      ENUM ('hostel', 'three', 'four', 'five') NOT NULL,
    transport        ENUM ('bus','airplane')                  NOT NULL,
    date_start       LONG                                     NOT NULL,
    quantity_of_days SMALLINT(3) UNSIGNED                     NOT NULL,
    price            INT(5) UNSIGNED                          NOT NULL,
    quantity_tours   TINYINT(2) UNSIGNED                      NOT NULL,
    description      TEXT                                     NOT NULL,
    image_path       VARCHAR(64)                              NOT NULL DEFAULT 'default.jpg',
    discount         TINYINT(2) UNSIGNED
);

INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('rest', 'Belarus', 'Zvezda', 'three', 'bus', 1827648000, 14, 950, 17, 'belarusrest',
        'belOne.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path, discount)
VALUES ('excursion', 'Belarus', 'Nyasvizh', 'four', 'bus', 1627648000, 7, 450, 30, 'belarusexcursion',
        'belTwo.jpg', 10);
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('shopping', 'Belarus', 'Cosmos', 'hostel', 'bus', 1727648000, 2, 300, 10, 'belarusshopping',
        'belThree.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path, discount)
VALUES ('rest', 'Chile', 'Remota', 'five', 'airplane', 1927648000, 16, 4500, 21, 'chilerest',
        'chileThree.jpg', 7);
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('excursion', 'Chile', 'Takarua Lodge', 'hostel', 'airplane', 1670648000, 10, 3200, 14, 'chileexcursion',
        'chileOne.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path, discount)
VALUES ('shopping', 'Chile', 'UGO Hotel', 'four', 'airplane', 1740648000, 6, 2700, 7, 'chileshopping',
        'chileTwo.jpg', 5);
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path, discount)
VALUES ('rest', 'Chile', 'Remota', 'five', 'airplane', 21927648000, 16, 45000, 21, 'chilerest',
        'default.jpg', 7);
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path)
VALUES ('excursion', 'Chile', 'Takarua Lodge', 'hostel', 'airplane', 21670648000, 10, 32000, 14, 'chileexcursion',
        'default.jpg');
INSERT INTO tours(tour_purpose, country, hotel_name, hotel_stars, transport, date_start, quantity_of_days,
                  price, quantity_tours, description, image_path, discount)
VALUES ('shopping', 'Chile', 'UGO Hotel', 'four', 'airplane', 21740648000, 6, 27000, 7, 'chileshopping',
        'default.jpg', 5);

/*one user - many passports, into order*/
create table passport
(
    id_passport     INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk      INT UNSIGNED NOT NULL,
    surname         VARCHAR(64)  NOT NULL,
    name            VARCHAR(64)  NOT NULL,
    birth_date      LONG         NOT NULL,
    passport_number VARCHAR(64)  NOT NULL,
    passport_image  VARCHAR(64),
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user)
);

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number, passport_image)
VALUES (3, 'Ivanov', 'Ivan', 1600000000, 'HE8975899', 'defaultPassport.pdf');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number, passport_image)
VALUES (3, 'Ivanova', 'Olga', 1600100000, 'LG1564689', 'defaultPassport.pdf');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number, passport_image)
VALUES (3, 'Ivanov', 'Igor', 1600200000, 'IK0009998', 'defaultPassport.pdf');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number, passport_image)
VALUES (4, 'Merier', 'Luis', 1600300000, 'JU8484949', 'defaultPassport.pdf');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number, passport_image)
VALUES (4, 'Buijuer', 'Anette', 1600400000, 'EW8798546', 'defaultPassport.pdf');

INSERT INTO passport (id_user_fk, surname, name, birth_date, passport_number, passport_image)
VALUES (5, 'Norris', 'Chuck', 1600500000, 'US9999888', 'defaultPassport.pdf');

/*into order*/
create table travel_docs
(
    id_travel_docs INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    voucher        VARCHAR(64),
    insurance      VARCHAR(64),
    ticket         VARCHAR(64)
);

INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('defaultVoucher.pdf', 'defaultInsurance.jpg', 'defaultTicket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('defaultVoucher.pdf', 'defaultInsurance.jpg', 'defaultTicket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('defaultVoucher.pdf', 'defaultInsurance.jpg', 'defaultTicket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('defaultVoucher.pdf', 'defaultInsurance.jpg', 'defaultTicket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('defaultVoucher.pdf', 'defaultInsurance.jpg', 'defaultTicket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('defaultVoucher.pdf', 'defaultInsurance.jpg', 'defaultTicket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('defaultVoucher.pdf', 'defaultInsurance.jpg', 'defaultTicket.pdf');
INSERT INTO travel_docs (voucher, insurance, ticket)
VALUES ('defaultVoucher.pdf', 'defaultInsurance.jpg', 'defaultTicket.pdf');

/*one user - one sheet*/
create table sheet
(
    id_sheet          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_user_fk        INT UNSIGNED UNIQUE NOT NULL,
    sheet_sum         INT(7) UNSIGNED     NOT NULL DEFAULT 0,
    customer_discount ENUM ('0','5','7','10','15') DEFAULT '0',
    FOREIGN KEY (id_user_fk) REFERENCES users (id_user)
);

ALTER TABLE sheet
    AUTO_INCREMENT = 2000;

INSERT INTO sheet(id_user_fk, sheet_sum, customer_discount)
VALUES (3, 500000, '10');
INSERT INTO sheet(id_user_fk, sheet_sum, customer_discount)
VALUES (4, 1500000, '0');
INSERT INTO sheet(id_user_fk, sheet_sum, customer_discount)
VALUES (5, 50000000, '15');

/*one operation - many sheets*/
create table sheet_operation
(
    id_operation      INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_sheet_fk       INT UNSIGNED NOT NULL,
    operation_sum     INT(7)       NOT NULL DEFAULT 0,
    operation_purpose VARCHAR(64)  NOT NULL DEFAULT 'default Error',
    FOREIGN KEY (id_sheet_fk) REFERENCES sheet (id_sheet)
);

INSERT INTO sheet_operation (id_sheet_fk, operation_sum, operation_purpose)
VALUES (2002, 20000, 'add one payment');
INSERT INTO sheet_operation (id_sheet_fk, operation_sum, operation_purpose)
VALUES (2002, -200000, 'payment for tour');
INSERT INTO sheet_operation (id_sheet_fk, operation_sum, operation_purpose)
VALUES (2002, 100000, 'add paycard sum');
INSERT INTO sheet_operation (id_sheet_fk, operation_sum, operation_purpose)
VALUES (2002, -150000, 'order payment');

/*one order - many tours - many passports - one travel_documents*/
create table orders
(
    id_order          INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    id_tour_fk        INT UNSIGNED                                                        NOT NULL,
    id_passport_fk    INT UNSIGNED                                                        NOT NULL,
    id_travel_docs_fk INT UNSIGNED UNIQUE                                                 NOT NULL,
    date_order        LONG                                                                NOT NULL,
    state             ENUM ('new','confirmed','paid','added_docs','finished', 'declined') NOT NULL DEFAULT 'new',
    comment           VARCHAR(64),
    FOREIGN KEY (id_tour_fk) REFERENCES tours (id_tour),
    FOREIGN KEY (id_passport_fk) REFERENCES passport (id_passport),
    FOREIGN KEY (id_travel_docs_fk) REFERENCES travel_docs (id_travel_docs)
);

INSERT INTO orders(id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order, state)
VALUES (6, 6, 1, 1627648000, 'new');
INSERT INTO orders(id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order, state)
VALUES (5, 6, 2, 1627648000, 'confirmed');
INSERT INTO orders(id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order, state)
VALUES (4, 6, 3, 1627648000, 'paid');
INSERT INTO orders(id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order, state)
VALUES (3, 6, 4, 1627648000, 'added_docs');
INSERT INTO orders(id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order, state)
VALUES (2, 6, 5, 1627648000, 'finished');
INSERT INTO orders(id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order, state)
VALUES (1, 6, 6, 1627648000, 'paid');
INSERT INTO orders(id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order, state)
VALUES (1, 6, 7, 2027648000, 'declined');
INSERT INTO orders(id_tour_fk, id_passport_fk, id_travel_docs_fk, date_order, state)
VALUES (1, 6, 8, 1927648000, 'new');

/*card to replenish sheet sum*/
create table paycards
(
    id_paycard    INT UNSIGNED UNIQUE AUTO_INCREMENT PRIMARY KEY,
    card_number   INT(7) UNSIGNED UNIQUE NOT NULL,
    card_sum      INT UNSIGNED           NOT NULL,
    card_quantity INT UNSIGNED           NOT NULL
);

INSERT INTO paycards(card_number, card_sum, card_quantity)
VALUES (1111111, 10000, 30);
INSERT INTO paycards(card_number, card_sum, card_quantity)
VALUES (5000000, 50000, 20);
INSERT INTO paycards(card_number, card_sum, card_quantity)
VALUES (7777777, 100000, 10);
INSERT INTO paycards(card_number, card_sum, card_quantity)
VALUES (3339333, 300000, 5);