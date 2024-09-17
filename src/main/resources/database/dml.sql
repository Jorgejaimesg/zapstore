-- INSERTAR PAISES
INSERT INTO country (id, name)
VALUES 
('COL', 'Colombia'),
('USA', 'United States'),
('MEX', 'Mexico');

-- INSERTAR REGIONES
INSERT INTO region (id, name, country_id)
VALUES 
('AND', 'Andina', 'COL'),
('PAC', 'Pacifica', 'COL'),
('TEX', 'Texas', 'USA'),
('CDMX', 'Ciudad de Mexico', 'MEX');

-- INSERTAR CIUDADES
INSERT INTO city (id, name, region_id)
VALUES 
('BOG', 'Bogotá', 'AND'),
('MED', 'Medellín', 'AND'),
('CAL', 'Cali', 'PAC'),
('HOU', 'Houston', 'TEX'),
('MEX', 'Ciudad de Mexico', 'CDMX');

-- INSERTAR TIPOS DE CLIENTE
INSERT INTO customer_type  (name)
VALUES
('natural person'),
('legal entity');

-- INSERTAR TIPOS DE PROVEEDOR
INSERT INTO supplier_type  (name)
VALUES
('common regime'),
('simplified regime');

-- INSERTAR TIPOS DE IDENTIFICACIÓN
INSERT INTO id_type (name)
VALUES
('National ID'), 
('Passport'),         
('Tax ID'),           
("Driver's License");

-- INSERTAR CATEGORÍAS
INSERT INTO category (name) 
VALUES 
('herramientas electricas'),
('equipos de iluminacion'),
('pilas y acumuladores'),
('equipos electricos'),
('maquinaria y herramientas electricas'),
('vigilancia y control');

-- INSERTAR MARCAS
INSERT INTO brand (name) 
VALUES 
('Schneider Electric'),
('Siemens'),
('Legrand'),
('Eaton'),
('ABB'),
('Fanuc'),
('Philips');

-- INSERTAR MÉTODOS DE PAGO
INSERT INTO payment_method (name)
VALUES
('Cash'),
('PayPal'),
('Bank transfer'),
('Credit card'),
('Debit card');

-- INSERTAR PROVEEDORES
INSERT INTO supplier (nit, supplier_type, name, email, city_id)
VALUES 
('1111111111', 1, 'Sodimac', 'contacto@sodimac.com', 'BOG'),
('2222222222', 1, 'Ferretería EPA', 'ventas@epa.com', 'MED'),
('3333333333', 1, 'Cables y Conductores S.A.', 'info@cablesyconductores.com', 'CAL'),
('4444444444', 2, 'Materiales Eléctricos S.A.', 'contacto@materialeselectricos.com', 'HOU'),
('5555555555', 2, 'Distribuidora Electrica Latinoamericana', 'ventas@delatinoamericana.com', 'MEX');


-- INSERTAR NÚMEROS DE TELÉFONOS DE PROVEEDORES
INSERT INTO supplier_phone (supplier_id, phone_number) 
VALUES 
('1111111111', 3216549870),
('2222222222', 3147894560),
('3333333333', 3184567890),
('4444444444', 3114561230),
('5555555555', 3171236540);

-- INSERTAR PRODUCTOS
INSERT INTO product (category_id, reference, stock, stock_min, name, brand_id, sale_price) 
VALUES 
(1, 'SD-1200', 100, 10, 'Taladro Inalámbrico 18V', 1, 150.00),
(2, 'IL-300', 200, 20, 'Lámpara LED Philips 15W', 7, 12.50),
(3, 'AC-500', 150, 15, 'Batería Recargable AAA 1000mAh', 5, 3.20),
(4, 'EL-210', 80, 8, 'Cable Eléctrico de Cobre 2.5mm', 4, 25.00),
(5, 'ME-800', 60, 6, 'Transformador Monofásico 10kVA', 3, 500.00),
(6, 'MX-750', 120, 12, 'Multímetro Digital', 2, 45.00),
(1, 'HD-150', 75, 7, 'Sierra Circular 1400W', 1, 250.00);

-- INSERTAR RELACIÓN PRODUCTOS-PROVEEDORES
INSERT INTO supplier_product (id_product, id_supplier)
VALUES
(1, '1111111111'),  
(2, '3333333333'),  
(3, '4444444444'),  
(4, '5555555555'),  
(5, '2222222222'),  
(6, '3333333333'),  
(7, '1111111111');

-- INSERTAR CLIENTES
INSERT INTO customer (id, customer_type, id_type, first_name, last_name, email) 
VALUES 
('1000000001', 1, 1, 'John', 'Doe', 'johndoe@gmail.com'),
('2000000002', 1, 1, 'Jane', 'Smith', 'janesmith@yahoo.com'),
('3000000003', 2, 2, 'Robert', 'Johnson', 'robert.johnson@outlook.com'),
('4000000004', 1, 1, 'Michael', 'Williams', 'michael.williams@gmail.com'),
('5000000005', 2, 2, 'Emily', 'Brown', 'emily.brown@hotmail.com');

