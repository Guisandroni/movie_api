CREATE TABLE movie (
    id serial PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(300),
    release_date DATE,
    rating NUMERIC,
    created_at TIMESTAMP,
    update_at TIMESTAMP
)