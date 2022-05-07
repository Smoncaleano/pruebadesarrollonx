
/* Populate tabla cargos */

INSERT INTO cargos (nombre) VALUES('Asesor de ventas');
INSERT INTO cargos (nombre) VALUES('Administrador de ventas');
INSERT INTO cargos (nombre) VALUES('Soporte');

/* Populate tabla usuarios */
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Sebastian', 'Moncaleano', 'aronstian@gmail.com', '2022-01-01', 1);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Mr. John', 'Doe', 'john.doe@gmail.com', '2022-01-02', 2);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2022-01-03', 3);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2022-01-04', 1);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2022-01-05', 1);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2022-01-06', 3);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2022-01-07', 2);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2022-01-08', 3);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Dr. James', 'Gosling', 'james.gosling@gmail.com', '2022-01-09',2);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Magma', 'Lee', 'magma.lee@gmail.com','2022-01-10', 1);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Tornado', 'Roe', 'tornado.roe@gmail.com', '2022-01-11', 3);
INSERT INTO usuarios (nombre, apellido, email, create_at, fk_cargo) VALUES('Jade', 'Doe', 'jane.doe@gmail.com', '2022-01-12', 2);

/*Populate table mercancias*/
INSERT INTO mercancias (nombre, cantidad, create_at, fk_usuario_creador) VALUES ('CHASIS', 3, '2019-03-06', 1);
INSERT INTO mercancias (nombre, cantidad, create_at, fk_usuario_creador) VALUES ('ACEITE LUBRICANTE DELUXE', 60, '2020-03-04', 1);
INSERT INTO mercancias (nombre, cantidad, create_at, fk_usuario_creador) VALUES ('EJES GENÉRICOS', 45, '2021-03-06', 2);
INSERT INTO mercancias (nombre, cantidad, create_at, fk_usuario_creador) VALUES ('FRENOS DE TAMBOR', 2, '2022-03-06', 3);
INSERT INTO mercancias (nombre, cantidad, create_at, fk_usuario_creador) VALUES ('FRENOS DE DISCO', 60, '2020-03-04', 1);
INSERT INTO mercancias (nombre, cantidad, create_at, fk_usuario_creador) VALUES ('SERVOFRENO', 45, '2020-03-06', 2);

