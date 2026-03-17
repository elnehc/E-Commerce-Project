INSERT INTO users (user_id, first_name, last_name, email, phone_number, address)
VALUES (1, 'TEST_USER', 'TEST_LAST', 'test@example.com', '1111111111', 'Test Address');

INSERT INTO orders (order_id, order_number, user_id, order_date, order_status, total_amount)
VALUES (1, 'ORD-01', 1, '2026-01-01', 'PLACED', 49.99),
       (2, 'ORD-02', 1, '2026-04-02', 'CANCELLED', 19.99);

INSERT INTO products (product_id, name, brand, category, description)
VALUES (1, 'Glass Bottle', 'SimpleBrand', 'Kitchen', 'Small glass bottle'),
       (2, 'Wooden Spoon', 'SimpleBrand', 'Kitchen', 'Sturdy wooden spoon');

INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price)
VALUES (1, 1, 1, 2, 24.995),
       (2, 1, 2, 1, 19.99);