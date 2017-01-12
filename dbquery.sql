CREATE DATABASE IF NOT EXISTS ubmplatform;
use ubmplatform;
CREATE TABLE IF NOT EXISTS account (
  Email varchar(40) NOT NULL,
  Password varchar(20) NOT NULL,
  Tipo varchar(1) NOT NULL DEFAULT 'R',
  DataInvalidazione date DEFAULT NULL,
  PRIMARY KEY (Email)
);
CREATE TABLE IF NOT EXISTS profilo (
  Email varchar(40) NOT NULL,
  Nome varchar(20) NOT NULL,
  Cognome varchar(20) NOT NULL,
  DataNascita date DEFAULT NULL,
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
  ID int(11) NOT NULL AUTO_INCREMENT,
  Email varchar(40) NOT NULL,
  Foto varchar(100) NOT NULL,
  Prezzo float unsigned NOT NULL,
  Titolo varchar(50) NOT NULL,
  DataPubblicazione datetime NOT NULL DEFAULT NOW(),
  Descrizione varchar(100) NOT NULL,
  Facolta varchar(20) NOT NULL,
  Categoria char(1) NOT NULL,
  Materia varchar(40) DEFAULT NULL,
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



--INSERT INTO account VALUES ('francesco@studenti.unisa.it','ciao','R',NULL);
--INSERT INTO account VALUES ('marco@studenti.unisa.it','ciao','R',NULL);
--INSERT INTO account VALUES ('prova@studenti.unisa.it','ciao','R',NULL);

--INSERT INTO profilo VALUES ('francesco@unisa.it','Francesco','Mogavero','1996-01-24','foto1','San Marino','3421244323','Basso e sassofono');
--INSERT INTO profilo VALUES ('marco@unisa.it','Marco','Della Medaglia','1994-02-21','foto2','Marcianise','3294121233','Chitarra e batteria');
--INSERT INTO profilo VALUES ('prova@unisa.it','Prova','Di Ubm','1980-12-31','foto3','Scampia','3333333333','Piano e politica svedese');

/* NUOVE QUERY INSERT ANNUNCIO
INSERT INTO ubmplatform.annuncio
(ID, Email, Foto, Prezzo, Titolo, DataPubblicazione, Descrizione, Facolta, Categoria, Materia, Edizione, ISBN, Autore, Condizione)
VALUES(1, 'francesco@studenti.unisa.it', 'libro.jpg', 5, 'Object Oriented Software Engineering', NOW(), 'Il libro del corso di IS', 'Informatica', 'L', null, 1, 'ISBNISBNISBN1', 'Dutroit', 'Ottima');

INSERT INTO ubmplatform.annuncio
(ID, Email, Foto, Prezzo, Titolo, DataPubblicazione, Descrizione, Facolta, Categoria, Materia, Edizione, ISBN, Autore, Condizione)
VALUES(2, 'utente@studenti.unisa.it', 'libro.jpg', 5, 'Appunti di Biologia Molecolare', '2012-12-01 16:59:27', 'Appunti del corso di Biologia', 'Biologia', 'A', "Biologia Molecolare", 0, null,null, 'Ottima');

INSERT INTO ubmplatform.annuncio
(ID, Email, Foto, Prezzo, Titolo, DataPubblicazione, Descrizione, Facolta, Categoria, Materia, Edizione, ISBN, Autore, Condizione)
VALUES(3, 'utente@studenti.unisa.it', 'libro.jpg', 3, 'Appunti Architettura degli Elaboratori', '2014-07-05 14:45:34', 'Appunti del corso di Architettura degli Elaboratori', 'Informatica', 'A', "Architettura degli elaboratori", 0, null,null, 'Buona');

INSERT INTO ubmplatform.annuncio
(ID, Email, Foto, Prezzo, Titolo, DataPubblicazione, Descrizione, Facolta, Categoria, Materia, Edizione, ISBN, Autore, Condizione)
VALUES(4, 'utente@studenti.unisa.it', 'fotoAnnuncio', 27, 'Programmazione Java', '2015-07-05 13:25:34', 'Libro del corso di Programmazione 2 del prof. Deufemia', 'Informatica', 'L', null, 0, "ISBNISBNISBNI","Roberto Bruni", 'Buona');

INSERT INTO ubmplatform.annuncio
(ID, Email, Foto, Prezzo, Titolo, DataPubblicazione, Descrizione, Facolta, Categoria, Materia, Edizione, ISBN, Autore, Condizione)
VALUES(5, 'utente@studenti.unisa.it', 'libro.jpg', 15, 'Java EE7', '2016-10-04 21:25:34', 'Libro del corso di Programmazione Distribuita', 'Informatica', 'L', null, 0, "ISBNISBNISBNI","Antonio Goncalves", 'Buona');
*/

--VECCHIE QUERY INSERT ANNUNCIO
--INSERT INTO annuncio VALUES ('1', 'prova@unisa.it', 'fotoannuncio', '23.2', 'Lezioni di C', '2017-01-04', 'Lezioni di base di C', 'Matematica', 'L', 'Informatica', '1', '22444-53', 'Prova Tartaglione', 'Sottolineato'); 
--INSERT INTO annuncio VALUES ('2', 'francesco@unisa.it', 'fotoannuncio', '13.2', 'Appunti Biologia', '2014-01-04', 'Appunti della prof X', 'Biologia', 'A', 'Biologia', null, null, null, null);
--INSERT INTO annuncio VALUES ('3', 'prova@unisa.it', 'fotoannuncio', '50.2', 'Astrofisica spaziale', '2011-01-04', 'Libro della Mondadori di Astrofisica', 'Ingegneria Nucleare', 'L', 'Astrofisica', '1', '24111-53', 'Naomi Ugami', 'Evidenziato'); 
--INSERT INTO annuncio VALUES ('4', 'marco@unisa.it', 'fotoannuncio', '563.2', 'Appunti ottimi', '2003-01-04', 'Appunti buoni per una materia', 'Informatica', 'A', null, null, null, null, null);

--INSERT INTO feedback VALUES ('francesco@unisa.it', 'marco@unisa.it', '5', 'Complimenti non me lo sarei mai aspettato', '2017-01-04');
--INSERT INTO feedback VALUES ('marco@unisa.it', 'francesco@unisa.it', '1', 'Pessimo! Che schifo!', '2017-01-04');
--INSERT INTO feedback VALUES ('prova@unisa.it', 'marco@unisa.it', '2', 'La terza pagina era strappata! Ma che combini?', '2016-11-02');