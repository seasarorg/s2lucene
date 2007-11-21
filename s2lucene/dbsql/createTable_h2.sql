CREATE TABLE DOCUMENTS (
	ID BIGINT NOT NULL,
	NAME VARCHAR(100),
	CATEGORY VARCHAR(100),
	PRICE NUMERIC(9.0)
);

ALTER TABLE DOCUMENTS ADD PRIMARY KEY(ID);

CREATE SEQUENCE DOCUMENTSID
	START WITH 1
	INCREMENT BY 1
;

