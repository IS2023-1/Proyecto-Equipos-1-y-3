INSERT INTO productos (codigo, nombre, descripcion, disponibles, ruta_imagen, costo, dias_a_prestar) VALUES( '123456789112', 'Raquetas', 'Raquetas para jugar', '10', 'raquetas.png', '50', '5');
INSERT INTO productos (codigo, nombre, descripcion, disponibles, ruta_imagen, costo, dias_a_prestar) VALUES( '123456789117', 'Balon', 'Balon de futbol', '4', 'balon.png', '100', '4');
INSERT INTO productos (codigo, nombre, descripcion, disponibles, ruta_imagen, costo, dias_a_prestar) VALUES( '123456789114', 'PS5', 'Consola de videojuegos', '2', 'consola.png', '200', '2');
INSERT INTO productos (codigo, nombre, descripcion, disponibles, ruta_imagen, costo, dias_a_prestar) VALUES( '123456789115', 'Blu Ray de Animes', 'Animes para ver', '10', 'shingeji.png', '40', '7');


INSERT INTO usuarios (username, cuenta, nombre, apellido_paterno, apellido_materno, numero_cel, correo, carrera, contrasena, es_activo, pumapuntos, penalizaciones) VALUES('Armando','317058163', 'Armando', 'Aquino', 'Chapa', '5555845448', 'armandoaac@ciencias.unam.mx', 'Ciencias de la Computacion', '$2a$10$V5EME6wFFXuKDOBEeLPdhuR8u5Eef8uPTztRQP22NTTnomM9AxboW', 'true', '500', '0');
INSERT INTO usuarios (username, cuenta, nombre, apellido_paterno, apellido_materno, numero_cel, correo, carrera, contrasena, es_activo, pumapuntos, penalizaciones) VALUES('Pugberto','315083', 'Pugberto', 'Jaeger', 'Ackerman', '5528478963', 'adasdsd@ciencias.unam.mx', 'Actuaría', '$2a$10$hx59PEwCTiuAr0tQnbAQQeh6yOSas6sho1KostqYo08P7TNjyrCcG', 'true', '100', '0');
INSERT INTO usuarios (username, cuenta, nombre, apellido_paterno, apellido_materno, numero_cel, correo, carrera, contrasena, es_activo, pumapuntos, penalizaciones) VALUES('Andrea','314657123', 'Andrea', 'Reyes', 'Alonso', '1234567890', 'aaalonso@ciencias.unam.mx', 'Biologia', '$2a$10$17tRHEU5O8uXJhQVO5wbz.2fylrmDENoOEVPMOd5nlaCwyUErZlm6', 'false', '500', '0');
INSERT INTO usuarios (username, cuenta, nombre, apellido_paterno, apellido_materno, numero_cel, correo, carrera, contrasena, es_activo, pumapuntos, penalizaciones) VALUES('Ellie','315048033', 'Ellie', 'Ramirez', 'Montaño', '7987654321', 'aguass23@ciencias.unam.mx', 'Fisica', '$2a$10$e0LnQG0crDejYGSlr9f5Ae5xKawbxHPycryjc7KJJkr2VISwWcylu', 'true', '430', '0');

INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO roles (nombre) VALUES ('ROLE_PROV');
INSERT INTO roles (nombre) VALUES ('ROLE_USER');

-- Administradores
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (3, 1);

--Proveedores
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1, 2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (4, 2);

--Usuarios
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2, 3);

--Rentas
INSERT INTO rentar (fecha_de_entrega, fecha_de_renta, id_producto, id_usuario ) VALUES ('2001-11-11', '2001-12-12', '1', '1');
INSERT INTO rentar (fecha_de_entrega, fecha_de_renta, id_producto, id_usuario ) VALUES ('2001-11-11', '2001-12-12', '1', '2');
INSERT INTO rentar (fecha_de_entrega, fecha_de_renta, id_producto, id_usuario ) VALUES ('2001-11-11', '2001-12-12', '1', '3');

