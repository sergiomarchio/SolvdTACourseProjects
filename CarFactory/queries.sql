USE car_factory_sergio_marchio;


-- Insert statements: populate tables

INSERT INTO brands (name)
    VALUES ('Tesla'), ('BMW'), ('Peugeot'), ('Porsche'), ('Audi'), ('Fiat'), ('Citroën'), ('Pontiac');

INSERT INTO car_models(name, type, year, fuel_type, unitary_price, brand_id)
    VALUES ('Model 3', 'Sedan', 2020, 'Electric', 25000, (SELECT id FROM brands WHERE name = 'Tesla') ),
           ('Model S', 'Sedan', 2020, 'Electric', 50000, (SELECT id FROM brands WHERE name = 'Tesla') ),
           ('Cyber-truck', 'Pickup', 2020, 'Electric', 30000, (SELECT id FROM brands WHERE name = 'Tesla') ),
           ('Z4', 'Roadster', 2018, 'Gas', 80000, (SELECT id FROM brands WHERE name = 'BMW') ),
           ('1 Series', 'Subcompact', 2019, 'Gas', 30000, (SELECT id FROM brands WHERE name = 'BMW') ),
           ('208', 'Subcompact', 2019, 'Gas', 15000, (SELECT id FROM brands WHERE name = 'Peugeot') ),
           ('e-208', 'Subcompact', 2019, 'Electric', 25000, (SELECT id FROM brands WHERE name = 'Peugeot') ),
           ('911', 'Sports', 2018, 'Gas', 250000, (SELECT id FROM brands WHERE name = 'Porsche') ),
           ('Boxster', 'Roadster', 2017, 'Gas', 60000, (SELECT id FROM brands WHERE name = 'Porsche') ),
           ('Cayenne', 'SUV', 2018, 'Gas', 60000, (SELECT id FROM brands WHERE name = 'Porsche') ),
           ('A1', 'SUV', 2018, 'Gas', 25000, (SELECT id FROM brands WHERE name = 'Audi') ),
           ('Q7', 'SUV', 2020, 'Diesel', 130000, (SELECT id FROM brands WHERE name = 'Audi') ),
           ('500', 'City Car', 2016, 'Gas', 10000, (SELECT id FROM brands WHERE name = 'Fiat') ),
           ('207', 'Subcompact', 2012, 'Gas', 1300, (SELECT id FROM brands WHERE name = 'Peugeot') );

INSERT INTO countries(name) VALUES ('China'), ('England'), ('France');

INSERT INTO cities(name, country_id)
    VALUES('London', (SELECT id FROM countries WHERE name = 'England')),
          ('Paris', (SELECT id FROM countries WHERE name = 'France')),
          ('Wuhan', (SELECT id FROM countries WHERE name = 'China'));
          
INSERT INTO addresses(street, number, zip_code, city_id)
    VALUES('Bat street', '2019', '1919', (SELECT id FROM cities WHERE name = 'Wuhan'));

INSERT INTO providers(name, email, phone, address_id)
    VALUES('Pangolin ltd.', 'pan@mail.com', '02983747', last_insert_id());

INSERT INTO part_types(name, type, provider_id)
    VALUES('backbone 3000', 'chasis', last_insert_id()),
          ('tiger skeleton', 'chasis', last_insert_id()),
          ('lion skeleton', 'chasis', last_insert_id()),
          ('kitten paws', 'wheel', last_insert_id()),
          ('donut wheel', 'wheel', last_insert_id());

INSERT INTO addresses(street, number, zip_code, city_id)
    VALUES('Candle st.', '1760', '1840', (SELECT id FROM cities WHERE name = 'London'));

INSERT INTO providers(name, email, phone, address_id)
    VALUES('Energy Kernel ltd.', 'vapormachines@ek.uk', '4545-4545', last_insert_id());

INSERT INTO part_types(name, type, provider_id)
    VALUES('Roar 66', 'engine', last_insert_id()),
          ('Nikola\'s dream 2.0', 'engine', last_insert_id());

