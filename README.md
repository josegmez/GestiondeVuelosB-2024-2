# Sistema de Gestión de Vuelos B 2024-2

## Descripción del Módulo

Este módulo permite al administrador de la aerolínea gestionar los tipos de aeronaves y un conjunto de vuelos para la aerolínea (crear, editar, eliminar y consultar).

### Funcionalidades MVP

1. **Gestión de Aeronaves (CRUD):**
   - **Crear, consultar, modificar y eliminar tipos de aviones:** Permite al administrador de la aerolínea gestionar los diferentes tipos de aviones que se pueden utilizar en los vuelos. 
   - Al crear un tipo de avión se debe incluir la siguiente información:
     - **Id Aeronave**
     - **Tipo de Avión:** Ejemplo: Boeing 737, Airbus A320, etc.
     - **Cantidad Máxima de Asientos**
     - **Distribución de Asientos:** Ejemplo: 2-4-2, 3-3-3, 2-2-2.
   - Este primer CRUD se entrega como parte del sprint I.

2. **Gestión de Vuelos (CRUD):**
   - **Crear vuelos directos:** Permite al administrador de la aerolínea crear vuelos directos con la información básica detallada más adelante.
   - **Consultar vuelos:** Permite al administrador consultar los vuelos creados para modificarlos o eliminarlos. Esta funcionalidad es exclusivamente para el administrador, diferente del módulo de búsqueda de vuelos.
   - **Modificar vuelos:** Permite modificar un vuelo ya creado, con las siguientes restricciones:
     - Si el vuelo tiene reservas realizadas, sólo permite modificar el tipo de aeronave y la cantidad máxima de pasajeros.
   - **Eliminar vuelos:** Permite eliminar un vuelo ya creado, siempre y cuando no tenga reservas activas.

### Interacciones con Otros Módulos:

- Recibe del módulo de **autorización y autenticación** el token de sesión con la información de rol y permisos.
- Recibe peticiones del módulo de **búsqueda de vuelos**.
- Recibe peticiones del módulo de **gestión de asientos**.

## Integrantes

- Daniel Andrés Agudelo García  
- Paulina García Aristizábal  
- David Camilo García Echavarría  
- Juan Sebastián Ortiz Tangarife  

## Descripción

Este proyecto incluye la implementación de un sistema de gestión de vuelos con una base de datos H2 en memoria y una colección de peticiones en Postman para interactuar con la API de vuelos y aeronaves, permitiendo al administrador realizar operaciones CRUD sobre ambos recursos.

## Herramientas Utilizadas

- **IDE:** IntelliJ IDEA, Visual Studio Code
- **Back-end:** Spring Boot, Java, Apache Maven 3.5, JDK 17
- **Base de Datos:** H2
- **Pruebas de API:** Postman
- **Navegador Web:** Google Chrome

## Base de Datos

El proyecto utiliza una base de datos H2 en memoria, la cual es accesible desde la consola de H2 en el siguiente enlace:

- **URL de la Consola de H2:** [localhost:8080/h2-console](http://localhost:8080/h2-console/)
- **Usuario:** `sa`
- **Contraseña:** `sa`

### Consultas de Inserción

Para poblar la base de datos con datos de prueba, puedes utilizar las consultas de inserción disponibles en el archivo: `BD Fábrica.txt.txt`.

## Colecciones de Postman

Para realizar pruebas y validaciones del sistema, puedes importar las siguientes colecciones de Postman:

- **Flights.postman_collection.json:** Incluye todas las peticiones necesarias para interactuar con el API de vuelos, permitiendo realizar operaciones CRUD.
- **Airplanes.postman_collection.json:** Contiene las peticiones para interactuar con el API de aeronaves, incluyendo operaciones CRUD.

Estas colecciones te permitirán realizar operaciones de creación, consulta, actualización y eliminación tanto de vuelos como de tipos de aviones utilizando las peticiones REST proporcionadas en la API.

## Cómo Ejecutar el Proyecto

1. Clona este repositorio.
2. Abre el proyecto en tu IDE preferido (IntelliJ IDEA o Visual Studio Code).
3. Asegúrate de tener configurado Java JDK 17.
4. Ejecuta el proyecto utilizando Spring Boot. Esto iniciará el servidor en el puerto `8080`.
5. Accede a la base de datos H2 en la URL indicada y ejecuta las consultas de inserción para poblar la base de datos con datos iniciales.
6. Usa Postman para probar las funcionalidades con las colecciones importadas.

## Integración a GraphQL

- **URL de la Consola de GraphQL:** [localhost:8080/graphql](http://localhost:8080/graphql)
- Se recomienda utilizar Postman en lugar de la consola para facilidad de uso de Querys y Mutations