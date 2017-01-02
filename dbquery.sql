CREATE DATABASE IF NOT EXISTS ubmplatform;
use ubmplatform;
CREATE TABLE IF NOT EXISTS account (
  Email varchar(40) NOT NULL,
  Password varchar(20) NOT NULL,
  Tipo char(1) NOT NULL,
  PRIMARY KEY (Email)
);
CREATE TABLE IF NOT EXISTS profilo (
  Email varchar(40) NOT NULL,
  Nome varchar(20) NOT NULL,
  Cognome varchar(20) NOT NULL,
  DataNascita date NOT NULL,
  Foto varchar(100) DEFAULT NULL,
  Residenza varchar(40) DEFAULT NULL,
  Telefono varchar(10) DEFAULT NULL,
  Interessi varchar(100) DEFAULT NULL,
  PRIMARY KEY (Email),
  FOREIGN KEY (Email) REFERENCES account (Email)
);

CREATE TABLE IF NOT EXISTS feedback (
  EmailP varchar(40) NOT NULL,
  EmailR varchar(40) NOT NULL,
  Valutazione smallint(6) NOT NULL,
  Descrizione varchar(50) DEFAULT NULL,
  DataPubblicazione date NOT NULL,
  PRIMARY KEY (EmailP,EmailR),
  FOREIGN KEY (EmailP) REFERENCES account (Email),
  FOREIGN KEY (EmailR) REFERENCES account (Email)
);
CREATE TABLE IF NOT EXISTS annuncio (
  ID int(11) NOT NULL,
  Email varchar(20) NOT NULL,
  Foto varchar(100) NOT NULL,
  Prezzo float NOT NULL,
  Titolo varchar(50) NOT NULL,
  DataPubblicazione date NOT NULL,
  Descrizione varchar(100) NOT NULL,
  Facolta varchar(20) NOT NULL,
  Categoria char(1) NOT NULL,
  Materia varchar(20) DEFAULT NULL,
  Edizione int(11) DEFAULT NULL,
  ISBN varchar(13) DEFAULT NULL,
  Autore varchar(20) DEFAULT NULL,
  Condizione varchar(20) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (Email) REFERENCES account (Email)
);

CREATE TABLE IF NOT EXISTS amministratore (
  Email varchar(40) NOT NULL,
  Password varchar(20) NOT NULL,
  PRIMARY KEY (Email)
);


--INSERT INTO account
--VALUES ('mario1996','ciao','v');

--INSERT INTO profilo
--VALUES ('mario1996','mario','memoli','1996-01-24','cjfe','cava','383902','ddc');


--SELECT account.* FROM account JOIN profilo WHERE account.Email= profilo.Email AND account.Tipo='v' ORDER BY account.Email;
