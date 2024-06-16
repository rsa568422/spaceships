# Prueba técnica Spring Boot [W2M]

### Aplicación para consultar naves espaciales

---

### Resumen
Para la implementación de la API propuesta se ha empleado Java 21 y Spring boot 3.3.0, como gestor de dependencias se
emplea Maven y para la persistencia una base de datos en memoria H2.

Comando para generar contenedor:
>docker-compose build
 
Comando para levantar la aplicación
>docker-compose up

[Swagger de la API](http://localhost:8080/doc/swagger-ui/index.html)

[Informe mutaciones PIT](./src/test/resources/report/index.html)

[Repositorio GitHub](https://github.com/rsa568422/spaceships)

---

### Requisitos
#### Este mantenimiento debe permitir:
* Consultar todas las naves utilizando paginación.
* Consultar una única nave por id.
* Consultar todas las naves que contienen, en su nombre, el valor de un parámetro enviado en
la petición. Por ejemplo, si enviamos “wing” devolverá “x-wing”.
* Crear una nueva nave.
* Modificar una nave.
* Eliminar una nave.
* Test unitario de como mínimo de una clase.
* Desarrollar un @Aspect que añada una línea de log cuando nos piden una nave con un id
negativo.
* Gestión centralizada de excepciones.
* Utilizar cachés de algún tipo.

#### Puntos a tener en cuenta:
* Las naves se deben guardar en una base de datos. Puede ser, por ejemplo, H2 en memoria.
* La prueba se debe presentar en un repositorio de Git. No hace falta que esté publicado. Se
puede enviar comprimido en un único archivo.

#### Puntos opcionales de mejora:
* Utilizar alguna librería que facilite el mantenimiento de los scripts DDL de base de datos.
* Test de integración.
* Presentar la aplicación dockerizada.
* Documentación de la API.
* Seguridad del API.
* Implementar algún consumer/producer para algún broker (Rabbit, Kafka, etc).

---

### Solución propuesta
Para la resolución del problema planteado se propone una solución siguiendo una arquitectura hexagonal en la que podamos
separar la lógica de negocio con los detalles de implementación. Para disponer de un escenario en el que hacer uso de
las relaciones entre entidades y las transacciones, se han agregado al problema original las grabaciones (series y
películas) asociadas a cada nave. En cuanto a los requisitos completados, se cumplen los requisitos mínimos, así como
varios puntos opcionales y alguno extra.

#### Puntos a tener en cuenta:
* Las naves se deben guardar en una base de datos. Puede ser, por ejemplo, H2 en memoria.
  
        Se agrega dependencia a H2 para mantener una persistencia en memoria.

* La prueba se debe presentar en un repositorio de Git. No hace falta que esté publicado. Se
  puede enviar comprimido en un único archivo.

        Se sube el proyecto a GitHub y se sigue una metodología gitflow.

#### Puntos opcionales de mejora:
* Utilizar alguna librería que facilite el mantenimiento de los scripts DDL de base de datos.

        Se hace uso de la interfaz JpaRepository proporcianada en Spring, ya que permite una implementación rapida,
        sencilla y mantenible de la persistencia mediante Hibernate.

* Test de integración.

        Se dispone de tests de integración para los controladores de los endpoint de la API.

* Presentar la aplicación dockerizada.

        Se dispone de fichero docker-compose.yml y Dockerfile.

* Documentación de la API.

        Se agrega la dependencia springdoc para facilitar la generación de documentación mediante Swagger.

* Seguridad del API.

        PUNTO PENDIENTE DE REVISIÓN: Se genera una rama de trabajo feature/security con la dependencia y cofiguración
        necesaria para Spring security y los componentes necesarios para el uso de persistencia en la gestión de
        usuarios. Esta rama no se entrega en el producto final ya que no compila correctamente mediante clean install al
        fallar algunos tests de integración por problemas con los permisos y la autenticación.

#### Mejoras propuestas:
* Uso de herramienta PIT para comprobar la calidad del testing

        Se hace uso de la herramienta PIT para generar mutaciones en el código y comprobar si nuestros tests son capaces
        de prevenirnos de estos cambios no deseados, adjuntando los informes generados por dicha herramienta en el
        fichero ./src/test/resources/report/index.html.