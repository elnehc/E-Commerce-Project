INSERT INTO users (user_id, first_name, last_name, email, phone_number, address)
VALUES
    (1, 'Lunet', 'Varo', 'lunet.varo@example.com', '1111111111', '101 Ardin St'),
    (2, 'Mirel', 'Sovan', 'mirel.sovan@example.com', '2222222222', '202 Belmo Ave'),
    (3, 'Tavik', 'Relo', 'tavik.relo@example.com', '3333333333', '303 Cindar Rd'),
    (4, 'Neris', 'Palo', 'neris.palo@example.com', '4444444444', '404 Dovan Ln'),
    (5, 'Selto', 'Quin', 'selto.quin@example.com', '5555555555', '505 Elric Dr');

INSERT INTO orders (order_id, order_number, user_id, order_date, order_status, total_amount)
VALUES
    (1, 'ORD-01', 1, '2026-01-08', 'PLACED', 67.48),
    (2, 'ORD-02', 1, '2026-02-14', 'CANCELLED', 18.25),
    (3, 'ORD-03', 2, '2026-03-03', 'DELIVERED', 54.10),
    (4, 'ORD-04', 3, '2026-03-19', 'SHIPPED', 41.60),
    (5, 'ORD-05', 4, '2026-04-07', 'RETURNED', 92.75),
    (6, 'ORD-06', 5, '2026-03-27', 'PLACED', 63.35);

INSERT INTO products (product_id, name, brand, category, description)
VALUES
    (1, 'Velon Disk', 'Arctis', 'Type-A', 'Compact velon disk'),
    (2, 'Mira Stem', 'Arctis', 'Type-B', 'Segmented mira stem'),
    (3, 'Talon Grid', 'Brevik', 'Type-C', 'Layered talon grid'),
    (4, 'Orin Block', 'Celmor', 'Type-A', 'Dense orin block'),
    (5, 'Pavo Ring', 'Dramel', 'Type-D', 'Smooth pavo ring'),
    (6, 'Nexa Fold', 'Eldra', 'Type-B', 'Flexible nexa fold'),
    (7, 'Rilo Pad', 'Fenton', 'Type-C', 'Soft rilo pad'),
    (8, 'Zerin Clip', 'Arctis', 'Type-D', 'Locking zerin clip');

INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price)
VALUES
    (1, 1, 1, 1, 22.49),
    (2, 1, 2, 3, 14.99),

    (3, 2, 5, 1, 18.25),

    (4, 3, 3, 2, 17.30),
    (5, 3, 8, 1, 19.50),

    (6, 4, 6, 1, 16.80),
    (7, 4, 7, 2, 12.40),

    (8, 5, 4, 1, 47.75),
    (9, 5, 5, 1, 45.00),

    (10, 6, 2, 1, 14.99),
    (11, 6, 3, 1, 17.30),
    (12, 6, 8, 1, 19.50),
    (13, 6, 7, 1, 11.56);