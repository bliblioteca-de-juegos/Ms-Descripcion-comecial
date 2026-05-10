CREATE TABLE descripciones_comerciales (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    juego_id BIGINT NOT NULL,
    titulo_comercial VARCHAR(150) NOT NULL,
    descripcion VARCHAR(1500) NOT NULL,
    requisitos VARCHAR(1000),
    publicada_en DATETIME NOT NULL,
    actualizada_en DATETIME,
    CONSTRAINT uk_descripcion_comercial_juego UNIQUE (juego_id)
);
