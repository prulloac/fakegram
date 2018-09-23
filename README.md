# SpringBoot Rest Demo1 - Fakegram

## Como ejecutar

Para ejecutar este proyecto debes tener los siguientes software instalados en tu computador:

- Gradle
- Java 8

Este proyecto por defecto utiliza una base de datos H2 en un archivo ubicado en `~/fakegram`. Si el archivo no existe, el proyecto lo intentará crear de forma automática.
En caso de querer utilizar otra base de datos como MySQL o PostgreSQL, basta con incluir la dependencia correspondiente en el archivo `build.gradle`, además de modificar la URL de JDBC en `application.properties`

Para correr el proyect, basta con ejecutar 
```bash
gradle bootRun
```

Una vez ejecutado, se puede acceder a la url `http://localhost:8080/swagger-ui.html` para acceder a la documentación de la API REST generada.

### Modo de uso

Esta api emula de forma básica ciertas funcionalidades de [Instagram](https://www.instagram.com):

- Creación de usuarios
- Inicio de sesión mediante nombre de usuario o correo y contraseña
- Follow entre usuarios
- Carga de imágenes (la imagen se entrega con URL en lugar del archivo de imagen)
- Se puede dar o quitar like a una foto mediante el mismo endpoint de la API
- Etiquetar a usuarios en una foto
- Etiquetar a usuarios en un comentario
- Los usuarios pueden tener biografia
- Los usuarios pueden tener una dirección, la cual debe contar con una ubicación geográgica*
- Las fotos subidas pueden contar con ubicación geográgica*
- Las fotos subidas pueden contener #hashtags en su descripción
- Se puede buscar fotos según su id, usuario que la ha subido, ubicación geográgica* y mediante hashtag.

\* Las ubicaciones geográficas se ingresan en el siguiente formato: `lugar, pais`. Ejemplo: `Santiago, Chile`
## Estructura del proyecto

Este proyecto posee la siguiente estructura de paquetes:
```
|- controller
|- dao
|- dto
|- mapper
|- model
|- service
|- util
```

`controller` contiene los controladores de la API que exponen endpoints de consumo a clientes.

`dao`contiene las interfaces de comunicación con base de datos

`dto`contiene los objetos de transferencia de datos, los cuáles son los que comunican con los clientes

`mapper`contiene los objetos transformación entre modelos y dto

`model`contiene los objetos que representan a las entidades de datos del proyecto

`service`contiene los objetos que coordinan la lófica de datos entre dao y controller

`util`contiene los objetos de utilidad miscelanea para el sistema. En este caso, solo existe la clase que se encarga de parsear los hashtag desde la descripción de las fotos