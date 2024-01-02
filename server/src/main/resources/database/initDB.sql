-- create table if not EXISTS managements (
--   management_id serial primary key,
--   management_name text
-- );
--
-- create table if not EXISTS budget (
--   budget_id serial primary key,
--     management_id INTEGER REFERENCES managements(management_id)   ,
--     budget_category1 float,
--     budget_category2 float,
--     budget_category3 float,
--     final_budget float
-- );
--
-- create table if not EXISTS departments (
--   department_id serial primary key,
--     department_name text,
--     management_id INTEGER REFERENCES managements (management_id) ON DELETE CASCADE
--     );
--
-- create table if not EXISTS positions (
--   position_id serial primary key,
--     position_name text
-- );
--
-- create table if not EXISTS roles (
--   role_id serial primary key,
--     role_name text
-- );
--
-- create table if not EXISTS users (
--   user_id serial primary key,
--     last_name text,
--     first_name text,
--     patronymic text,
--     user_name varchar(20) UNIQUE,
--     password text,
--     position_id INTEGER REFERENCES positions(position_id)   ,
--     role_id INTEGER REFERENCES roles(role_id)   ,
--     department_id INTEGER REFERENCES departments(department_id)
--     );
--
-- create table if not EXISTS statuses (
--   status_id serial primary key,
--   status_name text
-- );
--
-- create table if not EXISTS categories (
--   category_id serial primary key,
--   category_name text
-- );
--
-- create table if not EXISTS appeals (
--   appeal_id serial primary key,
--   registration_date TIMESTAMP,
--     status_id INTEGER REFERENCES statuses(status_id)   ,
--     user_id integer REFERENCES users(user_id)   ,
--       appeal_text text,
--     closing_date TIMESTAMP,
--     closing_user_id INTEGER REFERENCES users(user_id)   ,
--     COMMENT text
-- );
-- create table if not EXISTS applications (
--   application_id serial primary key,
--   application_date TIMESTAMP,
--     status_id INTEGER REFERENCES statuses(status_id)   ,
--     create_user_id integer REFERENCES users(user_id)   ,
--     customer_user_id integer REFERENCES users(user_id)   ,
--      appeal_id INTEGER REFERENCES appeals(appeal_id)   ,
--     category_id INTEGER REFERENCES categories(category_id)    ,
--     product_name text,
--     product_characteristic text,
--     price_for_one float,
--     amount INTEGER,
--     application_comment text,
--     final_price float,
--     closing_date TIMESTAMP,
--     closing_user_id INTEGER REFERENCES users(user_id)    ,
--     COMMENT text
-- );
--
-- create table if not EXISTS orders (
--   order_id serial primary key,
--   order_date TIMESTAMP,
--     status_id INTEGER REFERENCES statuses(status_id)   ,
--     create_user_id integer REFERENCES users(user_id)   ,
--     application_id INTEGER REFERENCES applications(application_id)    ,
--       procurement_organization text,
--     unp integer,
--       contact_person text,
--     contact_number text,
--       vat integer,
--       price_with_vat float
-- );
--
-- create table if not EXISTS procurement_archive (
--   procurement_id serial primary key,
--   procurement_date TIMESTAMP,
--     status_id INTEGER REFERENCES statuses(status_id)    ,
--     user_id integer REFERENCES users(user_id)    ,
--     order_id INTEGER REFERENCES orders(order_id)    ,
--       comment text
-- );

CREATE TABLE IF NOT EXISTS managements (
                                           management_id INT AUTO_INCREMENT PRIMARY KEY,
                                           management_name VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS budget (
                                      budget_id INT AUTO_INCREMENT PRIMARY KEY,
                                      management_id INT,
                                      budget_category1 FLOAT,
                                      budget_category2 FLOAT,
                                      budget_category3 FLOAT,
                                      final_budget FLOAT,
                                      FOREIGN KEY (management_id) REFERENCES managements(management_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS departments (
                                           department_id INT AUTO_INCREMENT PRIMARY KEY,
                                           department_name VARCHAR(255),
    management_id INT,
    FOREIGN KEY (management_id) REFERENCES managements(management_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS positions (
                                         position_id INT AUTO_INCREMENT PRIMARY KEY,
                                         position_name VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS roles (
                                     role_id INT AUTO_INCREMENT PRIMARY KEY,
                                     role_name VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS users (
                                     user_id INT AUTO_INCREMENT PRIMARY KEY,
                                     last_name VARCHAR(255),
    first_name VARCHAR(255),
    patronymic VARCHAR(255),
    user_name VARCHAR(20) UNIQUE,
    password VARCHAR(255),
    position_id INT,
    role_id INT,
    department_id INT,
    FOREIGN KEY (position_id) REFERENCES positions(position_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE,
    FOREIGN KEY (department_id) REFERENCES departments(department_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS statuses (
                                        status_id INT AUTO_INCREMENT PRIMARY KEY,
                                        status_name VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS categories (
                                          category_id INT AUTO_INCREMENT PRIMARY KEY,
                                          category_name VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS appeals (
                                       appeal_id INT AUTO_INCREMENT PRIMARY KEY,
                                       registration_date TIMESTAMP,
                                       status_id INT,
                                       user_id INT,
                                       appeal_text TEXT,
                                       closing_date TIMESTAMP,
                                       closing_user_id INT,
                                       COMMENT TEXT,
                                       FOREIGN KEY (status_id) REFERENCES statuses(status_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (closing_user_id) REFERENCES users(user_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS applications (
                                            application_id INT AUTO_INCREMENT PRIMARY KEY,
                                            application_date TIMESTAMP,
                                            status_id INT,
                                            create_user_id INT,
                                            customer_user_id INT,
                                            appeal_id INT,
                                            category_id INT,
                                            product_name TEXT,
                                            product_characteristic TEXT,
                                            price_for_one FLOAT,
                                            amount INT,
                                            application_comment TEXT,
                                            final_price FLOAT,
                                            closing_date TIMESTAMP,
                                            closing_user_id INT,
                                            COMMENT TEXT,
                                            FOREIGN KEY (status_id) REFERENCES statuses(status_id) ON DELETE CASCADE,
    FOREIGN KEY (create_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (customer_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (appeal_id) REFERENCES appeals(appeal_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE,
    FOREIGN KEY (closing_user_id) REFERENCES users(user_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS orders (
                                      order_id INT AUTO_INCREMENT PRIMARY KEY,
                                      order_date TIMESTAMP,
                                      status_id INT,
                                      create_user_id INT,
                                      application_id INT,
                                      procurement_organization TEXT,
                                      unp INT,
                                      contact_person TEXT,
                                      contact_number TEXT,
                                      vat INT,
                                      price_with_vat FLOAT,
                                      FOREIGN KEY (status_id) REFERENCES statuses(status_id) ON DELETE CASCADE,
    FOREIGN KEY (create_user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (application_id) REFERENCES applications(application_id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS procurement_archive (
                                                   procurement_id INT AUTO_INCREMENT PRIMARY KEY,
                                                   procurement_date TIMESTAMP,
                                                   status_id INT,
                                                   user_id INT,
                                                   order_id INT,
                                                   comment TEXT,
                                                   FOREIGN KEY (status_id) REFERENCES statuses(status_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
    );