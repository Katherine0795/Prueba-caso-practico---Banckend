CREATE TABLE tb_solicitud (
    idsolicitud SERIAL PRIMARY KEY,
    marca VARCHAR(255),
    tiposolicitud VARCHAR(255),
    fechaenv DATE,
    numerocontacto VARCHAR(15),
    nombrecontacto VARCHAR(85)
);

CREATE TABLE tb_contacto (
    idcontacto SERIAL PRIMARY KEY,
    idsolicitud INT,
    numerocontacto VARCHAR(15),
    nombrecontacto VARCHAR(85),
	FOREIGN KEY(idSolicitud) REFERENCES tb_solicitud(idSolicitud)
);


INSERT INTO tb_solicitud (marca, tipoSolicitud, fechaEnv, numeroContacto, nombreContacto)
VALUES
    ('MarcaA', 'Tipo1', '2025-04-02', '965412659', 'Juan Aguilar'),
    ('MarcaB', 'Tipo2', '2025-04-03', '987441026', 'Ana Salazar'),
    ('MarcaC', 'Tipo3', '2025-04-04', '966526996', 'Carlos Peralta'),
    ('MarcaD', 'Tipo4', '2025-04-05', '965585563', 'Luisa García'),
    ('MarcaE', 'Tipo5', '2025-04-06', '966200256', 'Pedro Martínez');
	
INSERT INTO tb_contacto (idSolicitud, numeroContacto, nombreContacto)
VALUES
    (1, '966637850', 'Laura Ramírez'),
    (1, '965444545', 'José Sánchez'),
    (2, '963229764', 'Marta López'),
    (2, '965478529', 'Raúl Díaz'),
    (3, '963210005', 'Elena Ruiz'),
    (4, '963256985', 'David Romero'),
    (5, '965478965', 'Sandra Martínez');
	
select * from tb_solicitud;


