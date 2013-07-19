--
-- Accounts
--

INSERT INTO account (id, name, description, account_type, balance, retired_at, created_by, created_at) VALUES (UUID('32314574-3231-4574-9842-b26f9842b26f'), 'Fineco', 'Fineco Bank', 'asset', 0.0, null, 'system', CURRENT_TIMESTAMP);

INSERT INTO account (name, description, account_type, balance, retired_at, created_by, created_at) VALUES ('Opo wallet', 'Opo wallet', 'asset', 0.0, null, 'system', CURRENT_TIMESTAMP);

INSERT INTO account (name, description, account_type, balance, retired_at, created_by, created_at) VALUES ('Books', 'Book and other likewise', 'expense', 0.0, null, 'system', CURRENT_TIMESTAMP);

-- insert into account (id, name, account_type, balance) values ('2', 'Opo wallet', 'Asset', 1.0);
-- insert into account (id, name, account_type, balance) values ('3', 'Hardware', 'Expense', 1.0);