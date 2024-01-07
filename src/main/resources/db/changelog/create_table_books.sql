-- Create the table books

CREATE TABLE IF NOT EXISTS book_store.books (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL UNIQUE,
    price NUMERIC(10, 2) NOT NULL,
    description VARCHAR(255),
    cover_image VARCHAR(255),
    status BookStatus DEFAULT FALSE
);
