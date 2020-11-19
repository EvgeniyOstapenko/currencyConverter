CREATE TABLE users (
	id INT NOT NULL,
	inquery_id INT NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE inquiries (
	id INT NOT NULL,
	amount INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES users(id)
);
