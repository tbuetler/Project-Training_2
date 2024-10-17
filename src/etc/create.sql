CREATE TABLE IF NOT EXISTS SalesValue
(
	id				BIGSERIAL PRIMARY KEY,
	productId		INT NOT NULL,
	number			INT NOT NULL,
	percentage		DOUBLE
);
