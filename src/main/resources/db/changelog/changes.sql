CREATE SCHEMA IF NOT EXISTS zoo;

DROP TABLE IF EXISTS zoo.animal_food;
DROP TABLE IF EXISTS zoo.animal;
DROP TABLE IF EXISTS zoo.product;

DROP SEQUENCE IF EXISTS zoo.animal_food_seq;
DROP SEQUENCE IF EXISTS zoo.animal_seq;
DROP SEQUENCE IF EXISTS zoo.product_seq;


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

