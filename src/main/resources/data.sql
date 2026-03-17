INSERT INTO users (user_id, first_name, last_name, email, phone_number, address)
VALUES (1, 'TEST_USER', 'TEST_LAST', 'test@example.com', '1111111111', 'Test Address');

INSERT INTO orders (order_id, order_number, user_id, order_date, order_status, total_amount)
VALUES (1, 'ORD-101', 1, CURRENT_TIMESTAMP, 'PLACED', 49.99);

INSERT INTO products (product_id, name, brand, category, description)
VALUES (1, 'Glass Bottle', 'SimpleBrand', 'Kitchen', 'Small glass bottle');

INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price)
VALUES (1, 1, 1, 2, 24.995);