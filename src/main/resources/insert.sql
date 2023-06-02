-- Inserting roles
INSERT INTO Role (role_name) VALUES
                                 ('Administrator'),
                                 ('User');

-- Inserting users
INSERT INTO User (login, name, surname, email, password, role_id) VALUES
                                                                      ('admin', 'Administrator', '', 'admin@example.com', 'admin123', 1),
                                                                      ('user1', 'User', '1', 'user1@example.com', 'user123', 2),
                                                                      ('user2', 'User', '2', 'user2@example.com', 'user456', 2);

-- Inserting countries
INSERT INTO Country (name) VALUES
                               ('Ukraine'),
                               ('USA'),
                               ('United Kingdom');

-- Inserting cities
INSERT INTO City (name, country_id) VALUES
                                        ('Kyiv', 1),
                                        ('Lviv', 1),
                                        ('New York', 2),
                                        ('London', 3);

-- Inserting hotels
INSERT INTO Hotel (name, city_id, location, description) VALUES
                                                             ('Grand Hotel', 1, '123 Main Street, Kyiv', 'Luxurious hotel located in the heart of Kyiv'),
                                                             ('Riviera Hotel', 2, '456 Elm Street, Lviv', 'Charming hotel offering scenic views of Lviv'),
                                                             ('The Plaza', 3, '789 Broadway, New York', 'Iconic hotel known for its elegance and luxury');

-- Inserting rooms
INSERT INTO Room (hotel_id, capacity, number, type, price, is_available) VALUES
                                                                             (1, 2, 101, 'Standard', 100.00, 1),
                                                                             (1, 3, 102, 'Deluxe', 150.00, 1),
                                                                             (2, 2, 201, 'Standard', 120.00, 1),
                                                                             (2, 4, 202, 'Suite', 200.00, 1);

-- Inserting bookings
INSERT INTO Booking (user_id, hotel_id, start_date, end_date, num_of_people) VALUES
                                                                                 (2, 1, '2023-06-10', '2023-06-15', 2),
                                                                                 (3, 2, '2023-07-01', '2023-07-05', 1);

-- Inserting booking rooms
INSERT INTO BookingRoom (booking_id, room_id) VALUES
                                                  (1, 1),
                                                  (1, 2),
                                                  (2, 3);
