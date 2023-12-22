CREATE SCHEMA IF NOT EXISTS book_store;

CREATE TABLE IF NOT EXISTS book_store.books (
                                id SERIAL PRIMARY KEY,
                                title VARCHAR(255) NOT NULL,
                                author VARCHAR(255) NOT NULL,
                                isbn VARCHAR(255) NOT NULL,
                                price DECIMAL(19,2) NOT NULL,
                                description TEXT,
                                cover_image TEXT
);