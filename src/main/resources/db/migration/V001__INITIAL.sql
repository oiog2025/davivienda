CREATE TABLE users (
                       id  BIGINT GENERATED BY DEFAULT AS IDENTITY
                           PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       is_active BOOLEAN DEFAULT TRUE,
                       created_at TIMESTAMP DEFAULT NOW(),
                       updated_at TIMESTAMP
);
--  Password
--  Admin123!
INSERT INTO users
(name, email, password, is_active)
VALUES (
           'admin',
           'admin@example.com',
        '$2a$10$8s6rL.Zowgy5IzaiDyWVQOapZ5TmLneLb45m0IxOObCCFkzIQqLQ2',
           TRUE
       );