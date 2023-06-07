-- -- Inserting roles
-- INSERT INTO Role (role_name)
-- VALUES ('Administrator'),
--        ('User');
--
-- -- Inserting users
-- INSERT INTO User (login, name, surname, email, password, role_id)
-- VALUES ('michael.scott', 'Michael', 'Scott', 'michael.scott@example.com', 'Bestboss123', (SELECT id FROM Role WHERE name = 'Administrator')),
--        ('jim.halpert', 'Jim', 'Halpert', 'jim.halpert@example.com', 'Pranks123', (SELECT id FROM Role WHERE name = 'User')),
--        ('pam.beesly', 'Pam', 'Beesly', 'pam.beesly@example.com', 'Painting123', (SELECT id FROM Role WHERE name = 'User')),
--        ('dwight.schrute', 'Dwight', 'Schrute', 'dwight.schrute@example.com', 'Beets123', (SELECT id FROM Role WHERE name = 'User')),
--        ('angela.martin', 'Angela', 'Martin', 'angela.martin@example.com', 'Cats123', (SELECT id FROM Role WHERE name = 'User')),
--        ('oscar.martinez', 'Oscar', 'Martinez', 'oscar.martinez@example.com', 'Smart123', (SELECT id FROM Role WHERE name = 'User')),
--        ('stanley.hudson', 'Stanley', 'Hudson', 'stanley.hudson@example.com', 'Crossword123', (SELECT id FROM Role WHERE name = 'User')),
--        ('phyllis.vance', 'Phyllis', 'Vance', 'phyllis.vance@example.com', 'Knitting123', (SELECT id FROM Role WHERE name = 'User')),
--        ('ryan.howard', 'Ryan', 'Howard', 'ryan.howard@example.com', 'Fire123', (SELECT id FROM Role WHERE name = 'Administrator')),
--        ('andy.bernard', 'Andy', 'Bernard', 'andy.bernard@example.com', 'Singing123', (SELECT id FROM Role WHERE name = 'User')),
--        ('erin.hannon', 'Erin', 'Hannon', 'erin.hannon@example.com', 'Cheerful123', (SELECT id FROM Role WHERE name = 'User')),
--        ('kevin.malone', 'Kevin', 'Malone', 'kevin.malone@example.com', 'Chili123', (SELECT id FROM Role WHERE name = 'User')),
--        ('toby.flenderson', 'Toby', 'Flenderson', 'toby.flenderson@example.com', 'Hr123', (SELECT id FROM Role WHERE name = 'User')),
--        ('kelly.kapoor', 'Kelly', 'Kapoor', 'kelly.kapoor@example.com', 'Fashion123', (SELECT id FROM Role WHERE name = 'User')),
--        ('creed.brattom', 'Creed', 'Bratton', 'creed.bratton@example.com', 'Mysterious123', (SELECT id FROM Role WHERE name = 'User'));
--
-- -- Inserting countries
-- INSERT INTO Country (name)
-- VALUES ('Germany'),
--        ('France'),
--        ('Italy'),
--        ('Spain'),
--        ('Canada'),
--        ('Mexico'),
--        ('Australia'),
--        ('Japan');
--
-- -- Inserting cities
-- INSERT INTO City (name, country_id)
-- VALUES ('Berlin', (SELECT countryId FROM Country WHERE name = 'Germany')),
--        ('Munich', (SELECT countryId FROM Country WHERE name = 'Germany')),
--        ('Paris', (SELECT countryId FROM Country WHERE name = 'France')),
--        ('Lyon', (SELECT countryId FROM Country WHERE name = 'France')),
--        ('Rome', (SELECT countryId FROM Country WHERE name = 'Italy')),
--        ('Venice', (SELECT countryId FROM Country WHERE name = 'Italy')),
--        ('Barcelona', (SELECT countryId FROM Country WHERE name = 'Spain')),
--        ('Madrid', (SELECT countryId FROM Country WHERE name = 'Spain')),
--        ('Toronto', (SELECT countryId FROM Country WHERE name = 'Canada')),
--        ('Vancouver', (SELECT countryId FROM Country WHERE name = 'Canada')),
--        ('Mexico City', (SELECT countryId FROM Country WHERE name = 'Mexico')),
--        ('Cancun', (SELECT countryId FROM Country WHERE name = 'Mexico')),
--        ('Sydney', (SELECT countryId FROM Country WHERE name = 'Australia')),
--        ('Melbourne', (SELECT countryId FROM Country WHERE name = 'Australia')),
--        ('Tokyo', (SELECT countryId FROM Country WHERE name = 'Japan')),
--        ('Osaka', (SELECT countryId FROM Country WHERE name = 'Japan'));
--
-- -- Inserting hotels
-- INSERT INTO Hotel (name, city_id, location, description)
-- VALUES ('Berlin Grand', (SELECT city_id FROM City WHERE name = 'Berlin'), '2 Berlin St, Berlin', 'Luxury hotel in Berlin'),
--        ('Paris Towers', (SELECT city_id FROM City WHERE name = 'Paris'), '22 Paris St, Paris', 'Historic hotel in Paris'),
--        ('Rome Palazzo', (SELECT city_id FROM City WHERE name = 'Rome'), '45 Rome St, Rome', 'Beautiful hotel in Rome'),
--        ('Barcelona Beach Hotel', (SELECT city_id FROM City WHERE name = 'Barcelona'), '12 Barcelona St, Barcelona', 'Beachfront hotel in Barcelona'),
--        ('Toronto Suites', (SELECT city_id FROM City WHERE name = 'Toronto'), '67 Toronto St, Toronto', 'Modern hotel in Toronto'),
--        ('Mexico City Inn', (SELECT city_id FROM City WHERE name = 'Mexico City'), '28 Mexico City St, Mexico City', 'Traditional hotel in Mexico City'),
--        ('Sydney Harbour Hotel', (SELECT city_id FROM City WHERE name = 'Sydney'), '38 Sydney St, Sydney', 'Hotel with stunning harbour views'),
--        ('Tokyo Towers', (SELECT city_id FROM City WHERE name = 'Tokyo'), '12 Tokyo St, Tokyo', 'Beautiful hotel in Tokyo');
--
-- -- Inserting RoomClassification
-- INSERT INTO RoomClassification (name)
-- VALUES ('Standard'),
--        ('Deluxe'),
--        ('Suite');
--
-- -- Inserting rooms
-- -- Let's suppose each hotel has 10 rooms: 4 Standard, 4 Deluxe, 2 Suite
-- DECLARE @hotel_id INT = 1;
-- WHILE @hotel_id <= 8
-- BEGIN
--     DECLARE @room_number INT = 100;
-- DECLARE @classification_id INT;
-- DECLARE @price DECIMAL(7,2);
-- DECLARE @i INT = 1;
-- WHILE @i <= 10
-- BEGIN
--     IF @i <= 4 -- Standard
-- BEGIN
-- SET @classification_id = (SELECT id FROM RoomClassification WHERE name = 'Standard');
-- SET @price = 100.00;
-- END
-- ELSE IF @i <= 8 -- Deluxe
-- BEGIN
-- SET @classification_id = (SELECT id FROM RoomClassification WHERE name = 'Deluxe');
-- SET @price = 200.00;
-- END
-- ELSE -- Suite
-- BEGIN
-- SET @classification_id = (SELECT id FROM RoomClassification WHERE name = 'Suite');
-- SET @price = 300.00;
-- END
-- INSERT INTO Room (hotel_id, capacity, number, room_classification_id, price, isAvailable)
-- VALUES (@hotel_id, 2, @room_number, @classification_id, @price, 1);
-- SET @i = @i + 1;
-- SET @room_number = @room_number + 1;
-- END
-- SET @hotel_id = @hotel_id + 1;
-- END
