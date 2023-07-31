DROP TABLE IF EXISTS pessoa;
CREATE TABLE person(id INT, name VARCHAR(500),email VARCHAR(500),dateOfBirth DATETIME,age INT, PRIMARY KEY(id));
DROP TABLE IF EXISTS dados_bancarios;
CREATE TABLE bank_data(id INT, person_id INT,agency INT, account INT, bank INT , PRIMARY KEY(id));
