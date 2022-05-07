# Desarrollo api angular-springboot
## Por: Sebastian Moncaleano Mosquera
## Prueba técnica para la oferta laboral de Nexxos. 

## Manual de uso
El back corre en localhost http://localhost:8090/

El front corre en http://localhost:4200/

Para ver la Api documentacion, se debe tener la aplicación back-end corriendo y abrir http://localhost:8090/swagger-ui.html

Para tener control sobre las actualizaciones de las mercancias, se hizo uso de un trigger afer update, dicho script se encuentra en la carpeta _**[trigger_audit_mercancia.sql](/script_trigger/trigger_audit_mercancia.sql)**_

El proyecto back tiene un import.sql que al arrancar la Api (spring.jpa.hibernate.ddl-auto=create), crea todas las tablas en la BD y pobla con datos de mercancía, cargo y usuario, para hacer pruebas.

## _Pasos_: 
- Clone el respositorio en su computador
- Desde el editor de su preferencia, abra el proyecto angular-frontend (Para el desarrollo del mismo se hizo uso de Visual Studio Code) e inicielo con ng serve
- Desde el IDE de su preferencia, abra el proyecto spring-boot-backend-apirest (Spring-tool) y despliegue de manera local 
- Inicie en un navegador la ruta http://localhost:4200/
- Si desea conocer la documentación de la Api, abra http://localhost:8090/swagger-ui.html. Puede ver un ejemplo en [swagger](/ImagenesSwagger/swagger_documentation.png)

## Pruebas

- Se hizo uso de JUnit y Mockito para las pruebas, dicha imagen de la ejecución de las mismas se encuentran en [pruebaFuncional](/ImagenesPruebasJUnit_Mockito/pruebas_funcionales_mockito.png) 
- Se hizo uso de Postman para probar los endpoints, algunos ejemplos en [ImagenesPostman](/ImagenesPostman/)

## Se hizo uso de:

- Java 8
- Spring framework, Spring data, Spring web
- BD PostgreSQL
- Maven 
- JUnit
- Mockito
- Swagger

## _Se cumplió con_:
- Buenas prácticas de programación
- Manejo de excepciones
- Pruebas de unidad 
- Manejo de DTO’S y entidades
- Manejo de API’S y controladores
- Estructuración de paquetes y capas
- Inyección de dependencias
- Normalización de base de datos


## Postdata:

En el archivo _**[application.properties](/spring-boot-backend-apirest/src/main/resources/application.properties)**_ se encuentra la configuración para la conexión a la base de datos postgres, en postgresql://localhost:5432/postgres. El user es "postgres" y la contraseña es "123456".
