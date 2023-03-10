# User Service
## Descripcion 

_Proyecto de prueba para un servicio de gestión de usuario haciendo uso de RESTful_

## Características

El proyecto se codifico en el lenguage de programación Java donde se implemento un api REST haciendo uso de Spring Boot. 

La Herramienta de contrucción seleccionada fue Gradle utilizando Groovy como lenguaje especifico de dominio,

El proyeto hace uso de una BD en memoria con HSQLDB que al ejecutarse crea el usuario, esquema, tablas, indices. Además carga dos registros de usuario 
los cuales se utilizan en las pruebas.

Para el proceso de autenticación se hizo uso de JSON Web Token (JWT) integrado con Spring Security.

El proyecto al ejecutarse levanta un servicio que utiliza el puerto 8282 de la máquina (http://localhost:8282) y el path-context es /test-user-service

EL API de usuarios responde con el path (/test-user-service/api/users) con los siguientes métodos creados:
- __POST__ - Crear Usuario (/)
- __GET__ - Obtener usuario por Id (/{userId})
- __GET__ - Obtener todos los usuarios (/)
- __PUT__ - Actualizar usuario (/{userId})
- __DELETE__ - Borrar usuario (/{userId})

El API de autenticación responde con el path (/test-user-service/auth) con el siguiente método:
- __POST__ - Acceso (/login)

Se hace uso de validaciones para verificar el formato de correo y el formato de la clave.
Las expresiones regulares usada son:

_Expresión regular para correo según la [RFC 5322](https://www.rfc-editor.org/rfc/rfc5322)_
```bash
^[a-zA-Z0-9_!#$%&'\*+/=?{|}~^.-]+@[a-zA-Z0-9.-]+$
```

_Expresión regular para la clave según [OWASP](https://owasp.org/www-community/OWASP_Validation_Regex_Repository)_
_Contraseña de 4 a 8 caracteres que requiere números y letras minúsculas y mayúsculas_
```bash
^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,8}$
```

Se realizaron pruebas unitarias con JUnit y Mockito.

La herramienta de documentación de API responde con el path (/test-user-service/swagger-ui/) haciendo uso de Swagger.

Se puede revisar un diagrama de componentes donde se visualizan las clases implementadas agrupadas conforme la estructura
del proyecto [Ver](resource_readme/Diagrama_Componentes_User_Service.png)

## Tecnologias

- [Spring](https://spring.io/) - Marco de trabajo ligero de código abierto basado en Java ampliamente utilizado para desarrollar aplicaciones empresariales el cual brinda un enfoque simplificado y modular
- [Spring Boot v2.7.8](https://spring.io/projects/spring-boot) - La tecnología de Spring Boot es una herramienta que hace que el desarrollo de aplicaciones web y microservicios con Spring Framework sea más rápido y fácil a través de tres funcionalidades principales: configuración automática, un enfoque obstinado de la configuración y la capacidad de crear aplicaciones independientes.
- [Spring Web v2.7.8](https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#web) - Módulo que permite construir aplicaciones RESTful usando Spring MVC.
- [Spring Data JPA v2.7.8](https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#data.sql.jpa-and-spring-data) - Spring Data JPA, parte de la familia Spring Data más grande, facilita la implementación de repositorios basados en JPA que en su defecto usa la implementacion de Hibernate.
- [Spring Security v2.7.8](https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#web.security) - Módulo que permite brindar una capa de seguridad implementando autenticación y autorización haciendo uso de token.
- [JJWT v0.11.2](https://github.com/jwtk/jjwt) - Libreria que permite user JJWT. JJWT tiene como objetivo ser la biblioteca más fácil de usar y comprender para crear y verificar JSON Web Tokens (JWT) en JVM.
- [Lombok v1.18.24](https://projectlombok.org/) - Libreria para la gestión automática de recursos, generación automática de getters, setters, equals, hashCode y toString, etc.
- [SpringFox v3.0.0](https://github.com/springfox/springfox) - Librería para la documentación de API JSON para aplicaciones basadas en Spring.
- [HSQLDB n2.5.2](https://docs.spring.io/spring-boot/docs/2.7.8/reference/htmlsingle/#web.security) - HyperSQL DataBase es un sistema de base de datos relacional SQL escrito en Java. Ofrece un motor de base de datos transaccional pequeño, rápido y de subprocesos múltiples con tablas en memoria y basadas en disco, y admite modos integrados y de servidor.
- [Spring Boot Gradle Plugin v2.7.8](https://docs.spring.io/spring-boot/docs/2.7.8/gradle-plugin/reference/html/) - Plugin brinda soporte de Spring Boot en Gradle.
- [Swagger v3](https://swagger.io/) - Herramienta de código abierto para diseñar, construir, documentar, y utilizar servicios web RESTful.

![img.png](resource_readme/Dependencias.PNG)

## Requerimientos

- [Gradle v8.0-bin](https://docs.gradle.org) - Herramienta de código abierto que permite la automatización de la compilación de código fuente, la cual se encuentra centrada en la flexibilidad y el rendimiento; además dispone de un sistema de gestión de dependencias sólido. Gradle basa la construcción de proyectos en algunos conceptos de Apache Ant y Apache Maven.
- [Git v2.35.1](https://git-scm.com/downloads) - Sistema de control de versiones diseñado por Linus Torvalds, pensando en la eficiencia, la confiabilidad y compatibilidad del mantenimiento de versiones de aplicaciones cuando estas tienen un gran número de archivos de código fuente.
- [Java 1.8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) - Lenguaje de programación ampliamente utilizado para codificar aplicaciones web creado por Sun Microsystems en 1995.
- [IntelliJ v2021.3.2](https://www.jetbrains.com/es-es/idea/download/#section=windows) - IDE inteligente y sensible al contexto para trabajar con Java y otros lenguajes JVM como Kotlin, Scala y Groovy en todo tipo de aplicaciones.
- [Postman v10.9.1](https://www.postman.com/downloads/) - Herramienta para realizar pruebas a la api
- [Chrome v110.0.5481.105](https://www.google.com/intl/es_es/chrome/) - Navegador web

_Nota: Se puede usar el IDE de preferencia ya sea Eclipse, Visual Studio Code, Netbeans, etc; así como el navegador web._

## Instalación

Los pasos que se tiene que seguir para la instalación y ejecución son:

__Clonar el repositorio__

```bash
git clone git@github.com:wmarcia86/test-user-service.git
git branch
git status
git log --oneline
```

__Ejecutar el proyecto con gradlew__

Desde la terminal o linea de comando entrar al directorio del proyecto ../test-user-service ejecutar el siguiente archivo conforme el sistema operativo:

En Mac o Linux
```bash
./gradlew bootRun
```

En Windows
```bash
gradlew.bat bootRun
```

__Ejecutar el proyecto a traves de Jar__

Desde la terminal o linea de comando entrar al directorio del proyecto ../test-user-service ejecutar el siguiente archivo conforme el sistema operativo:

En Mac o Linux
```bash
./gradlew bootJar
```

En Windows
```bash
gradlew.bat bootJar
```

Dentro del directorio del proyecto entrar a la carpeta ../build/libs y ejecutar el comando:

En Mac, Linux o Windows
```bash
java -jar user-service-1.0.jar
```

__Abrir el proyecto con el IDE__

Los pasos para abrir el proyecto puede variar conforme el IDE que se use en este caso menciono los pasos
que se pueden seguir para IntelliJ.

- Ejecutar IntelliJ IDEA y en la ventana de bienvenida del IDE, dar clic en el botón “Open”.
- Seleccionar el directorio donde se encuentra el proyecto y dar clic en el botón "OK".
- Esperar que el IDE configure el proyecto.

__Consumir API__

Se puede realizar pruebas haciendo uso de Postman o cualquiera otra herramienta de preferencia.

Puede descargar la colección de postman [Collection Postman](resource_readme/User-Service-Test.postman_collection.json)

El token que se puede usar para las pruebas es el siguiente y este tiene que ser agregado a la
petición en postman en la pestaña Authorization excepto para la petición al end point de acceso.

```bash
token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYWdpdGFyaW8ud2lsYmVydEBnbWFpbC5jb20iLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjc3MjI1MjQzLCJleHAiOjE2Nzk4MTcyNDN9.5v4GMjiz3zJzrWYCW0jr5lPAQsWD1XF4XMcpVE4QJLQ
```

- __Crear usuario__

```bash
Method: POST
Url: http://localhost:8282/test-user-service/api/users/
Body raw JSON: 
{
    "name": "Williams Antonio Marcia Lanzas",
    "email": "williams.marcia@gmail.com",
    "password": "mlW.2023",
    "phones": [
        {
            "number": "(505) 0001-0000",
            "citycode": "MG",
            "countrycode": "NI"
        }
    ]
}
```
![img.png](resource_readme/Postman_Crear_Usuario.PNG)

- __Obtener usuario por Id__

```bash
Method: GET
Url: http://localhost:8282/test-user-service/api/users/{userId}
Path parameter: 
{userId}: c0a83801-8682-199d-8186-826ae3720000
```
![img.png](resource_readme/Postman_Obtener_Usuario.PNG)

- __Obtener todos los usuarios__

```bash
Method: GET
Url: http://localhost:8282/test-user-service/api/users/
```
![img.png](resource_readme/Postman_Obtener_Todos_Usuario.PNG)

- __Actualizar usuario__

```bash
Method: PUT
Url: http://localhost:8282/test-user-service/api/users/{userId}
Path parameter: 
{userId}: c0a83801-8682-199d-8186-826ae3720000
Body raw JSON: 
{
    "name": "Wilbert Antonio Marcia Lanzas",
    "email": "sagitario.wilbert@gmail.com",
    "password": "Wml.2023",
    "isActive": true,
    "phones": [
        {
            "number": "(505) 1127-0000",
            "citycode": "MG",
            "countrycode": "NI",
            "isActve": true
        },
        {
            "id": "c0a83801-8682-199d-8186-826cbcbe0001",
            "number": "(505) 1129-0000",
            "citycode": "MG",
            "countrycode": "NI",
            "isActve": true
        }
    ]
}
```
![img.png](resource_readme/Postman_Actualizar_Usuario.PNG)

- __Borrar usuario__

```bash
Method: DELETE
Url: http://localhost:8282/test-user-service/api/users/{userId}
Path parameter: 
{userId}: c0a83801-8686-138f-8186-8616454b0001
```
![img.png](resource_readme/Postman_Eliminar_Usuario.PNG)

- __Acceso__

```bash
Method: POST
Url: http://localhost:8282/test-user-service/auth/login
Request Parameter: 
  email: sagitario.wilbert@gmail.com
  password: Wml.2023
```
![img.png](resource_readme/Postman_Acceso.PNG)

_Nota: Tomar en cuenta que al consumir este servicio se convierte
como token valido el que se genero. Por ende el token proporcionado 
ya no sería valido._

- __Acceso a Swagger__
En la barra del navegador poner la ruta:

```bash
Url: http://localhost:8282/swagger-ui
```
![img.png](resource_readme/Swagger.PNG)

## Autor

Ing. Wilbert Antonio Marcia Lanzas | Nicaragua | 2023

## Licencia

Apache License, Version 2.0




