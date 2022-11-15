# Backend para Ciencias Top

## Uso
 > Puede correr un entorno de desarrollo completo con [Docker](https://www.docker.com/), no es necesario hacer configuración adicional al instalar.

Para correr el entorno de desarrollo, se ejecuta Docker compose desde el inicio de nuestro directorio de trabajo.

```bash
cd <UbicacionDelProyecto>/Proyecto-Equipos-1-y-3/ciencias-top-be
docker compose up
```

## Git

La rama de desarrollo principal debe ser Backend, jerarquicamente dependiente de la rama Main
> Todo cambio implementado en *main* debe aplicarse a *Backend* antes de continuar su desarrollo.
> Por el contrario, todo cambio que quiera ser llevado de *backend* a *main* debe ser aprobado.

### Configuración inicial

+ Clonar el repositorio. Es necesario proveer sus credenciales de Github, la contraseña ya no es permitida por lo que es necesario generar un token.

```bash
git clone https://github.com/IS2023-1/Proyecto-Equipos-1-y-3.git
```

+ Cambiar la rama base a Backend 

```bash
git checkout Backend
```

En esta rama se consolidaran los cambios funcionales dentro del backend, para hacer el desarrollo se debe crear una rama específica relacionada a la clase a trabajar. Sí no existe primero debe ser creada:
> Rama preexistente
```bash
git checkout <rama> # p. e. git checkout Backend-Producto
```

> Rama nueva
```bash
git checkout -b <rama> # p. e. git checkout -b Backend-Usuario
```

Adicionalmente durante el primer push a Github es necesario crear una rama upstream. *Observación*: Las ramas locales y las ramas remotas no son la misma, incluso si se llaman igual: **Backend =/= origin/Backend**.
```bash
git push --set-upstream origin <rama nueva (repetir nombre local)> # p. e. git push --set-upstream origin Backend-Usuario
```

## API

Para poder acceder localmente a la API es necesario tener corriendo la aplicación en Docker Compose, [*remitir a Uso*](#uso).

Se puede accesar a la API a traves del puerto **10000** (sujeto a cambio). 

```bash
localhost:10000/<endpoint>
```

### Endpoints:
**/productos**
+ /buscar/todo
  + Devuelve todos los productos disponibles. 
+ /buscar/{id}
+ /buscar/{nombre}
+ /buscar/{codigo}
+ /agregar
+ /actualizar/{id}
+ /eliminar/{id}
  
**/usuarios**
+ /buscar/todo
  + Devuelve todos los usuarios. Uso: ```localhost:10000/usuarios/buscar/todo```
+ /id/{id}
  +  Devuelve el usuario mediante su id (generado automáticamente). Uso: ```localhost:10000/usuarios/id/{id}```
+ /buscar/{nombre}
  + Devuelve el una lista de usuarios mediante su nombre. Uso: ```localhost:10000/usuarios/buscar/{nombre}```
+ /cuenta/{cuenta}
  + Devuelve un usuario mediante su numero de cuenta o trabajador. Uso: ```localhost:10000/usuarios/cuenta/{cuenta}```
+ /buscar/correo/{correo}
  + Devuelve un usuario mediante su correo. Uso: ```localhost:10000/usuarios/buscar/correo/{correo}```
+ /agregar/
  + Agrega un usuario. Uso: ```localhost:10000/usuarios/agregar```
+ /editar/{id}
  + Edita un usuario a través de su id (generado automáticamente). Uso: ```localhost:10000/usuarios/editar/{id}```
+ /eliminar/{id}
  + Elimina un usuario a través de su id (generado automáticamente). Uso: ```localhost:10000/usuarios/eliminar/{id}```

**/rentar**
+ /buscar/id
+ /buscar/idProducto
(Por definir si es necesario)
  + /buscar/{codigo}
  + /buscar/{idUsuario}
+ /buscar/{numIdentificacion}
+ /nueva/{idProducto}&{idUsuario}
+ /actualizar/{numIdentificacion}
+ /eliminar/{numIdentificacion}
  
**/proveedor**
+ /buscar/{id}
+ /buscar/{numIdentificacion}

**/proveedor**
+ /buscar/{id}
+ /buscar/{numIdentificacion}

## TODO
+ Configurar el entorno local
  + Instalar Java 17
  + Instalar Maven
  + Instalar Postgres 14.5
  + Crear base de datos cienciastop_bd, con acceso de usuario: **admin** y contraseña **admin**. 
  + Clonar el repositorio local.
  + Cambiar
  + Actualizar base de datos local a cienciastop_bd
  + 
+ Actualizar los metodos que usan ids

### Endpoints 
 - [ ] /productos
