--
-- Accounts
--

INSERT INTO account (id, name, description, account_type, balance, active, created_by, created_at) VALUES (UUID(), 'Fineco', 'Fineco Bank', 'asset', 0.0, 1, 'system', CURRENT_TIMESTAMP);


-- insert into account (id, name, account_type, balance) values ('2', 'Opo wallet', 'Asset', 1.0);
-- insert into account (id, name, account_type, balance) values ('3', 'Hardware', 'Expense', 1.0);