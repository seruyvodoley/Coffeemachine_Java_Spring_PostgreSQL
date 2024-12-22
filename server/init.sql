-- Create table if it doesn't exist
CREATE TABLE IF NOT EXISTS coffee_machines (
    id SERIAL PRIMARY KEY,
    brand VARCHAR(255),
    model VARCHAR(255),
    price DECIMAL,
    water_tank_capacity INT,
    milk_tank_capacity INT,
    coffee_bean_capacity INT
);

-- Insert sample data
INSERT INTO coffee_machines (brand, model, price, water_tank_capacity, milk_tank_capacity, coffee_bean_capacity)
VALUES
    ('DeLonghi', 'Magnifica S', 29999, 1800, 600, 250),
    ('Philips', 'Series 3200', 33999, 1500, 250, 275),
    ('Nespresso', 'Pixie', 8499, 700, 0, 100),
    ('Breville', 'Barista Express', 54999, 2000, 500, 225),
    ('Jura', 'E6', 79999, 1500, 400, 280),
    ('Krups', 'Evidence', 43999, 2000, 600, 260),
    ('Saeco', 'Xelsis', 94999, 1700, 500, 300),
    ('Bosch', 'Tassimo', 3499, 900, 0, 150);
