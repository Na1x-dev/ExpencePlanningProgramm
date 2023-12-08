create table if not EXISTS managements (
  management_id serial primary key,
  management_name text
);

create table if not EXISTS budget (
  budget_id serial primary key,
    management_id INTEGER REFERENCES managements(management_id),
    budget_category1 float,
    budget_category2 float,
    budget_category3 float,
    final_budget float
);

create table if not EXISTS departments (
  department_id serial primary key,
    department_name text,
    management_id INTEGER REFERENCES managements (management_id)
);

create table if not EXISTS positions (
  position_id serial primary key,
    position_name text
);

create table if not EXISTS roles (
  role_id serial primary key,
    role_name text
);

create table if not EXISTS users (
  user_id serial primary key,
    last_name text,
    first_name text,
    patronymic text,
    user_name varchar(30) UNIQUE,
    password text,
    position_id INTEGER REFERENCES positions(position_id),
    role_id INTEGER REFERENCES roles(role_id),
    department_id INTEGER REFERENCES departments(department_id)
);

create table if not EXISTS statuses (
  status_id serial primary key,
  status_name text
);

create table if not EXISTS categories (
  category_id serial primary key,
  category_name text
);

create table if not EXISTS appeals (
  appeal_id serial primary key,
  registration_date TIMESTAMP,
    status_id INTEGER REFERENCES statuses(status_id),
    user_id integer REFERENCES users(user_id),
      appeal_text text,
    closing_date TIMESTAMP,
    closing_user_id INTEGER REFERENCES users(user_id),
    COMMENT text
);
create table if not EXISTS applications (
  application_id serial primary key,
  application_date TIMESTAMP,
    status_id INTEGER REFERENCES statuses(status_id),
    create_user_id integer REFERENCES users(user_id),
    customer_user_id integer REFERENCES users(user_id),
     appeal_id INTEGER REFERENCES appeals(appeal_id),
    category_id INTEGER REFERENCES categories(category_id),
    product_name text,
    product_characteristic text,
    price_for_one float,
    amount INTEGER,
    application_comment text,
    final_price float,
    closing_date TIMESTAMP,
    closing_user_id INTEGER REFERENCES users(user_id),
    COMMENT text
);

create table if not EXISTS orders (
  order_id serial primary key,
  order_date TIMESTAMP,
    status_id INTEGER REFERENCES statuses(status_id),
    create_user_id integer REFERENCES users(user_id),
    application_id INTEGER REFERENCES applications(application_id),
      procurement_organization text,
    unp integer,
      contact_person text,
    contact_number text,
      vat integer,
      price_with_vat float
);

create table if not EXISTS procurement_archive (
  procurement_id serial primary key,
  procurement_date TIMESTAMP,
    status_id INTEGER REFERENCES statuses(status_id),
    user_id integer REFERENCES users(user_id),
    order_id INTEGER REFERENCES orders(order_id),
      comment text
);