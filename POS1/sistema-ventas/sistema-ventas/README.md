# POS Backend SpringBoot

Un sistema de backend para punto de venta (POS) que gestiona ventas, productos y empleados. Construido con Spring Boot y MySQL.

## Tecnologías

- **Java 17+**
- **Spring Boot 3.x**
- **MySQL 8.0**
- **JPA/Hibernate**
- **Maven**

## Requisitos

- Java 11 o superior
- MySQL 8.0 instalado y corriendo
- Maven (o usa el Maven Wrapper incluido)

## Instalación

1. Clona el repositorio:
```bash
git clone https://github.com/tu_usuario/POS-Backend-SBoot.git
cd POS-Backend-SBoot
```

2. Configura la conexión a MySQL en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_datos
spring.datasource.username=root
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
```

3. Construye el proyecto:
```bash
./mvnw clean install
```

## Cómo correr

```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

## Endpoints principales
## El borrado es lógico no físico de la base da datos

### Empleados
- `GET /api/empleados` - Listar todos los empleados
- `POST /api/empleados` - Crear un empleado
- `GET /api/empleados/{id}` - Obtener un empleado
- `PUT /api/empleados/{id}` - Actualizar un empleado
- `DELETE /api/empleados/{id}` - Eliminar un empleado

### Productos
- `GET /api/productos` - Listar todos los productos
- `POST /api/productos` - Crear un producto
- `GET /api/productos/{id}` - Obtener un producto
- `PUT /api/productos/{id}` - Actualizar un producto
- `DELETE /api/productos/{id}` - Eliminar un producto

### Ventas
- `GET /api/ventas` - Listar todas las ventas
- `POST /api/ventas` - Crear una venta
- `GET /api/ventas/{id}` - Obtener una venta
- `DELETE /api/ventas/{id}` - Eliminar una venta

## Estructura del proyecto

```
src/
├── main/
│   ├── java/com/example/sistema_ventas/
│   │   ├── controller/       - Controladores REST
│   │   ├── service/          - Lógica de negocio
│   │   ├── model/            - Entidades JPA
│   │   ├── repository/       - Acceso a datos
│   │   └── config/           - Configuraciones
│   └── resources/
│       └── application.properties  - Propiedades de la app
└── test/                     - Tests unitarios
```
