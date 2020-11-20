CREATE TABLE usr (
	id INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE request (
	id INT NOT NULL,
	money DECIMAL NOT NULL,
	user_id INT NOT NULL,
	source_currency VARCHAR(255) NOT NULL,
	target_currency VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES usr(id)
);


