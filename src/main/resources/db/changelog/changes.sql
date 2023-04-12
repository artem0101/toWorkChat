DROP TABLE IF EXISTS zoo.animal_food;
DROP TABLE IF EXISTS zoo.animal;
DROP TABLE IF EXISTS zoo.product;

DROP SEQUENCE IF EXISTS zoo.animal_food_seq;
DROP SEQUENCE IF EXISTS zoo.animal_seq;
DROP SEQUENCE IF EXISTS zoo.product_seq;

CREATE SCHEMA IF NOT EXISTS zoo;

CREATE TABLE zoo.product (
    id           		BIGINT PRIMARY KEY,
    name         		VARCHAR(30) NOT NULL,
    amount_of_products  INT NOT NULL,
    unit_type   		VARCHAR(30) NOT NULL,
    type   				VARCHAR(30) NOT NULL
);

CREATE SEQUENCE zoo.product_seq
START 1
INCREMENT 1
OWNED BY zoo.product.id;

CREATE TABLE zoo.animal (
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(30) NOT NULL,
    type  		VARCHAR(30) NOT NULL,
    is_predator	BOOLEAN NOT NULL
);

CREATE SEQUENCE zoo.animal_seq
START 1
INCREMENT 1
OWNED BY zoo.animal.id;

CREATE TABLE zoo.animal_food (
	id          		BIGINT PRIMARY KEY,
    animal_id   		BIGINT NOT NULL,
    product_id  		BIGINT NOT NULL,
    composition_rate	BIGINT NOT NULL,
    FOREIGN KEY (animal_id) REFERENCES zoo.animal (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES zoo.product (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE SEQUENCE zoo.animal_food_seq
START 1
INCREMENT 1
OWNED BY zoo.animal_food.id;

INSERT INTO zoo.product (id,name,amount_of_products,unit_type,"type") VALUES
	 (1,'Морковь',44,'QUANTITY','VEGETABLE'),
	 (2,'Мясо',144,'KILOGRAM','MEAT'),
	 (3,'Капуста',32,'QUANTITY','VEGETABLE'),
	 (4,'Банан',23,'QUANTITY','FRUIT'),
	 (5,'Яблоко',0,'QUANTITY','FRUIT'),
	 (6,'Зерно',11,'KILOGRAM','CEREALS'),
	 (7,'Вода',11,'LITER','WATER');

INSERT INTO zoo.animal (id,name,"type",is_predator) VALUES
	 (1,'Обезьяна','MAMMAL',false),
	 (2,'Заяц','MAMMAL',false),
	 (3,'Орёл','BIRD',true),
	 (4,'Тигр','MAMMAL',true),
	 (5,'Лошадь','MAMMAL',false);

INSERT INTO zoo.animal_food (id,animal_id,product_id,composition_rate) VALUES
	 (1,1,7,3),
	 (2,1,4,5),
	 (3,1,5,4),
	 (4,2,7,1),
	 (5,2,1,3),
	 (6,2,3,2),
	 (7,3,7,1),
	 (8,3,2,3),
	 (9,3,6,1),
	 (10,3,5,2),
	 (11,4,7,10),
	 (12,4,7,7),
	 (13,5,7,11),
	 (14,5,6,7),
	 (15,5,5,6),
	 (16,5,1,5);


