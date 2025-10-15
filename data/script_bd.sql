-- Script SQL para crear la base de datos de mascotas exóticas
-- Universidad Distrital Francisco José de Caldas
-- Programación Avanzada - Primer Parcial

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS veterinaria_exotica;
USE veterinaria_exotica;

-- Crear la tabla de mascotas exóticas
CREATE TABLE IF NOT EXISTS mascotas (
    id_mascota VARCHAR(20) PRIMARY KEY,
    apodo VARCHAR(50) NOT NULL,
    clasificacion VARCHAR(30) NOT NULL,
    familia VARCHAR(50) NOT NULL,
    genero VARCHAR(30) NOT NULL,
    especie VARCHAR(30) NOT NULL,
    tipo_alimento VARCHAR(30) NOT NULL,
    edad INT NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    observaciones TEXT,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Crear índices para mejorar el rendimiento de las consultas
CREATE INDEX idx_apodo ON mascotas(apodo);
CREATE INDEX idx_clasificacion ON mascotas(clasificacion);
CREATE INDEX idx_familia ON mascotas(familia);
CREATE INDEX idx_tipo_alimento ON mascotas(tipo_alimento);

-- Insertar algunos datos de ejemplo
INSERT INTO mascotas (id_mascota, apodo, clasificacion, familia, genero, especie, tipo_alimento, edad, peso, observaciones) VALUES
('M001', 'Leo', 'Mamífero', 'Felidae', 'Panthera', 'leo', 'Carnívoro', 5, 180.5, 'León africano macho adulto'),
('M002', 'Luna', 'Mamífero', 'Felidae', 'Panthera', 'leo', 'Carnívoro', 4, 145.2, 'León africano hembra adulta'),
('M003', 'Spike', 'Reptil', 'Iguanidae', 'Iguana', 'iguana', 'Herbívoro', 3, 2.8, 'Iguana verde juvenil'),
('M004', 'Ruby', 'Ave', 'Psittacidae', 'Ara', 'macao', 'Omnívoro', 8, 1.2, 'Guacamayo rojo adulto'),
('M005', 'Shadow', 'Mamífero', 'Felidae', 'Panthera', 'tigris', 'Carnívoro', 6, 220.0, 'Tigre de Bengala macho');

-- Mostrar la estructura de la tabla
DESCRIBE mascotas;

-- Mostrar los datos insertados
SELECT * FROM mascotas;
