ViUcab
===================


Hola aquí tendrás una guía completa para el levantamiento del proyecto. Seguido de una pequeña guía para mantener buenos estandares al momento de programar, asi nos será mas fácil la lectura de nuestro codigo, tratando de fomentar las buenas practicas. 

----------
Herramientas Desarrollo y sus correspondientes Versiones
=


----------


ServerSide
-
    Intellij: IDE de JAVA
    
	JAVA Development Kit 8
  


----------


      
Dependencias
-
- [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows) (Community Edition, Version 2017.2.5)
- [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (JAVA Development Kit 8)


----------

<i class="icon-download"></i> Importando el proyecto
-------------

> **Nota:**

> - Para descargar intelliJ debes usar tu correo Ucab para obtener la licencia.
> - La licencia deberás usarla al momento de abrir el IDE por primera vez.

 - Una vez abierto el IDE navegamos *file*>*open*
 - Se desplegará una ventana donde selecionaremos la carpeta del proyecto.


----------


<i class="icon-pencil"></i> Empezando a codear
-
Llevaremos una estructura MVC, donde la implementación será la siguiente:
	

 - Persistance Layer
 	 - Será el manejador de conexciones a la DB.
	 - Estará encargado de mapear los objetos de la DB a objetos del entorno Java
	 - Naming:
		 - *NombreObjeto*Repository.java Ejemplo UserRepository.java
 - Service Layer
	 - Recibe los objetos por parte del Repositorio
	 - Es clave que en cada Service layer se use el repositorio del objeto que se maneje, en caso de tener que obtener datos de otro objeto, deberiamos usar su propio servicio, en pocas palabras en UserService.java para obtener objetos de la base de datos usaremos UserRepository.java, en caso de querer consultar otro objeto por ejemplo Video, desde UserService debemos consultar desde el servicio del objeto, VideoService por ejemplo.
	 - Es importante manejar interfaces.
	 - Naming:
		 - *NombreObjeto*Service.java Ejemplo UserService.java
		 - I*NombreObjeto*Service.java Ejemplo IUserService.java
 - Controller Layer
	 -Recibe los objetos de los Servicios, los convierte a objetos json que se envian a FrontEnd.
	 -Es importante no manejar ninguna función ni lógica de negocio en esta capa.
	 -Naming:
		 *NombreObjeto*Controller.java Ejemplo UserController.java


----------
Claves de acceso, nombre de DB estaran en "application.properties"
=


----------


Tendremos una jerarquia de esta manera:
-


![project](https://i.imgur.com/gMUSHcy.png)