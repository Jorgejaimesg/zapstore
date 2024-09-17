DROP DATABASE zap_store;
CREATE DATABASE IF NOT EXISTS zap_store;
USE zap_store;

CREATE TABLE country (
    id VARCHAR(5) PRIMARY KEY NOT NULL,
    name VARCHAR(100)
);

CREATE TABLE region (
    id VARCHAR(5) PRIMARY KEY NOT NULL,
    name VARCHAR(100),
    country_id VARCHAR(5),
    FOREIGN KEY (country_id) REFERENCES country(id)
);

CREATE TABLE city (
    id VARCHAR(5) PRIMARY KEY NOT NULL,
    name VARCHAR(100),
    region_id VARCHAR(5),
    FOREIGN KEY (region_id) REFERENCES region(id)
);

CREATE TABLE customer_type(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100)
);
CREATE TABLE supplier_type(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100)
);

CREATE TABLE id_type(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100)
);
-- TABLA: CLIENTE
CREATE TABLE customer (
    id VARCHAR(100) PRIMARY KEY NOT NULL,
    customer_type INT,
    id_type INT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100),
    FOREIGN KEY (id_type) REFERENCES id_type(id),
    FOREIGN KEY (customer_type) REFERENCES customer_type(id),
    CHECK (email LIKE '%_@__%.__%')
);

-- TABLA NUMERO DE TELEFONOS
CREATE TABLE customer_phone (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    customer_id VARCHAR(100),
    phone_number BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- TABLA PROVEEDORES
CREATE TABLE supplier (
    nit VARCHAR(100) PRIMARY KEY NOT NULL,
    supplier_type INT,
    name VARCHAR(100),
    email VARCHAR(100),
    city_id VARCHAR(5),
    FOREIGN KEY (supplier_type) REFERENCES supplier_type(id),
    FOREIGN KEY (city_id) REFERENCES city(id),
    CHECK (email LIKE '%_@__%.__%')
);

CREATE TABLE customer_address(
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ZipCode INT,
    details VARCHAR(200),
    city_id VARCHAR(5),
    customer_id VARCHAR(100),
    FOREIGN KEY (city_id) REFERENCES city(id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

-- TABLA NUMEROS DE PROVEEDORES
CREATE TABLE supplier_phone (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    supplier_id VARCHAR(100),
    phone_number BIGINT,
    FOREIGN KEY (supplier_id) REFERENCES supplier(nit)
);

-- TABLA DE CATEGORIAS
CREATE TABLE category (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100)
);

-- TABLA DE MARCAS
CREATE TABLE brand (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100)
);

-- TABLA DE PRODUCTOS
CREATE TABLE product (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    category_id INT,
    reference VARCHAR(100),
    stock INT,
    stock_min INT,
    name VARCHAR(100),
    brand_id INT,
    sale_price FLOAT,
    FOREIGN KEY (category_id) REFERENCES category(id),
    FOREIGN KEY (brand_id) REFERENCES brand(id),
    CHECK (stock >= 0),
    CHECK (stock_min >= 0),
    CHECK (sale_price >= 0)
);

-- TABLA PROVEEDOR PRODUCTO
CREATE TABLE supplier_product(
    id_supplier VARCHAR(100),
    id_product INT,
    FOREIGN KEY (id_supplier) REFERENCES supplier(nit),
    FOREIGN KEY (id_product) REFERENCES product(id),
    PRIMARY KEY (id_product, id_supplier)

);

-- TABLA METODO DE PAGOS
CREATE TABLE payment_method (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100)
);

-- TABLAS ESTADOS

CREATE TABLE status_sale (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100)
);

CREATE TABLE status_order (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    name VARCHAR(100)
);
-- TABLA VENTAS
CREATE TABLE sale (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    total_price FLOAT,
    discount_amount FLOAT,
    discount_percent FLOAT,
    customer_id VARCHAR(100),
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method_id INT,
    status_id INT,
    FOREIGN KEY (customer_id) REFERENCES customer(id),
    FOREIGN KEY (status_id) REFERENCES status_sale(id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_method(id),
    CHECK (discount_amount >= 0),
    CHECK (discount_percent BETWEEN 0 AND 100),
    CHECK (total_price >= 0)
);

-- TABLA ORDENES
CREATE TABLE supplier_order (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    total_price FLOAT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_method_id INT,
    status_id INT,
    FOREIGN KEY (status_id) REFERENCES status_order(id),
    FOREIGN KEY (payment_method_id) REFERENCES payment_method(id),
    CHECK (total_price >= 0)
);

-- TABLA DETALLES DE ORDENES
CREATE TABLE order_detail (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    product_id INT,
    quantity INT,
    purchase_price FLOAT,
    order_id INT,
    sub_total FLOAT,
    supplier_id VARCHAR(100),
    FOREIGN KEY (supplier_id) REFERENCES supplier(nit),
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (order_id) REFERENCES supplier_order(id),
    CHECK (quantity > 0),
    CHECK (purchase_price >= 0)
);


-- TABLA DETALLES DE VENTA
CREATE TABLE sale_detail (
    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    product_id INT,
    quantity INT,
    sale_price FLOAT,
    sale_id INT,
    sub_total FLOAT,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (sale_id) REFERENCES sale(id),
    CHECK (quantity > 0),
    CHECK (sale_price >= 0)
);

DELIMITER //

CREATE TRIGGER before_order_detail_insert
BEFORE INSERT ON order_detail
FOR EACH ROW
BEGIN
    SET NEW.sub_total = NEW.quantity * NEW.purchase_price;
END //

CREATE TRIGGER before_order_detail_update
BEFORE UPDATE ON order_detail
FOR EACH ROW
BEGIN
    SET NEW.sub_total = NEW.quantity * NEW.purchase_price;
END //

DELIMITER ;

DELIMITER //

CREATE TRIGGER before_sale_detail_insert
BEFORE INSERT ON sale_detail
FOR EACH ROW
BEGIN
    SET NEW.sub_total = NEW.quantity * NEW.sale_price;
END //

CREATE TRIGGER before_sale_detail_update
BEFORE UPDATE ON sale_detail
FOR EACH ROW
BEGIN
    SET NEW.sub_total = NEW.quantity * NEW.sale_price;
END //

DELIMITER ;


DELIMITER //


