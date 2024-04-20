INSERT INTO tb_client (name, cpf, email, phone) VALUES ('Douglas Fragoso', '12345678900', 'douglas@email.com', '81123456789');
INSERT INTO tb_client (name, cpf, email, phone) VALUES ('Maria Silva', '12345678901', 'maria@email.com', '81123456789');
INSERT INTO tb_client (name, cpf, email, phone) VALUES ('João Pereira', '12345678902', 'joão@email.com', '81123456789');
INSERT INTO tb_client (name, cpf, email, phone) VALUES ('Ana Fragoso', '12345678903', 'ana@email.com', '81123456789');

INSERT INTO tb_product(name, description, price) VALUES ('Product 1', 'Description 1', 100.00);
INSERT INTO tb_product(name, description, price) VALUES ('Product 2', 'Description 2', 200.00);
INSERT INTO tb_product(name, description, price) VALUES ('Product 3', 'Description 3', 300.00);
INSERT INTO tb_product(name, description, price) VALUES ('Product 4', 'Description 4', 400.00);

INSERT INTO tb_order(client_id, date, total, status) VALUES (1, '2021-06-20T19:53:07Z', 500.00, 2);
INSERT INTO tb_order(client_id, date, total, status) VALUES (2, '2021-06-20T19:53:07Z', 400.00, 1);
INSERT INTO tb_order(client_id, date, total, status) VALUES (3, '2021-06-20T19:53:07Z', 300.00, 3);
INSERT INTO tb_order(client_id, date, total, status) VALUES (4, '2021-06-20T19:53:07Z', 800.00, 4);

INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (1, 1, 2);
INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (1, 3, 1);
INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (2, 2, 2);
INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (3, 3, 1);
INSERT INTO tb_order_item(order_id, product_id, quantity) VALUES (4, 4, 2);


