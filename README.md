# 🍰 Mil Sabores - Backend

API REST para la aplicación Mil Sabores, una plataforma de pastelería (ficticia) que permite a los usuarios explorar productos, gestionar su perfil y participar en un blog comunitario.

## 📋 Descripción

Backend desarrollado con **Spring Boot 3.5.7** que proporciona servicios de autenticación JWT con sistema de roles, gestión de productos, usuarios y publicaciones de blog.

## 🛠️ Tecnologías

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Security** - Autenticación y autorización basada en roles
- **JWT (JSON Web Tokens)** - Tokens de sesión
- **Spring Data JPA** - Persistencia de datos
- **MySQL** - Base de datos
- **Lombok** - Reducción de código boilerplate
- **Maven** - Gestión de dependencias
- **SpringDoc OpenAPI (Swagger)** - Documentación de API

## 📁 Estructura del Proyecto

```
src/main/java/com/ms_backend/mil_sabores_backend/
├── config/
│   ├── OpenApiConfig.java         # Configuración de Swagger
│   └── SecurityConfig.java        # Configuración de seguridad y CORS
├── controller/
│   ├── AuthController.java        # Endpoints de autenticación
│   ├── PostController.java        # Endpoints del blog
│   ├── ProductoController.java    # Endpoints de productos
│   └── UsuarioController.java     # Endpoints de usuarios (solo ADMIN)
├── dto/
│   ├── AuthResponse.java          # Respuesta de login (token, email, role)
│   └── LoginRequest.java          # Solicitud de login
├── filter/
│   └── JwtAuthenticationFilter.java # Filtro de autenticación JWT
├── model/
│   ├── Post.java                  # Entidad de publicación
│   ├── Producto.java              # Entidad de producto
│   ├── Role.java                  # Enum de roles (USER, ADMIN)
│   └── Usuario.java               # Entidad de usuario
├── repository/
│   ├── PostRepository.java
│   ├── ProductoRepository.java
│   └── UsuarioRepository.java
├── service/
│   ├── CustomUserDetailsService.java
│   ├── JwtService.java            # Generación y validación de JWT
│   ├── PostService.java
│   ├── ProductoService.java
│   └── UsuarioService.java
├── DataLoader.java                # Carga de datos iniciales
└── MilSaboresBackendApplication.java
```

## 👤 Sistema de Roles

La aplicación cuenta con dos roles de usuario:

| Rol | Descripción |
|-----|-------------|
| `USER` | Usuario estándar. Puede ver productos, crear posts, gestionar su perfil |
| `ADMIN` | Administrador. Puede gestionar productos, usuarios y tiene acceso completo |

### Permisos por Rol

| Funcionalidad | USER | ADMIN |
|--------------|------|-------|
| Ver productos | ✅ | ✅ |
| Crear/Editar/Eliminar productos | ❌ | ✅ |
| Ver posts del blog | ✅ | ✅ |
| Crear posts | ✅ | ✅ |
| Eliminar posts propios | ✅ | ✅ |
| Gestionar usuarios | ❌ | ✅ |
| Ver/Editar perfil propio | ✅ | ✅ |

## 🔌 Endpoints API

### 📖 Documentación Swagger

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8080/v3/api-docs

### Autenticación (`/api/auth`)

| Método | Endpoint | Descripción | Rol Requerido |
|--------|----------|-------------|---------------|
| POST | `/api/auth/login` | Iniciar sesión | Público |
| POST | `/api/auth/register` | Registrar usuario | Público |
| GET | `/api/auth/profile` | Obtener perfil del usuario | USER, ADMIN |
| PUT | `/api/auth/profile` | Actualizar perfil | USER, ADMIN |

### Productos (`/api/productos`)

| Método | Endpoint | Descripción | Rol Requerido |
|--------|----------|-------------|---------------|
| GET | `/api/productos` | Listar todos los productos | Público |
| GET | `/api/productos/{id}` | Obtener producto por ID | Público |
| POST | `/api/productos` | Crear producto | ADMIN |
| PUT | `/api/productos/{id}` | Actualizar producto | ADMIN |
| DELETE | `/api/productos/{id}` | Eliminar producto | ADMIN |

