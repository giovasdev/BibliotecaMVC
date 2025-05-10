# 📚 Sistema de Gestión de Bibliotecas

¡Bienvenido al Sistema de Gestión de Bibliotecas, una aplicación de escritorio moderna y fácil de usar desarrollada en Java con Swing y MySQL! 🎉 Este sistema permite gestionar libros, revistas y DVDs de manera eficiente, ideal para bibliotecarios y amantes de la tecnología.

## 🎯 Descripción del Proyecto

El Sistema de Gestión de Bibliotecas es una aplicación diseñada para simplificar la administración de una biblioteca. Con esta herramienta, puedes:

- Agregar, editar y eliminar elementos (libros 📖, revistas 📰 y DVDs 📀).
- Buscar elementos por criterios específicos (título, categoría, género).
- Visualizar detalles de los elementos en una interfaz gráfica intuitiva.

Desarrollado con una arquitectura MVC, combina Java Swing para la interfaz y MySQL para el almacenamiento de datos, garantizando robustez y escalabilidad. 🛠️

## ✨ Características

- **Soporte Multi-Elemento**: Gestiona libros, revistas y DVDs en una sola plataforma.
- **Interfaz Intuitiva**: Barra de herramientas, menús y diálogos fáciles de usar. 🖥️
- **Búsqueda Avanzada**: Encuentra elementos por título (libros), categoría (revistas) o género (DVDs). 🔍
- **Operaciones CRUD**: Crea, lee, actualiza y elimina elementos sin complicaciones.
- **Manejo de Errores**: Validación robusta con mensajes emergentes para el usuario. ⚠️
- **Integración con Base de Datos**: Almacena datos en MySQL con un esquema bien definido. 🗄️

## 🛠️ Tecnologías Utilizadas

- **Lenguaje**: Java ☕ (JDK 8 o superior)
- **Interfaz**: Java Swing
- **Base de Datos**: MySQL
- **Conector**: MySQL Connector/J
- **Arquitectura**: Modelo-Vista-Controlador (MVC)

## 📦 Instalación

¡Sigue estos pasos para configurar y ejecutar el Sistema de Gestión de Bibliotecas en tu máquina! 🚀

### Requisitos Previos

- Java Development Kit (JDK): Versión 8 o superior
- MySQL Server: Versión 5.7 o superior
- MySQL Connector/J: Driver JDBC para MySQL
- IDE: IntelliJ IDEA, Eclipse o cualquier IDE compatible con Java
- Git: Para clonar el repositorio

### Pasos de Configuración

#### Clonar el Repositorio 🐙

```bash
git clone https://github.com/giovasdev/BibliotecaMVC.git
cd sistema-gestion-bibliotecas
```

#### Configurar la Base de Datos MySQL 🗄️

1. Inicia sesión en MySQL:
```bash
mysql -u root -p
```

2. Crea la base de datos y las tablas ejecutando el script proporcionado:
```sql
SOURCE database/biblioteca_schema.sql
```

3. Actualiza las credenciales de la base de datos en `src/com/biblioteca/model/dao/ConexionBD.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/biblioteca";
private static final String USER = "root";
private static final String PASSWORD = "tu_contraseña";
```

#### Agregar MySQL Connector/J 📥

