# PROYECTO WOMB
Trabajo de Final de Grado Superior de Desarrollo de Aplicaciones Multiplataforma. Consiste en una web sobre valoración de productos.

# REQUISITOS

* Instalación previa de Docker en su sistema operativo. Podrá encontrar la información aquí [Documentación Docker](https://docs.docker.com/desktop/). Deberá elegir el Sistema Operativo que está empleando la máquina para elegir la configuración idónea de Docker. La recomendación es que instale Docker en una distribución de Linux.
* Es recomendable instalar un Sistema Gestor de Base de Datos. Puede ser cualquiera o incluso la propia terminal de MySQL [Datagrip](https://www.jetbrains.com/help/datagrip/installation-guide.html)
* Una vez disponga de esto deberá levantar una base de datos en el puerto 3306 y ejecutar el script.sql que viene en la raíz del directorio. Ello nos proveerá de las tablas y relaciones que necesitamos para Womb.
* También deberá haber instalado Maven para el empaquetamiento del proyecto y la posterior construcción de la imagen para Docker [Maven](https://maven.apache.org/install.html)


# PASOS A SEGUIR

* Diríjase a la carpeta llamada proyectWomb:
```
cd IdeaProject/proyectWomb
```
* Una vez esté dentro ejecute el siguiente comando:
```
mvn clean package
```
* Habrá obtenido un .jar de la Api Rest. Ahora construiremos la imagen para Docker:
```
docker build -t womb .
```
* Ahora tenenmos la imagen de womb en nuestro sistema. La imagen de MySQL se descargará automáticamente de Dockerhub si no la tiene en sus sistema.

* Nos movemos a la carpeta docker que está dentro de la carpeta proyectWomb:
```
cd proyectWomb/docker
```
* Dentro de esta carpeta disponemos de un fichero llamado docker-compose.yml que levantará los contenedores de Docker que necesita. Primero la base de datos y consecuentemente la Api Rest que hemos llamado womb. Para levantar los servicios ejecute:
```
docker-compose up
```
* Ahora el Backend de Womb está operativo.

