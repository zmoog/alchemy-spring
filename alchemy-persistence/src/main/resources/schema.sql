
--
--
--

create table account (
	ID BINARY(16) DEFAULT X'' PRIMARY KEY, 
	name varchar(100) NOT NULL, 
	description varchar(100) NOT NULL,
	account_type varchar(32) NOT NULL,
	balance float NOT NULL,
	active BOOLEAN NOT NULL,
	
	created_by varchar(100) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	updated_by varchar(100),
	updated_at TIMESTAMP
);