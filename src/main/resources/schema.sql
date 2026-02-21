CREATE TABLE users (
    user_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    phone_number varchar(20) NOT NULL,
    address varchar(255) NOT NULL
);

CREATE TABLE orders (
    order_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_number varchar(20) NOT NULL UNIQUE,
    user_id int NOT NULL,
    order_date datetime NOT NULL,
    order_status ENUM('PLACED','CANCELLED','SHIPPED','DELIVERED','RETURNED') NOT NULL,
    total_amount decimal(10, 2) NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE products (
    product_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL,
    brand varchar(50) NOT NULL,
    category varchar(50) NOT NULL,
    description text
);

CREATE TABLE order_items (
    order_item_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    order_id int NOT NULL,
    product_id int NOT NULL,
    quantity int NOT NULL,
    unit_price decimal(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