INSERT INTO model_parts(car_model_id, part_type_id, count)
    VALUES((SELECT id FROM car_models WHERE name = 'Model 3'), (SELECT id from part_types WHERE name = 'backbone 3000'), 1),
          ((SELECT id FROM car_models WHERE name = 'Model 3'), (SELECT id from part_types WHERE name = 'Nikola\'s dream 2.0'), 2),
          ((SELECT id FROM car_models WHERE name = 'Model 3'), (SELECT id from part_types WHERE name = 'donut wheel'), 5),
          ((SELECT id FROM car_models WHERE name = '208'), (SELECT id from part_types WHERE name = 'Roar 66'), 1),
          ((SELECT id FROM car_models WHERE name = '208'), (SELECT id from part_types WHERE name = 'lion skeleton'), 1),
          ((SELECT id FROM car_models WHERE name = '208'), (SELECT id from part_types WHERE name = 'kitten paws'), 5),
          ((SELECT id FROM car_models WHERE name = '207'), (SELECT id from part_types WHERE name = 'tiger skeleton'), 1),
          ((SELECT id FROM car_models WHERE name = '207'), (SELECT id from part_types WHERE name = 'kitten paws'), 5);

INSERT INTO addresses(street, number, zip_code, city_id)
    VALUES('Manet st.', '1010', '4433', (SELECT id FROM cities WHERE name = 'Paris'));

INSERT INTO providers(name, email, phone, address_id)
    VALUES('Vincent RGB', 'vincent@vvg.com', '1863568', last_insert_id());

INSERT INTO paint_colors(name, provider_id)
    VALUES('Black', last_insert_id()),
          ('Blue', last_insert_id()),
          ('Gray', last_insert_id()),
          ('Red', last_insert_id()),
          ('Yellow', last_insert_id());

INSERT INTO model_colors(car_model_id, paint_color_id)
    VALUES((SELECT id FROM car_models WHERE name = 'Model 3'), (SELECT id FROM paint_colors WHERE name = 'Black')),
          ((SELECT id FROM car_models WHERE name = 'Model 3'), (SELECT id FROM paint_colors WHERE name = 'Red')),
          ((SELECT id FROM car_models WHERE name = 'Model 3'), (SELECT id FROM paint_colors WHERE name = 'Gray')),
          ((SELECT id FROM car_models WHERE name = '208'), (SELECT id FROM paint_colors WHERE name = 'Black')),
          ((SELECT id FROM car_models WHERE name = '208'), (SELECT id FROM paint_colors WHERE name = 'Gray')),
          ((SELECT id FROM car_models WHERE name = '207'), (SELECT id FROM paint_colors WHERE name = 'Gray'));


-- Update statements

UPDATE car_models SET unitary_price = 27000 WHERE name = 'Model 3';
UPDATE car_models SET unitary_price = 255000 WHERE name = '911';
UPDATE car_models SET name = 'Cybertruck' WHERE name = 'Cyber-truck';

UPDATE addresses SET street = 'Vampire st.' WHERE street = 'Bat street';
UPDATE addresses SET street = 'Flame st.' WHERE street = 'Candle st.';
UPDATE addresses SET street = 'Manet Av.' WHERE street = 'Manet st.';

UPDATE providers SET email = 'crazycritter@aol.com' WHERE name = 'Pangolin ltd.';
UPDATE providers SET email = 'steampowered@energykernel.com' WHERE name = 'Energy Kernel ltd.';
UPDATE providers SET email = 'van@go.com' WHERE name = 'Vincent RGB';

UPDATE paint_colors SET name = 'Cherry' WHERE name = 'Red';
UPDATE paint_colors SET name = 'Silver' WHERE name = 'Gray';
UPDATE paint_colors SET name = 'Onyx' WHERE name = 'Black';

UPDATE part_types SET name = 'The donut wheel' WHERE name = 'donut wheel';
UPDATE part_types SET name = 'Lion skelly' WHERE name = 'lion skeleton';
UPDATE part_types SET name = 'Backbone 3k' WHERE name = 'backbone 3000';


-- Delete statements

DELETE FROM paint_colors WHERE name = 'Yellow';
DELETE FROM car_models WHERE name = '1 Series';
DELETE FROM car_models WHERE type = 'Roadster';
DELETE FROM car_models WHERE year <= 2015;
DELETE FROM brands WHERE name = 'Pontiac';


-- Get all data from DB

-- In MySQL FULL JOIN can be achiveved with an UNION of LEFT and RIGHT join of the tables.
-- To do that, create temp tables where constraints allows for just using LEFT JOIN,
-- and make the respective UNIONS :)

