# Ms-Descripcion-comercial

Microservicio encargado de administrar la informacion comercial de un juego, como titulo comercial, descripcion y requisitos.

## Responsabilidad

- Crear descripcion comercial de un juego.
- Consultar descripcion por juego.
- Actualizar descripcion comercial.
- Eliminar descripcion comercial.
- Validar existencia del juego antes de guardar.

## Datos tecnicos

| Item | Valor |
| --- | --- |
| Puerto | `8091` |
| Base de datos | `descripcion_comercial_db` |
| Ruta base | `/api/v2/descripciones-comerciales` |
| Swagger | `http://localhost:8091/doc/swagger-ui.html` |
| Eureka name | `ms-descripcion-comercial` |

## Endpoints principales

- `GET /api/v2/descripciones-comerciales`
- `GET /api/v2/descripciones-comerciales/{id}`
- `GET /api/v2/descripciones-comerciales/juego/{juegoId}`
- `POST /api/v2/descripciones-comerciales`
- `PUT /api/v2/descripciones-comerciales/{id}`
- `DELETE /api/v2/descripciones-comerciales/{id}`

## Comunicacion

- Usa Feign Client para consultar juegos en `ms-juegos`.
- Se registra en Eureka.

## Ejecucion local

```bash
./mvnw spring-boot:run
```

## Ejecucion con Docker

Desde la repo `Infraestructura`:

```bash
docker compose up -d --build ms-descripcion-comercial
```

