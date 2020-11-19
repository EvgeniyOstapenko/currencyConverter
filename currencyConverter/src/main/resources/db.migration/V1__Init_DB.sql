CREATE TABLE usr (
	id INT NOT NULL,
	enquiry_id INT,
	PRIMARY KEY (id)
);

CREATE TABLE enquiry (
	id INT NOT NULL,
	value INT NOT NULL,
	PRIMARY KEY (id)
);
