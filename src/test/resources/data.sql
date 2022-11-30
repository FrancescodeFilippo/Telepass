--CREATE TABLE CUSTOMER(
--fiscalCode varchar primary key,
--name varchar,
--surname varchar,
--address varchar
--);

INSERT INTO customer(fiscal_code,name,surname,address) VALUES ('ABC123', 'Mario', 'Rossi','Via dei Mille');
INSERT INTO customer VALUES ('CBA321', 'Alberto', 'Rossi','Via dei Cento');
INSERT INTO customer VALUES ('CDE456', 'Giovanni', 'Rossi','Via dei Duecento');
INSERT INTO customer VALUES ('EDC654', 'Paolo', 'Rossi','Via dei Trecento');

INSERT INTO device(uuid,state,fiscal_code) VALUES ('123', 'ACTIVE','ABC123');
INSERT INTO device(uuid,state,fiscal_code) VALUES ('21346564563423', 'INACTIVE','ABC123');
INSERT INTO device(uuid,state,fiscal_code) VALUES ('09873437289303', 'LOST','CBA321');