1. Descarga el archivo JAR de MySQL Connector/J desde [Maven Repository](https://mvnrepository.com).
2. Añade el JAR al classpath de tu proyecto:
    - **IntelliJ IDEA**: Archivo > Estructura del Proyecto > Bibliotecas > Añadir
    - **Eclipse**: Clic derecho en el proyecto > Ruta de Construcción > Añadir Archivos Externos

#### Compilar y Ejecutar ▶️

- Abre el proyecto en tu IDE.
- Compila y ejecuta el archivo `Main.java` ubicado en `src/com/biblioteca/Main.java`.
- ¡La ventana de la aplicación debería aparecer, lista para gestionar tu biblioteca! 🎉

## 🗄️ Esquema de la Base de Datos

La aplicación utiliza una base de datos MySQL llamada `biblioteca` con las siguientes tablas:

### ElementoBiblioteca (Tabla base para todos los elementos)

- `id`: INT, AUTO_INCREMENT, PRIMARY KEY
- `titulo`: VARCHAR(255), NOT NULL
- `autor`: VARCHAR(255), NOT NULL
- `ano_publicacion`: INT, NOT NULL
- `tipo`: ENUM('LIBRO', 'REVISTA', 'DVD'), NOT NULL

### Libro (Libros)

- `id`: INT, PRIMARY KEY, FOREIGN KEY a ElementoBiblioteca(id)
- `isbn`: VARCHAR(13), NOT NULL
- `numero_paginas`: INT, NOT NULL
- `genero`: VARCHAR(100)
- `editorial`: VARCHAR(100)

### Revista (Revistas)

- `id`: INT, PRIMARY KEY, FOREIGN KEY a ElementoBiblioteca(id)
- `numero_edicion`: INT, NOT NULL
- `categoria`: VARCHAR(100)

### DVD (DVDs)

- `id`: INT, PRIMARY KEY, FOREIGN KEY a ElementoBiblioteca(id)
- `duracion`: INT, NOT NULL
- `genero`: VARCHAR(100)

📝 **Nota**: Ejecuta el script `biblioteca_schema.sql` para crear estas tablas. Asegúrate de que el servidor MySQL esté en ejecución.

## 🚀 Uso

### Iniciar la Aplicación

- Ejecuta `Main.java` para abrir la ventana principal.

### Navegar

- Usa la barra de menús (Archivo, Catálogo, Ayuda) o la barra de herramientas para alternar entre Libros, Revistas y DVDs.

### Gestionar Elementos

- **Agregar**: Haz clic en "Agregar" para abrir un diálogo e ingresar los detalles del elemento.
- **Editar**: Selecciona un elemento en la tabla y haz clic en "Editar" para modificarlo.
- **Eliminar**: Selecciona un elemento y haz clic en "Eliminar" para borrarlo (con confirmación).
- **Buscar**: Ingresa un término en el campo de búsqueda y haz clic en "Buscar" para filtrar elementos.

### Ver Detalles

- Selecciona un elemento y usa el menú contextual (si está implementado) para ver información detallada.

## 🐛 Solución de Problemas

### Error de Conexión a la Base de Datos

- Asegúrate de que el servidor MySQL esté en ejecución y que las credenciales en `ConexionBD.java` sean correctas.
- Verifica que la base de datos `biblioteca` y las tablas existan.

### Falta el Conector de MySQL

- Añade el archivo JAR de MySQL Connector/J al classpath del proyecto.

### Problemas con la Interfaz

- Confirma que estás usando una versión compatible de JDK (8 o superior).

### Otros Errores

- Revisa la consola para ver los mensajes de error y verifica que el esquema de la base de datos coincida con el código.

---

Si necesitas ayuda, ¡abre un issue en el repositorio! 🙋‍♂️

## 🤝 Contribución

¡Nos encantaría que contribuyas a mejorar el Sistema de Gestión de Bibliotecas! 🌟

1. **Hacer un Fork del Repositorio**:  
   Haz clic en el botón "Fork" en GitHub.

2. **Crear una Rama**:
   ```bash
   git checkout -b caracteristica/tu-caracteristica
   ```

3. **Realizar Cambios**:
   Añade nuevas funcionalidades, corrige errores o mejora la documentación.

4. **Confirmar y Enviar**:
   ```bash
   git commit -m "Añadir tu caracteristica"
   git push origin caracteristica/tu-caracteristica
   ```

5. **Abrir un Pull Request**:
   Envía un PR con una descripción clara de tus cambios.

Por favor, sigue el Código de Conducta y asegúrate de que tu código cumpla con las guías de estilo del proyecto.

## 📜 Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

## 🌟 Agradecimientos

- Desarrollado con ❤️ por Giovanny Vasco.
- Gracias a las comunidades de Java y MySQL por sus increíbles herramientas y bibliotecas.
- Inspirado en la necesidad de soluciones eficientes para la gestión de bibliotecas.

**¡Feliz gestión de bibliotecas!** 📚✨
