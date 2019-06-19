INSERT INTO funzionario(id, email, password, username)
VALUES (1, 'admin@example.com' , '$2a$10$H8fl2lxJIBMY46CGlT773uQDfNVYM8eFMHIbW4kRxwSgNcvAmQRJW','admin' );

INSERT INTO album(id,descrizione, titolo)
VALUES (9999999997,'Una famiglia di cacciatori', 'Felidi');

INSERT INTO album(id,descrizione, titolo)
VALUES (9999999998,'La natura è qualcosa di meraviglioso', 'Vegetazione');
	
INSERT INTO album(id,descrizione, titolo)
VALUES (9999999999,'Le migliori foto del regno dei Gorilla', 'Gorilla');
	
INSERT INTO fotografo(id, cognome, data_nascita, email, nome)
VALUES (9999999991, 'Rossi', '25/02/1998', 'mario.rossi@hotmail.com', 'Mario');
	
INSERT INTO fotografo(id, cognome, data_nascita, email, nome)
VALUES (9999999992, 'Verdi', '20/06/1996', 'marco.verdi@gmail.com', 'Marco');
	
INSERT INTO fotografo(id, cognome, data_nascita, email, nome)
VALUES (9999999993, 'Lella', '20/01/1997', 'mario.rossi@hotmail.com', 'Celeste');
	
INSERT INTO fotografo(id, cognome, data_nascita, email, nome)
VALUES (9999999994, 'Gregori', '30/11/1997', 'luca.gregori@gmail.com', 'Luca');
	
INSERT INTO fotografo(id, cognome, data_nascita, email, nome)
VALUES (9999999995, 'Rotaru', '11/07/1996', 'silviu.rotaru@outlook.com', 'Silviu');
	
INSERT INTO fotografo(id, cognome, data_nascita, email, nome)
VALUES (9999999996, 'Della Chiesa', '01/12/1994', 'antonio.dellachiesta@outlook.com', 'Antonio');
	
INSERT INTO fotografo(id, cognome, data_nascita, email, nome)
VALUES (9999999997, 'Verdini', '17/09/1995', 'dario.verdini@hotmail.it', 'Dario');
	
	
INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('43365f78-6cf8-485f-92e7-2c84b68b9d63','2012-07-17','Un cucciolo di gorilla che si nasconde nella foresta','/upload-dir/43365f78-6cf8-485f-92e7-2c84b68b9d63.jpeg','image/jpeg','Gorilla cucciolo',9999999999,9999999994);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('b5634f57-e3e9-445d-b091-764536c71d66','2018-08-19','Sicuramente sarà successo qualcosa','/upload-dir/b5634f57-e3e9-445d-b091-764536c71d66.jpeg','image/jpeg','Gorilla sconvolto',9999999999,9999999992);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('00a5473e-2782-431b-9246-1b5e63105b05','2014-09-01','Gorilla assai serio','/upload-dir/00a5473e-2782-431b-9246-1b5e63105b05.jpeg','image/jpeg','Gorilla serio',9999999999,9999999994);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('ffd0d186-4559-450b-a871-3dcc876dbb47','2015-03-17','Una foresta talmente bella da risultare magica','/upload-dir/ffd0d186-4559-450b-a871-3dcc876dbb47.jpeg','image/jpeg','Foresta magica pt1',9999999998,9999999996);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('4ecfc2f2-eb5a-486a-a1f6-e67e8014d290','2015-03-17','Una foresta talmente bella da risultare magica','/upload-dir/4ecfc2f2-eb5a-486a-a1f6-e67e8014d290.jpeg','image/jpeg','Foresta magica pt2',9999999998,9999999996);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('a7afc60-8b9c-4fda-8827-3e10fc1be95d','2016-06-24','Magnifica foresta dei Paesi Bassi','/upload-dir/0a7afc60-8b9c-4fda-8827-3e10fc1be95d.jpeg','image/jpeg','Giochi di luce',9999999998,9999999993);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('b85c2893-43df-4aa7-93af-6a95da054b88','2012-04-17','Cipressi in Toscana','/upload-dir/b85c2893-43df-4aa7-93af-6a95da054b88.jpeg','image/jpeg','Cipressi',9999999998,9999999992);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('a24e19c9-ae8b-4083-9612-934a6e996b11','2019-07-23','Alberi maestosi e giganti, si trovano in California','/upload-dir/a24e19c9-ae8b-4083-9612-934a6e996b11.jpeg','image/jpeg','Sequoie giganti',9999999998,9999999991);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('c26eefb2-f34a-491d-9d97-d5088f4fb681','2019-07-23','','/upload-dir/c26eefb2-f34a-491d-9d97-d5088f4fb681.jpeg','image/jpeg','Gorilla quasi umano',9999999999,9999999997);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('f0a9c1ff-2610-4fac-b729-830bf19a4569','2017-07-01','Gatto selvatico della savana','/upload-dir/f0a9c1ff-2610-4fac-b729-830bf19a4569.jpeg','image/jpeg','Serval',9999999997,9999999997);

INSERT INTO foto(id, data, descrizione, file_path, image_type, nome, album_id, fotografo_id)
VALUES ('de66ac80-4ba9-4cc9-b30e-4f101ecde02e','2017-07-01','Miglior arrampicatore della famiglia dei felidi','/upload-dir/de66ac80-4ba9-4cc9-b30e-4f101ecde02e.jpeg','image/jpeg','Margay',9999999997,9999999995);




INSERT INTO richiesta(id, descrizione, email)
VALUES (999999999, 'Salve sarei interessato alle vostre foto', 'francesco.grande@gmail.com');

INSERT INTO richiesta_foto(richiesta_id, foto_id)
VALUES (999999999, 'b85c2893-43df-4aa7-93af-6a95da054b88');

INSERT INTO richiesta_foto(richiesta_id, foto_id)
VALUES (999999999, 'a7afc60-8b9c-4fda-8827-3e10fc1be95d');

INSERT INTO richiesta_foto(richiesta_id, foto_id)
VALUES (999999999, 'b5634f57-e3e9-445d-b091-764536c71d66');

INSERT INTO richiesta_foto(richiesta_id, foto_id)
VALUES (999999999, 'de66ac80-4ba9-4cc9-b30e-4f101ecde02e');

INSERT INTO richiesta_foto(richiesta_id, foto_id)
VALUES (999999999, 'c26eefb2-f34a-491d-9d97-d5088f4fb681');


INSERT INTO richiesta(id, descrizione, email)
VALUES (999999998, 'Complimenti per le foto!', 'antonella.piccola@gmail.com');


INSERT INTO richiesta_foto(richiesta_id, foto_id)
VALUES (999999998, '00a5473e-2782-431b-9246-1b5e63105b05');

INSERT INTO richiesta_foto(richiesta_id, foto_id)
VALUES (999999998, 'b85c2893-43df-4aa7-93af-6a95da054b88');