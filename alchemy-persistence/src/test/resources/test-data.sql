-- insert into account (id, name) values ('1', 'Fineco');
-- insert into account (id, name) values ('2', 'Opo wallet');
-- insert into account (id, name) values ('3', 'Hardware');

INSERT INTO account (id, name, description, account_type, balance, retired_at, created_by, created_at) VALUES (UUID('32314574-3231-4574-9842-b26f9842b26f'), 'Fineco', 'Fineco Bank', 'asset', 0.0, null, 'system', CURRENT_TIMESTAMP);