# Sistema de Adopción de Mascotas (Perros y Gatos)

Este proyecto es una plataforma desarrollada en **Java** diseñada para facilitar el proceso de adopción de mascotas, como perros y gatos. La plataforma permite a los administradores gestionar las mascotas disponibles para adopción, mientras que los usuarios pueden consultar información y enviar solicitudes para adoptar.

## Características

- **Gestión de Mascotas:** Los administradores pueden agregar, editar y eliminar información sobre las mascotas disponibles para adopción.
- **Exploración de Mascotas:** Los usuarios pueden explorar una lista de mascotas, visualizar sus características y seleccionar posibles candidatos para adoptar.
- **Sistema de Autenticación:** Gestión de acceso que diferencia entre administradores y usuarios adoptantes, proporcionando diferentes privilegios de acceso según el rol.

## Requisitos Previos

Asegúrate de tener instalados los siguientes programas antes de comenzar:

- [Git](https://git-scm.com/)
- Java (JDK 17 o superior, se recomienda Java 22)
- Base de datos MySQL

## Instalación

Sigue estos pasos para configurar el proyecto en tu entorno local:

1. Clona el repositorio:
    ```bash
    git clone https://github.com/usuario/Pet-Oriented-Programming.git
    ```

2. Accede al directorio del proyecto:
    ```bash
    cd Pet-Oriented-Programming
    ```

3. Configura las variables de entorno creando un archivo `.env` en la raíz del proyecto. Añade las siguientes líneas, reemplazando los valores con los correspondientes a tu configuración:

    ```bash
    DB_URL=jdbc:mysql://localhost:3306/tu_basededatos
    USER=tu_usuario
    PASSWORD=tu_contraseña
    ```

4. Verifica que tu entorno está cargando las variables del archivo `.env` correctamente y que la base de datos MySQL está disponible y en ejecución.

5. Ejecuta el proyecto en tu entorno de desarrollo favorito. Si usas Visual Studio Code, asegúrate de instalar la extensión **Extension Pack for Java**.

## Migración de Base de Datos

El proyecto incluye un archivo de migración SQL que se encuentra en la carpeta `database` bajo el nombre `migrations.sql`. Este archivo contiene las instrucciones para crear y configurar las tablas necesarias para el sistema de adopción.

Sigue estos pasos para ejecutar la migración:

1. Accede a tu servidor de MySQL utilizando tu terminal o cualquier cliente de base de datos.
    ```bash
    mysql -u tu_usuario -p
    ```

2. Copia y pega el archivo `migrations.sql` en tu servidor de MySQL. Asegúrate de que las tablas sean creadas correctamente. El archivo `migrations.sql` incluye la creación de tablas como `mascotas`, `usuarios`, `adopciones` y `administradores`.

3. Puedes agregar datos de prueba a las tablas si deseas. Por ejemplo, puedes agregar datos de prueba para las tablas `mascotas`, `usuarios`, `adopciones` y `administradores`.

3. Verifica que las tablas y relaciones se han creado correctamente en tu base de datos.

## Contribuciones

Si deseas contribuir a este proyecto o reportar un problema, sigue estos pasos:

1. Haz un fork del repositorio y clónalo.

2. Crea una nueva rama para tu funcionalidad:
    ```bash
    git checkout -b mi-nueva-funcionalidad
    ```

3. Realiza tus cambios y haz un commit:
    ```bash
    git commit -m "Añadir mi nueva funcionalidad"
    ```

4. Sube tus cambios al repositorio:
    ```bash
    git push origin mi-nueva-funcionalidad
    ```

5. Abre un pull request en GitHub para que se revise tu contribución.