CREATE TEMPORARY TABLE temp_cars
    SELECT * FROM brands b
        LEFT JOIN car_models cm ON b.id = cm.brand_id
        LEFT JOIN model_parts mp ON mp.car_model_id = cm.id
        LEFT JOIN model_colors mc ON mc.car_model_id = cm.id
        LEFT JOIN cars c ON c.car_model_id = cm.id
        LEFT JOIN parts p ON p.car_id = c.id;

CREATE TEMPORARY TABLE temp_countries
    SELECT * FROM countries c
        LEFT JOIN cities ct ON ct.country_id = c.id
        LEFT JOIN addresses a ON a.city_id = ct.id;

CREATE TEMPORARY TABLE temp_depts
    SELECT * FROM departments d
        LEFT JOIN employees e ON e.department_id = d.id
        LEFT JOIN assembly_employees ae ON ae.employee_id = e.id;

CREATE TEMPORARY TABLE temp_assembly
    SELECT * FROM assembly_lines al
        LEFT JOIN car_assembly_lines cas ON cas.assembly_line_id = al.id;

CREATE TEMPORARY TABLE temp_prov
    SELECT * FROM providers p
        LEFT JOIN part_types pt ON pt.provider_id = p.id
        LEFT JOIN paint_colors pc ON pc.provider_id = p.id;

CREATE TEMPORARY TABLE temp_client
    SELECT * FROM client_orders co
        LEFT JOIN clients c ON c.id = co.client_id
    UNION
    SELECT * FROM client_orders co
        RIGHT JOIN clients c ON c.id = co.client_id;

------------------


-- Aggregate functions without Group By

SELECT AVG(unitary_price) FROM car_models;
SELECT MAX(unitary_price) FROM car_models;
SELECT MIN(unitary_price) FROM car_models;
SELECT MIN(year) FROM car_models;
SELECT COUNT(*) FROM part_types;
SELECT COUNT(*) as 'brands without models'
    FROM car_models cm
    RIGHT JOIN brands b ON b.id = cm.brand_id
    WHERE cm.name IS null;
SELECT COUNT(*) as 'models without part_types'
    FROM car_models cm
    LEFT JOIN model_parts mp ON cm.id = mp.car_model_id
    WHERE mp.car_model_id IS null;
    

-- Aggregate functions with group by
 
SELECT type, COUNT(*) FROM car_models GROUP BY type;

SELECT year, COUNT(*) FROM car_models GROUP BY year ORDER BY year ASC;

SELECT fuel_type, COUNT(*) as count FROM car_models GROUP BY fuel_type ORDER BY count DESC;

SELECT fuel_type, AVG(unitary_price) FROM car_models GROUP BY fuel_type;

SELECT b.name as brand, AVG(unitary_price) 
    FROM car_models cm
    INNER JOIN brands b ON b.id = cm.brand_id
    GROUP BY brand_id;

SELECT b.name as brand, COUNT(*) as 'model count'
    FROM car_models cm
    INNER JOIN brands b ON b.id = cm.brand_id
    GROUP BY brand_id;

SELECT pc.name as color, COUNT(*) as 'model count'
    FROM model_colors mc
    INNER JOIN paint_colors pc ON pc.id = mc.paint_color_id
    GROUP BY paint_color_id;


-- Aggregate functions with group by and having

SELECT year, COUNT(*) FROM car_models GROUP BY year HAVING COUNT(*) > 1;

SELECT fuel_type, AVG(unitary_price) as avg_price FROM car_models 
    GROUP BY fuel_type HAVING avg_price > 50000;

SELECT b.name, AVG(unitary_price) as avg_price
    FROM brands b
    INNER JOIN car_models cm ON b.id = cm.brand_id
    GROUP BY b.id
    HAVING avg_price > 75000;

SELECT b.name, MIN(unitary_price) as min_price
    FROM brands b
    INNER JOIN car_models cm ON b.id = cm.brand_id
    GROUP BY b.id
    HAVING min_price > 50000;

SELECT b.name, MIN(year) as min_year
    FROM brands b
    INNER JOIN car_models cm ON b.id = cm.brand_id
    GROUP BY b.id
    HAVING min_year >= 2019;

SELECT b.name, COUNT(*) as count
    FROM brands b
    INNER JOIN car_models cm ON b.id = cm.brand_id
    GROUP BY b.id
    HAVING count > 1;

SELECT pc.name, COUNT(*) as count
    FROM paint_colors pc
    INNER JOIN model_colors mc ON pc.id = mc.paint_color_id
    GROUP BY pc.id
    HAVING count > 1;

