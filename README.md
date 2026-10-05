# VetTurno

API REST para la Veterinaria Huellitas, construida con Java 17, Spring Boot, Spring Data JPA y MariaDB/MySQL.

## Configuración y ejecución

1. Copia `application-local.properties.example` como `application-local.properties`.
2. Ajusta en ese archivo la URL JDBC, el usuario, la contraseña y una clave JWT de al menos 32 caracteres.
3. Crea la base de datos `vetturno` en MariaDB/MySQL.
4. Compila con `mvn -q -DskipTests compile` y ejecuta con `mvn spring-boot:run`.

El archivo `application-local.properties` está ignorado por Git y no debe compartirse. La aplicación usa `spring.jpa.hibernate.ddl-auto=update`.

## Swagger

Con la aplicación encendida, la documentación está en:

- UI: `http://localhost:8080/swagger-ui/index.html`
- Especificación: `http://localhost:8080/v3/api-docs`

En Swagger se usa **Authorize** con el token devuelto por login. Se debe pegar solamente el JWT; Swagger agrega el esquema Bearer.

## Crear el primer administrador

El registro público siempre crea un usuario `USER`. Para crear el primer administrador:

1. Registra a Doña Marta en `POST /api/auth/register`, por ejemplo:

```json
{
  "username": "dona.marta",
  "password": "Marta1234!"
}
```

2. En MariaDB/MySQL cambia su rol:

```sql
UPDATE usuario SET rol = 'ADMIN' WHERE username = 'dona.marta';
```

3. Inicia sesión de nuevo en `POST /api/auth/login` para obtener un token con rol `ADMIN`.

## Endpoints y roles

| Método | Ruta | Rol |
|---|---|---|
| POST | `/api/auth/register` | Público |
| POST | `/api/auth/login` | Público |
| POST, GET | `/api/propietarios` | USER o ADMIN |
| POST, GET | `/api/mascotas` | USER o ADMIN |
| GET | `/api/veterinarios` | USER o ADMIN |
| POST | `/api/veterinarios` | ADMIN |
| POST, GET | `/api/citas` | USER o ADMIN |
| GET | `/api/citas/veterinario/{id}` | USER o ADMIN |

Las creaciones responden `201`. Las respuestas de negocio son DTO y nunca exponen entidades, contraseñas ni usuarios. Las peticiones protegidas usan JWT stateless.
