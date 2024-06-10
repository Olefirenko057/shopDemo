-- changeset artemii:create_table_users
CREATE TABLE users (
userId INT AUTO_INCREMENT NOT NULL,
name VARCHAR(255) NULL,
email VARCHAR(255) NULL,
phoneNumber VARCHAR(255) NULL,
passwordHash VARCHAR(255) NULL,
role ENUM ('CLIENT','ADMINISTRATOR') NULL,
CONSTRAINT PK_USERS PRIMARY KEY (userId)
);

-- changeset artemii:create_table_carts
CREATE TABLE carts (
cartId INT AUTO_INCREMENT NOT NULL,
userId INT NULL,
CONSTRAINT PK_CARTS PRIMARY KEY (cartId)
);

-- changeset artemii:create_table_cartitems
CREATE TABLE cartItems (
cartItemId INT AUTO_INCREMENT NOT NULL,
cartId INT NULL,
productId INT NULL,
quantity INT NULL,
CONSTRAINT PK_CARTITEMS PRIMARY KEY (cartItemId)
);

-- changeset artemii:create_table_favorites
CREATE TABLE favorites (
favoriteId INT AUTO_INCREMENT NOT NULL,
userId INT NULL,
productId INT NULL,
CONSTRAINT PK_FAVORITES PRIMARY KEY (favoriteId)
);

-- changeset artemii:create_table_orders
CREATE TABLE orders (
orderId INT AUTO_INCREMENT NOT NULL,
userId INT NULL,
createdAt datetime NULL,
deliveryAddress VARCHAR(255) NULL,
contactPhone VARCHAR(255) NULL,
deliveryMethod VARCHAR(255) NULL,
status ENUM('ORDERED', 'PAID', 'CONFIRMED', 'SENT_TO_WAREHOUSE', 'READY_TO_SHIP', 'SHIPPED_OUT') NULL,
updatedAt datetime NULL,
CONSTRAINT PK_ORDERS PRIMARY KEY(orderId)
);

-- changeset artemii:create_table_orderItems
CREATE TABLE orderItems (
orderItemId INT AUTO_INCREMENT NOT NULL,
orderId INT NULL,
productId INT NULL,
quantity INT NULL,
priceAtPurchase DECIMAL NULL
);


-- changeset artemii:foreign_key_carts_users
ALTER TABLE carts ADD CONSTRAINT foreign_key_carts_users FOREIGN KEY (userId) REFERENCES users (userId) ON UPDATE RESTRICT ON DELETE RESTRICT;

-- changeset artemii:foreign_key_cartitems_carts
ALTER TABLE cartItems ADD CONSTRAINT foreign_key_cartitems_carts FOREIGN KEY (cartId) REFERENCES carts (cartId) ON UPDATE RESTRICT ON DELETE RESTRICT;

-- changeset artemii:foreign_key_favorites_users
ALTER TABLE favorites ADD CONSTRAINT foreign_key_favorites_users FOREIGN KEY (userId) REFERENCES users (userId)  ON UPDATE RESTRICT ON DELETE RESTRICT;

-- changeset artemii:foreign_key_orders_users
ALTER TABLE orders ADD CONSTRAINT foreign_key_orders_users FOREIGN KEY (userId) REFERENCES users (userId)  ON UPDATE RESTRICT ON DELETE RESTRICT;

-- changeset artemii:foreign_key_orderitems_orders
ALTER TABLE orderItems ADD CONSTRAINT foreign_key_orderitems_orders FOREIGN KEY (orderId) REFERENCES orders (orderId)  ON UPDATE RESTRICT ON DELETE RESTRICT;
