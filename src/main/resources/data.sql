-- Sample initial data (passwords are BCrypt-hashed for 'password123')
INSERT INTO message (uuid, message, password, expiration_day, expiration_hour, expiration_minute, selfdelete) VALUES
    ('550e8400-e29b-41d4-a716-446655440000', 'Sample secret message 1', '$2a$10$example.hash.for.password123', 1, 2, 30, true),
    ('550e8400-e29b-41d4-a716-446655440001', 'Sample secret message 2', '$2a$10$example.hash.for.password123', 0, 5, 0, false);
