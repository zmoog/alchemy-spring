
--
--
--

create table account (
	id BINARY(16) DEFAULT X'' PRIMARY KEY, 
	name varchar(100) NOT NULL, 
	description varchar(100) NOT NULL,
	account_type varchar(32) NOT NULL,
	balance float NOT NULL,
	retired_at TIMESTAMP,
	
	created_by varchar(100) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by varchar(100),
	updated_at TIMESTAMP
);

-- CREATE TRIGGER TRIG BEFORE INSERT ON account REFERENCING NEW ROW AS NEW FOR EACH ROW SET NEW.ID = UUID();
