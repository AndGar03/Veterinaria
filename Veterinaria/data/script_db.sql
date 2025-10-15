-- Script SQL para crear la tabla de mascotas en SQLite
-- Este archivo contiene la estructura de la base de datos para el sistema de gestión de mascotas exóticas

-- Crear la tabla mascotas
CREATE TABLE IF NOT EXISTS mascotas (
    nombre_comun TEXT NOT NULL,           -- Nombre común de la mascota
    apodo TEXT PRIMARY KEY,               -- Apodo único de la mascota (clave primaria)
    clasificacion TEXT NOT NULL,          -- Clasificación taxonómica (REPTIL, MAMIFERO, AVE, etc.)
    familia TEXT NOT NULL,                -- Familia taxonómica
    genero TEXT NOT NULL,                 -- Género taxonómico
    especie TEXT NOT NULL,                -- Especie taxonómica
    tipo_alimento TEXT NOT NULL           -- Tipo de alimento principal (LACTEOS, CARNES, etc.)
);

-- Comentarios sobre la estructura:
-- - nombre_comun: Nombre común por el que se conoce la mascota
-- - apodo: Nombre personal único de la mascota, usado como clave primaria
-- - clasificacion: Enum con valores: REPTIL, MAMIFERO, AVE, ANFIBIO, PEZ, INVERTEBRADO
-- - familia: Familia taxonómica a la que pertenece la mascota
-- - genero: Género taxonómico
-- - especie: Especie taxonómica
-- - tipo_alimento: Enum con valores: LACTEOS, CARNES, VERDURAS, FRUTAS, FORRAJES, CEREALES, LEGUMINOSAS, OMNIVORO

-- Ejemplo de inserción de datos:
-- INSERT INTO mascotas VALUES (
--     'Iguana Verde',           -- nombre_comun
--     'Iggy',                   -- apodo
--     'REPTIL',                 -- clasificacion
--     'Iguanidae',              -- familia
--     'Iguana',                 -- genero
--     'I. iguana',              -- especie
--     'VERDURAS'                -- tipo_alimento
-- );
