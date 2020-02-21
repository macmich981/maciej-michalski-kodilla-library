CREATE TABLE readers (
	id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) CHARSET utf8mb4,
    last_name VARCHAR(255) CHARSET utf8mb4,
    account_created_date DATETIME
);

CREATE TABLE titles (
	id SERIAL PRIMARY KEY,
    author VARCHAR(255) CHARSET utf8mb4,
    publication_year INT(11),
    title VARCHAR(255) CHARSET utf8mb4
);

CREATE TABLE copies (
	id SERIAL PRIMARY KEY,
    state VARCHAR(255) CHARSET utf8mb4,
    title_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (title_id) REFERENCES titles(id)
);

CREATE TABLE rents (
	id SERIAL PRIMARY KEY,
    rent_date DATETIME,
    return_date DATETIME,
    copy_id BIGINT UNSIGNED NOT NULL,
    reader_id BIGINT UNSIGNED NOT NULL,
    FOREIGN KEY (copy_id) REFERENCES copies(id),
    FOREIGN KEY (reader_id) REFERENCES readers(id)
);