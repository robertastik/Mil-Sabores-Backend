# 🍰 Mil Sabores - Backend

API REST para la aplicación Mil Sabores, una plataforma de pastelería que permite a los usuarios explorar productos, gestionar su perfil y participar en un blog comunitario.

## 📋 Descripción

Backend desarrollado con **Spring Boot 3.5.7** que proporciona servicios de autenticación JWT, gestión de productos, usuarios y publicaciones de blog.

## 🛠️ Tecnologías

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Security** - Autenticación y autorización
- **JWT (JSON Web Tokens)** - Tokens de sesión
- **Spring Data JPA** - Persistencia de datos
- **MySQL** - Base de datos
- **Lombok** - Reducción de código boilerplate
- **Maven** - Gestión de dependencias

## 📁 Estructura del Proyecto

```
src/main/java/com/ms_backend/mil_sabores_backend/
├── config/
│   └── SecurityConfig.java        # Configuración de seguridad y CORS
├── controller/
│   ├── AuthController.java        # Endpoints de autenticación
│   ├── PostController.java        # Endpoints del blog
│   ├── ProductoController.java    # Endpoints de productos
│   └── UsuarioController.java     # Endpoints de usuarios
├── dto/
│   ├── AuthResponse.java          # Respuesta de login
│   └── LoginRequest.java          # Solicitud de login
├── filter/
│   └── JwtAuthenticationFilter.java # Filtro de autenticación JWT
├── model/
│   ├── Post.java                  # Entidad de publicación
│   ├── Producto.java              # Entidad de producto
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

## 🔌 Endpoints API

### Autenticación (`/api/auth`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| POST | `/api/auth/login` | Iniciar sesión | No |
| POST | `/api/auth/register` | Registrar usuario | No |
| GET | `/api/auth/profile` | Obtener perfil del usuario | Sí |
| PUT | `/api/auth/profile` | Actualizar perfil | Sí |

### Productos (`/api/productos`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/api/productos` | Listar todos los productos | No |
| GET | `/api/productos/{id}` | Obtener producto por ID | No |
| POST | `/api/productos` | Crear producto | Sí |
| PUT | `/api/productos/{id}` | Actualizar producto | Sí |
| DELETE | `/api/productos/{id}` | Eliminar producto | Sí |

### Blog (`/api/posts`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/api/posts` | Listar todas las publicaciones | No |
| POST | `/api/posts` | Crear publicación | Sí |
| DELETE | `/api/posts/{id}` | Eliminar publicación (solo autor) | Sí |

### Usuarios (`/api/usuarios`)

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| GET | `/api/usuarios` | Listar todos los usuarios | Sí |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID | Sí |
| PUT | `/api/usuarios/{id}` | Actualizar usuario | Sí |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario | Sí |

## 🔐 Características de Seguridad

- **Autenticación JWT** con tokens de 10 horas de duración
- **Contraseñas encriptadas** con BCrypt
- **CORS configurado** para `http://localhost:5173`
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

## 📝 Ejemplo de Uso

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
  "email": "usuario@ejemplo.com"
}
```

### Crear Publicación (con token)

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
  "preferencias": "chocolate, vainilla"
}
```

## 📄 Licencia

Este proyecto es parte del desarrollo académico para DUOC UC.

---

Desarrollado con ❤️ para Mil Sabores