-- INSERTAR DIRECCIONES DE CLIENTES
INSERT INTO customer_address (ZipCode, details, city_id, customer_id)
VALUES 
(110111, 'Calle 123 #45-67', 'BOG', '1000000001'),
(220222, 'Carrera 89 #12-34', 'MED', '2000000002'),
(330333, 'Avenida Siempre Viva #45-67', 'CAL', '3000000003'),
(440444, 'Calle 1 #2-3', 'HOU', '4000000004'),
(550555, 'Avenida Insurgentes #78-90', 'MEX', '5000000005');

-- INSERTAR NÚMEROS DE TELÉFONOS DE CLIENTES
INSERT INTO customer_phone (customer_id, phone_number) 
VALUES 
('1000000001', 3216549870),
('2000000002', 3014567890),
('3000000003', 3157896540),
('4000000004', 3184561230),
('5000000005', 3176543210);

-- INSERTAR ESTADOS DE VENTA
INSERT INTO status_sale (name) 
VALUES 
('Pending'),      
('Completed'),    
('Cancelled'),    
('Returned');     

-- INSERTAR ESTADOS DE ORDEN
INSERT INTO status_order (name) 
VALUES 
('Requested'),    
('Completed'),  
('Cancelled');    

-- INSERTAR VENTAS
INSERT INTO sale (total_price, discount_amount, discount_percent, customer_id, payment_method_id, status_id) 
VALUES 
(450.00, 20.00, 5.00, '1000000001', 1, 1),
(1200.00, 50.00, 4.00, '2000000002', 2, 1),
(300.00, 0.00, 0.00, '3000000003', 1, 1),
(750.00, 30.00, 3.00, '4000000004', 3, 1),
(980.00, 45.00, 4.50, '5000000005', 2, 1);

-- INSERTAR DETALLES DE VENTA
INSERT INTO sale_detail (product_id, quantity, sale_price, sale_id) 
VALUES 
(2, 10, 12.50, 1),
(4, 5, 25.00, 2),
(1, 1, 150.00, 3),
(6, 3, 45.00, 4),
(5, 1, 500.00, 5);

-- INSERTAR ÓRDENES
INSERT INTO supplier_order (total_price, payment_method_id, status_id) 
VALUES 
(1500.00, 1, 1),
(850.00, 2, 1),
(620.00, 3, 1),
(1750.00, 2, 1),
(950.00, 1, 1);

-- INSERTAR DETALLES DE ÓRDENES
INSERT INTO order_detail (product_id, quantity, purchase_price, order_id, supplier_id) 
VALUES 
(1, 10, 120.00, 1, '1111111111'),
(3, 20, 2.80, 3, '3333333333'),
(5, 5, 450.00, 4, '4444444444'),
(6, 15, 40.00, 2, '2222222222'),
(4, 50, 22.00, 5, '5555555555');

DELIMITER //

CREATE TRIGGER update_sale_status
AFTER UPDATE ON sale
FOR EACH ROW
BEGIN
    -- Si el estado cambia a 'Pending' o 'Completed', disminuir el stock
    IF NEW.status_id IN (2) THEN  -- 2: Completed
        UPDATE product p
        JOIN sale_detail sd ON p.id = sd.product_id
        SET p.stock = p.stock - sd.quantity
        WHERE sd.sale_id = NEW.id;
    
    -- Si el estado cambia a 'Cancelled' o 'Returned', aumentar el stock
    ELSEIF NEW.status_id IN (3, 4) THEN  -- 3: Cancelled, 4: Returned
        UPDATE product p
        JOIN sale_detail sd ON p.id = sd.product_id
        SET p.stock = p.stock + sd.quantity
        WHERE sd.sale_id = NEW.id;
    END IF;
END //

DELIMITER ;


DELIMITER //

-- Trigger para modificar el stock al actualizar el estado de una orden de proveedor
CREATE TRIGGER update_order_status
AFTER UPDATE ON supplier_order
FOR EACH ROW
BEGIN
    -- Si el estado cambia a 'Completed', aumentar el stock
    IF NEW.status_id = 2 THEN  -- 2: Completed
        UPDATE product p
        JOIN order_detail od ON p.id = od.product_id
        SET p.stock = p.stock + od.quantity
        WHERE od.order_id = NEW.id;
        
    -- Si el estado cambia a 'Requested' o 'Cancelled', disminuir el stock
    ELSEIF NEW.status_id IN (3) THEN  -- 3: Cancelled
        UPDATE product p
        JOIN order_detail od ON p.id = od.product_id
        SET p.stock = p.stock - od.quantity
        WHERE od.order_id = NEW.id;
    END IF;
END //

DELIMITER ;
