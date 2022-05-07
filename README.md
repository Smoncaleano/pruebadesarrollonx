# Desarrollo api angular-springboot
## Por: Sebastian Moncaleano Mosquera
## Prueba técnica para la oferta laboral de Nexxos. 

## Manual de uso
El back corre en localhost http://localhost:8090/

El front corre en http://localhost:4200/

Para ver la Api documentacion, se debe tener la aplicación back-end corriendo y abrir http://localhost:8090/swagger-ui.html

Para tener control sobre las actualizaciones de las mercancias, se hizo uso de un trigger afer update, dicho script se encuentra en la carpeta _**[trigger_audit_mercancia.sql](/script_trigger/trigger_audit_mercancia.sql) **_

El proyecto back tiene un import.sql que al arrancar la Api (spring.jpa.hibernate.ddl-auto=create), crea todas las tablas en la BD y pobla con datos de mercancía, cargo y usuario, para hacer pruebas.


## Se hizo uso de:

- Java 8
- Spring framework, Spring data, Spring web
- BD PostgreSQL
- Maven 
- JUnit
- Mockito
- Swagger


## Postdata:

En el archivo _**[application.properties](/spring-boot-backend-apirest/src/main/resources/application.properties)**_ se encuentra la configuración para la conexión a la base de datos postgres, en postgresql://localhost:5432/postgres. El user es "postgres" y la contraseña es "123456".
