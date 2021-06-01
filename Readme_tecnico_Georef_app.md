# Solución Propuesta.: 

*Aplicación:* Georef App

*Autor:* Orlando Chaparro

*Creado:* 05/2021
***

## Índice
[1. Descripción General](#1-descripción-general)    
[2. Backend](#2-backend-java)  
    [* Tecnologías](#-tecnologías)  
    [* Instalación](#-instalación)  
    [* Base de Datos](#base-de-datos)  
    [* Funcionamiento](#funcionamiento)  
    [* End points](#end-points)  
    [* Resultados](#resultados)  
[3. Frontend](#3-frontend-angular)  
    [* Tecnologías ](#-tecnologías)   
    [* Instalación](#-instalación)    
    [* Funcionamiento](#-funcionamiento)  
[4. Consideraciones Finales](#4-consideraciones-finales)  

***

## 1. Descripción General

Nombre de la Aplicación Georef App
Consiste en una api que 
  > *consumiendo la API pública https://apis.datos.gob.ar/georef/api/provincias, retorna los valores de **lat** y **long** de una provincia por su nombre.*

Los datos abiertos corresponden a API del **Servicio de Normalización de Datos Geográficos de Argentina**  - **datos.gob.ar**
    `https://datosgobar.github.io/georef-ar-api/`


### Se divide el proyecto en dos componentes:
#### El Backend:
* En Java que consume de la API pública y devuelve los datos en formato Json.
También maneja accesos de usuario por autenticación JWT.

#### El Frontend
* En Angular que consume los datos del Backend en Java.
a travéz de una formulario web de consulta.
La autenticación de usuarios se maneja suministrando las credenciales de acceso al Backend.



## 2. Backend Java
#### GEOREF_BACK 

### * Tecnologías
* Desarrollado en Java Spring boot
* Autenticación Spring Security JWT Token
* Base de datos Mysql
* Control de dependencias vía MAVEN
* Java openjdk 15.0.2 2021-01-19


### * Instalación
 > Ubicación del codigo fuente en Github : https://github.com/orlychaparro/GEOREF_BACK
 
 Clonar el proyecto en un directorio local 
 ```bash
`git clone https://github.com/orlychaparro/GEOREF_BACK.git`
```

 Importar el proyecto en un ide para Java como por ejemplo *Eclipse*
 
 Al importar por primera vez actualiza las dependencias via *MAVEN*.
 
 **Para correr la aplicación** :
 El framework Spring boot ya cuenta con servidor de aplicaciones interna **(Apache Tomcat).** 

Se ejecuta corriendo desde el IDE como **java aplication** el archivo **GeorefApplication.java.** 

levanta en **http://localhost:8080** 
> **Obs.**: La aplicación no tiene una interfaz gráfica. en la url se muestra la pantalla por defecto.  
 
    
### Base de Datos
Para el manejo de usuarios y roles de usuarios se utiliza la base de datos Mysql. 

> Antes de levantar la Aplicación backend es necesario tener mysql funcionando para el funcionamiento de autenticación.

En el archivo **GEOREF_BACK/src/main/resources/application.properties**
están definidos los parametros de conección a la base de datos: 

```bash
jdbc:mysql://localhost:3306/
Nombre de la base de datos : crud
username root
password 12345` 
Por si se necesite cambiar.
```
Contiene tres Tablas
* rol
* usuario
* usuario_rol

En el archivo https://github.com/orlychaparro/GEOREF_BACK/blob/main/utiles_basedatos/info_basedatos.txt
están las estructuras de las tablas.
y los valores básicos necesarios como:
* usuario admin en tabla de usuario
* roles de **ROLE_ADMIN y ROLE_USER** en tabla de roles.

### Funcionamiento
Al levantar la aplicación queda disponible para recibir peticiones http en la url según los end points habilitados y responder con objetos json. 

### End points
Login de usuario para autenticar por JWT
* localhost:8080/auth/login
* localhost:8080/auth/nuevo  (creación de nuevo usuario)

Consulta Api publica Georef por nombre de Provincias
* http://localhost:8080/georef/provincia/{nombre}

    **nombre** corresponde al nombre de la Provincia a consultar



    
### Resultados

### login
> **localhost:8080/auth/login**
``

Metodo Post

Devuelve datos del usuario autenticado y token de autenticación: en formato json o negación de acceso.

* email
* nombre
* nombre_usuario
* password
* Bearer token 


```json
{"nombreUsuario" : "admin", "password":"admin"}
```


```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyMjUyNDU3NiwiZXhwIjoxNjIyNTYwNTc2fQ.5sM-PakUJRiceXISaZkD7Azx4BrIMUJRdU_q_rkbQYWknY243qC91OGaEvpwuv9IkGG5BBsN3YR1QFQQImMKnQ",
    "bearer": "Bearer",
    "nombreUsuario": "admin",
    "email": "admin@georef.com.ar",
    "nombre": "Administrador",
    "authorities": [
        {
            "authority": "ROLE_USER"
        },
        {
            "authority": "ROLE_ADMIN"
        }
    ]
}
```
Credenciales no válidas:

```json
{
    "timestamp": "2021-06-01T05:22:57.650+0000",
    "status": 401,
    "error": "Unauthorized",
    "message": "no autorizado",
    "path": "/auth/login"
}
```
### Nuevo usuario
`* localhost:8080/auth/nuevo  (creación de nuevo usuario)`

**Extra No solicitado para la Prueba**

La posibilidad de crear nuevo usuario y asignar perfil de Administrador o usuario básico.
ROLE_ADMIN
ROLE_USER

**localhost:8080/auth/nuevo**

Parámetros
```json
{"nombre":"admin", "nombreUsuario" : "admin", "email":"a@a.a","password":"admin","roles": ["admin"]}
```


Parametros: 
email
nombre
nombre_usuario
password

Resultados

```json
{
    "mensaje": "ese nombre ya existe"
}
```
```json
{
    "mensaje": "Usuario Regitrado"
}
```
 ### Consulta de Provincias
 
`http://localhost:8080/georef/provincia/nombre`

**Ej:** Bucar la provincia Córdoba
> localhost:8080/georef/provincia/cordoba

Método Post.  
Parámetros: 
Authorization type Bearer Token  
Token
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyMjUyNTU3MSwiZXhwIjoxNjIyNTYxNTcxfQ.azScfS25iohmLh_x8hmnPWAm-OMDqs-mOLKGPN4RYqobTqVTPIhGdPuKQIksbTEyQmMvEdksR04R9A2_nkEYrg

#### Resultado

##### Devuelve datos de la consulta: 
cantidad de registros encontrados, parametros enviados, datos de la provincia **Id, nombre, lon, lat, total registros**. 

Si la consulta no encuentra provincias se devuelde el Json con el campo de cantidad 0,parametros enviados, y valores de provincia sin datos, total registros 0.

```json
{"cantidad":1,"inicio":0,"parametros":{"nombre":"cordoba"},"provincias":[{"centroide":{"lat":-32.142932663607,"lon":-63.8017532741662},"id":"14","nombre":"CÃ³rdoba"}],"total":1}
```

Respuesta cuando no existe la provincia consultada


```json
{"cantidad":0,"inicio":0,"parametros":{"nombre":"cordobassss"},"provincias":[],"total":0}
```


### 3. Frontend Angular
#### GEOREF_FRONT 

### * Tecnologías 
* Desarrollado en Angular
* Bootsrap, css, typescript
* HTML

### * Instalación
> Ubicación del codigo fuente en Github : https://github.com/orlychaparro/GEOREF_FRONT
 
 Clonar el proyecto en un directorio local 
 ```bash
`git clone https://github.com/orlychaparro/GEOREF_FRONT.git`
```

 Importar el proyecto en un ide como por ejemplo *Visual Studio Code*
 
 desde el directorio de la aplicación ejecutar en una terminal de linea de comandos: npm install para instalar las dependencias y los node_modules necesarios para el proyecto
 
 Se ejecuta corriendo desde terminal el comando :  
 ```bash
ng serve -0
```  
 
> levanta en http://localhost:4200

### * Funcionamiento
Se despliega un sitio con la sgte. estructura:  
**Menu de navegación** con las sgtes. opciones **Inicio - Provincias - Iniciar Sesión/Cerrar Sesión.** 
#### Páginas del sitio  
### Inicio (pagina inicial sin diseño).    
No tiene diseño, Despliega un mensajo si no hay sesión iniciada por usuario.  
        Si hay sesión iniciada despliega nombre, email, perfil del usuario.
       
- Al Iniciar sesión, se envía los datos de usuario y password via petición http a la dirección:
       > **localhost:8080/auth/login**
``

    Metodo Post

    Devuelve datos del usuario autenticado y **token de autenticación** en formato json 
    o negación de acceso.


### Provincias  
- Despliega un cuadro de texto para ingresar el nombre de la provincia a consultar.
- Despliega los resultados de la consulta.: (Mensaje si no hay resultados)  
                - Nombre de la Provincia:   
                - Valores de Latitud y Longitud.  
      
Al completar el campo de texto  con el nombre de la provincia a consultar **(Por. ej. cordoba)****  y presionar enter:
* Se genera una petición http a la dirección del Backend
> localhost:8080/georef/provincia/cordoba

Método Post.  
Parámetros: Sen envía el Token del usuario autenticado  

Y se recibe la respuesta en formato JSON. 
que se procesa con una interface en Javascript para manipular los datos a desplegar

Se despliegan los datos de Nombre de la Provincia, Longitud y latitud.
Si no hay datos se despliega un mensaje.

### 4. Consideraciones Finales

Puntos pendientes.
* Realizar Test Unitarios
* En el backend al recuperar los datos de la API de Datos abiertos, los nombres de provincia con acento como Córdoba se obtienen con caracteres raros.
* Mejorar la presentación HTML del sitio. Inicio, menú, logo. Página Provincias.
