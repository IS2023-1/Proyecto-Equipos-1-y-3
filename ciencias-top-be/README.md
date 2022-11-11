# Backend para Ciencias Top

## Uso:
 > Requiere [Docker](https://www.docker.com/), no es necesario hacer configuración adicional al instalar.

Para correr el entorno de desarrollo, se ejecuta Docker compose desde el inicio de nuestro directorio de trabajo.

```bash
cd <UbicacionDelProyecto>/Proyecto-Equipos-1-y-3/ciencias-top-be
docker-compose up
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
