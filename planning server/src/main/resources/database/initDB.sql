

  create table if not exists users_roles
  (
      users_id bigint not null references users,
      roles_id bigint not null references roles,
      primary key (users_id, roles_id)
  );

  create table if not exists positions(
    position_id bigint AUTO_INCREMENT primary key,
    title varchar(250) not null
  );

  create table if not exists statuses(
    status_id bigint AUTO_INCREMENT primary key,
    title varchar(250) not null
  );

  create table if not exists countries(
    country_id bigint AUTO_INCREMENT primary key,
    title varchar(250) not null
  );

  create table if not exists categories(
    category_id bigint AUTO_INCREMENT primary key,
    title varchar(250) not null
  );

    create table if not exists roles
    (
        id bigint AUTO_INCREMENT primary key,
        name varchar(255)
    );

    create table if not exists users(
        id bigint AUTO_INCREMENT primary key,
        password varchar(255),
        username varchar(255),
        position_id bigint not null,
        surname varchar(250) Not Null,
        name varchar(250) Not Null,
        patronymic varchar(250),
        constraint users_positions
        FOREIGN KEY (position_id) REFERENCES positions (position_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
    );

  create table if not exists shifts(
    shift_id bigint AUTO_INCREMENT primary key,
    user_id bigint not null,
    open_date datetime  not null,
    close_date datetime not null,
    constraint shifts_users
    FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  );

  Create table if not exists applications(
    application_id bigint AUTO_INCREMENT primary key,
    user_id_open bigint not null,
    description text Not Null,
    open_date date Not Null,
    close_date date,
    user_id_close bigint,
    status_id bigint,
    comment text,
    constraint applications_users_open
    FOREIGN KEY (user_id_open) REFERENCES users(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    constraint applications_statuses
    FOREIGN KEY (status_id) REFERENCES statuses(status_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    constraint applications_users_close
    FOREIGN KEY (user_id_close) REFERENCES users(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  );

  create table if not exists suppliers(
    supplier_id bigint AUTO_INCREMENT primary key,
    country_id bigint not null,
    unp int Not Null,
    title varchar(250) Not Null,
    phone varchar (20) Not Null,
    constraint suppliers_countries
    FOREIGN KEY (country_id) REFERENCES countries (country_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  );

  create table if not exists supplies(
    supply_id bigint AUTO_INCREMENT primary key,
    supplier_id bigint not null,
    supply_date date not null,
    constraint supplies_suppliers
    FOREIGN KEY (supplier_id) REFERENCES suppliers (supplier_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  );

  create table if not exists products(
    product_id bigint AUTO_INCREMENT primary key,

    title varchar(250) Not Null,
    category_id bigint not null,
    product_count int not null,
    price double Not Null,
    batch_number int not null,
    best_before_date date not null,
    weight double not null,
    constraint products_categories
    FOREIGN KEY (category_id) REFERENCES categories (category_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  );

  CREATE TABLE IF NOT EXISTS supplies_products(
    id bigint AUTO_INCREMENT primary key,
    supply_id bigint NOT NULL,
    product_id bigint NOT NULL,
    CONSTRAINT supplies_products0
      FOREIGN KEY (supply_id)
      REFERENCES supplies (supply_id)
      ON DELETE CASCADE
      ON UPDATE CASCADE,
    CONSTRAINT supplies_products1
      FOREIGN KEY (product_id)
      REFERENCES products (product_id)
      ON DELETE CASCADE
      ON UPDATE CASCADE);

  Create table if not exists couriers(
    courier_id bigint AUTO_INCREMENT primary key,
    surname varchar(250) Not Null,
    name varchar(250) Not Null,
    patronymic varchar(250) not null,
    passport_number varchar(250) Not Null,
    phone varchar (20) Not Null,
    organization_title varchar(200) Not Null,
    organization_phone varchar (20) Not Null
  );


  Create table if not exists deliveries(
    delivery_id bigint AUTO_INCREMENT primary key,
    courier_id bigint not null,
    city varchar(50) Not Null,
    street varchar(50) Not Null,
    house int Not Null,
    client_name varchar(70) Not Null,
    client_phone varchar (20) Not Null,
    delivery_date date Not Null,
    constraint deliveries_couriers
    FOREIGN KEY (courier_id) REFERENCES couriers (courier_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  );


  Create table if not exists orders(
    order_id bigint AUTO_INCREMENT primary key,
    order_date date Not Null,
    product_id bigint not null,
    delivery_id bigint,
    order_count int not null,
    constraint orders_deliveries
        FOREIGN KEY (delivery_id) REFERENCES deliveries (delivery_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    constraint orders_products
    FOREIGN KEY (product_id) REFERENCES products (product_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
  );
