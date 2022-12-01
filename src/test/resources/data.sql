--dati fittizi predisposti per test
INSERT INTO customer(fiscal_code,name,surname,address) VALUES ('ABC123', 'TName', 'TSurname','Via dei Mille');
INSERT INTO customer(fiscal_code,name,surname,address) VALUES ('CBA321', 'Alberto', 'Rossi','Via dei Cento');
INSERT INTO customer(fiscal_code,name,surname,address) VALUES ('CDE456', 'Giovanni', 'Rossi','Via dei Duecento');
INSERT INTO customer(fiscal_code,name,surname,address) VALUES ('EDC654', 'Paolo', 'Rossi','Via dei Trecento');

INSERT INTO device(uuid,state,fiscal_code) VALUES ('123', 'ACTIVE','ABC123');
INSERT INTO device(uuid,state,fiscal_code) VALUES ('456', 'INACTIVE','ABC123');
INSERT INTO device(uuid,state,fiscal_code) VALUES ('678', 'LOST','CBA321');
INSERT INTO device(uuid,state,fiscal_code) VALUES ('910', 'INACTIVE','CDE456');



