CREATE TABLE usr (
	id LONG NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE enquiry (
	id LONG NOT NULL,
	value DECIMAL NOT NULL,
	user_id LONG NOT NULL,
	source_currency VARCHAR(255) NOT NULL,
	target_currency VARCHAR(255) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES usr(id)
);