### Blog (`/api/posts`)

| Método | Endpoint | Descripción | Rol Requerido |
|--------|----------|-------------|---------------|
| GET | `/api/posts` | Listar todas las publicaciones | Público |
| POST | `/api/posts` | Crear publicación | USER, ADMIN |
| DELETE | `/api/posts/{id}` | Eliminar publicación (solo autor) | USER, ADMIN |

### Usuarios (`/api/usuarios`) - Solo Administradores

| Método | Endpoint | Descripción | Rol Requerido |
|--------|----------|-------------|---------------|
| GET | `/api/usuarios` | Listar todos los usuarios | ADMIN |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID | ADMIN |
| POST | `/api/usuarios` | Crear usuario | ADMIN |
| PUT | `/api/usuarios/{id}` | Actualizar usuario | ADMIN |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario | ADMIN |

## 🔐 Características de Seguridad

- **Autenticación JWT** con tokens de 10 horas de duración
- **Sistema de roles** (USER, ADMIN) con autorización basada en `@PreAuthorize`
- **Contraseñas encriptadas** con BCrypt
- **CORS configurado** para `http://localhost:5173` y `http://localhost:8080`
- **Endpoints protegidos** según rol de usuario

## 💰 Sistema de Descuentos

El endpoint `/api/auth/profile` proporciona datos para calcular descuentos:

- **Descuento por edad**: 50% si el usuario tiene 50+ años (`edad >= 50`)
- **Cupón felicesCincuenta**: 10% de descuento adicional
- **Torta de cumpleaños gratis**: Si es usuario DUOC y es su cumpleaños

## ⚙️ Configuración

### Requisitos Previos

- Java 21+
- MySQL 8.0+
- Maven 3.8+

### Base de Datos

Crear la base de datos MySQL:

```sql
CREATE DATABASE `mil-sabores-db`;
```

### application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mil-sabores-db?useSSL=false&serverTimezone=America/Santiago
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

### Crear Usuario Administrador

Después de registrar un usuario, ejecutar en MySQL:

```sql
UPDATE usuarios SET role = 'ADMIN' WHERE email = 'admin@ejemplo.com';
```

## 🚀 Ejecución

### Con Maven

```bash
# Desarrollo
./mvnw spring-boot:run

# Compilar
./mvnw clean package

# Ejecutar JAR
java -jar target/mil-sabores-backend-0.0.1-SNAPSHOT.jar
```

### Con IDE

Ejecutar la clase `MilSaboresBackendApplication.java`

## 📝 Ejemplos de Uso

### Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email": "usuario@ejemplo.com", "password": "123456"}'
```

Respuesta:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "email": "usuario@ejemplo.com",
  "role": "USER"
}
```

### Login como Administrador

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9...",
  "email": "admin@ejemplo.com",
  "role": "ADMIN"
}
```

### Crear Producto (solo ADMIN)

```bash
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token-admin>" \
  -d '{"nombre": "Torta Chocolate", "precio": 15000, "descripcion": "Deliciosa torta"}'
```

### Crear Publicación (USER o ADMIN)

```bash
curl -X POST http://localhost:8080/api/posts \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <token>" \
  -d '{"titulo": "Mi post", "contenido": "Contenido...", "categoria": "recetas"}'
```

## 👥 Modelo de Usuario

```json
{
  "id_user": 1,
  "nombre": "Juan Pérez",
  "email": "juan@ejemplo.com",
  "fechaNacimiento": "1990-05-15",
  "edad": 34,
  "isDuoc": true,
  "felicesCincuenta": false,
  "preferencias": "chocolate, vainilla",
  "role": "USER"
}
```

## 📊 Respuestas de Error

| Código | Descripción |
|--------|-------------|
| 400 | Bad Request - Datos inválidos |
| 401 | Unauthorized - Credenciales incorrectas |
| 403 | Forbidden - Sin permisos para esta acción |
| 404 | Not Found - Recurso no encontrado |

## 📄 Licencia

Este proyecto es parte del desarrollo académico para DUOC UC.

---

Desarrollado con ❤️ para Mil Sabores
