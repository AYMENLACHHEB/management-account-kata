-- CREATE CLIENT
INSERT INTO client VALUES (1, '12 avenue pasteur, 93150 Blanc mesnil', '1986-10-24', 'Julien', 'Dupon', '061213141516');


-- CREATE BANK_ACCOUNT
INSERT INTO bank_account VALUES (1, 'FR30001234', 100, NOW(), 100, 1);

-- CREATE OPERATION
INSERT INTO operation VALUES(1, 1000, NOW(), 'DEPOSIT', 1);
INSERT INTO operation VALUES(2, -100, NOW(), 'WITHDRAWAL', 1);

