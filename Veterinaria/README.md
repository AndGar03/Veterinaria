# Sistema de Gestión de Mascotas Exóticas

## Descripción
Sistema de escritorio desarrollado en Java para la gestión de mascotas exóticas, implementando la arquitectura MVC (Modelo-Vista-Controlador) y siguiendo los principios SOLID.

## Características Principales
- **Arquitectura MVC**: Separación clara entre modelo, vista y controlador
- **Base de datos SQLite**: Almacenamiento local de datos
- **Interfaz gráfica Swing**: Interfaz de usuario intuitiva y moderna
- **Patrón DAO**: Abstracción del acceso a datos
- **Patrón Singleton**: Gestión única de conexión a base de datos
- **Serialización**: Exportación de datos a archivos
- **Pruebas unitarias**: Cobertura de pruebas con JUnit 5

## Estructura del Proyecto

```
Veterinaria/
├── src/
│   └── udistrital/avanzada/veterinaria/
│       ├── modelo/                    # Entidades, enums y acceso a datos
│       │   ├── Mascota.java
│       │   ├── Clasificacion.java
│       │   ├── TipoAlimento.java
│       │   ├── ConexionBD.java
│       │   ├── MascotaDAO.java
│       │   ├── MascotaDAOImpl.java
│       │   └── ServicioMascota.java
│       ├── vista/                     # Interfaz de usuario
│       │   ├── VentanaPrincipal.java
│       │   └── VentanaCompletarDatos.java
│       └── controlador/               # Lógica de negocio y punto de entrada
│           ├── Controlador.java
│           └── Launcher.java
├── test/                              # Pruebas unitarias
│   └── udistrital/avanzada/veterinaria/
│       └── controlador/
│           └── ControladorTest.java
├── data/                              # Archivos de datos
│   ├── mascotas.properties
│   └── script_db.sql
├── specs/                             # Documentación
│   └── DiagramaClases.puml
└── README.md
```

## Requisitos del Sistema
- Java 8 o superior
- SQLite (incluido en el proyecto)
- JUnit 5 (para pruebas)

## Instalación y Ejecución

### 1. Compilar el proyecto
```bash
cd Veterinaria
javac -cp "src:Libreria/mysql-connector-java-5.1.46.jar" -d build/classes src/udistrital/avanzada/veterinaria/**/*.java
```

### 2. Ejecutar la aplicación
```bash
java -cp "build/classes:Libreria/mysql-connector-java-5.1.46.jar" udistrital.avanzada.veterinaria.controlador.Launcher
```

### 3. Ejecutar pruebas
```bash
java -cp "build/classes:Libreria/mysql-connector-java-5.1.46.jar:junit-platform-console-standalone.jar" org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## Funcionalidades

### Gestión de Mascotas
- **Adicionar**: Agregar nuevas mascotas al sistema
- **Consultar**: Buscar mascotas por apodo, clasificación, familia o tipo de alimento
- **Modificar**: Actualizar información de mascotas existentes
- **Eliminar**: Remover mascotas del sistema
- **Limpiar**: Limpiar campos de entrada

### Operaciones Especiales
- **Serializar a IDPYBA**: Exportar todas las mascotas a archivo serializado
- **Salir**: Guardar estado final y cerrar aplicación

### Carga Inicial
- Carga automática de mascotas desde `data/mascotas.properties`
- Interfaz para completar datos incompletos
- Inserción automática en base de datos

## Modelo de Datos

### Clase Mascota
- `nombreComun`: Nombre común de la mascota
- `apodo`: Apodo único (clave primaria)
- `clasificacion`: Clasificación taxonómica (enum)
- `familia`: Familia taxonómica
- `genero`: Género taxonómico
- `especie`: Especie taxonómica
- `tipoAlimentoPrincipal`: Tipo de alimento (enum)

### Enums
- **Clasificacion**: REPTIL, MAMIFERO, AVE, ANFIBIO, PEZ, INVERTEBRADO
- **TipoAlimento**: LACTEOS, CARNES, VERDURAS, FRUTAS, FORRAJES, CEREALES, LEGUMINOSAS, OMNIVORO

## Patrones de Diseño Implementados

### 1. MVC (Modelo-Vista-Controlador)
- **Modelo**: Entidades (Mascota, enums), acceso a datos (DAO, ConexionBD) y servicios (ServicioMascota)
- **Vista**: VentanaPrincipal y VentanaCompletarDatos (interfaces Swing)
- **Controlador**: Controlador (lógica de negocio) y Launcher (inicialización)

### 2. DAO (Data Access Object)
- Interfaz `MascotaDAO` define operaciones CRUD
- Implementación `MascotaDAOImpl` maneja SQLite

### 3. Singleton
- `ConexionBD` garantiza una única conexión a la base de datos

### 4. Service Layer
- `ServicioMascota` encapsula lógica de negocio compleja

### 5. Observer
- `ActionListener` para manejo de eventos de botones

## Principios SOLID Aplicados

1. **S** - Single Responsibility: 
   - `ServicioMascota`: Solo maneja lógica de carga y procesamiento de mascotas
   - `VentanaCompletarDatos`: Solo maneja interfaz para completar datos
   - `Launcher`: Solo maneja inicialización de la aplicación
2. **O** - Open/Closed: Extensible sin modificar código existente
   - Nuevas implementaciones de `MascotaDAO` sin modificar código existente
3. **L** - Liskov Substitution: Implementaciones DAO son intercambiables
   - `MascotaDAOImpl` puede ser reemplazada por otra implementación
4. **I** - Interface Segregation: Interfaces específicas y cohesivas
   - `MascotaDAO` define solo operaciones de acceso a datos
5. **D** - Dependency Inversion: Dependencias hacia abstracciones
   - `Controlador` depende de `MascotaDAO` (abstracción), no de `MascotaDAOImpl`
   - `ServicioMascota` recibe `MascotaDAO` como dependencia

## Archivos de Configuración

### mascotas.properties
Archivo de precarga con formato:
```
Animal1=NombreComun, Apodo, Clasificacion, Familia, Genero, Especie, TipoAlimento
```

### script_db.sql
Script SQL para crear la tabla de mascotas en SQLite.

## Pruebas Unitarias
- Cobertura completa del controlador
- Pruebas de operaciones CRUD
- Validación de campos obligatorios
- Pruebas de serialización

## Documentación
- JavaDoc completo en todas las clases públicas
- Diagrama de clases UML en PlantUML
- Documentación de métodos y parámetros

## Autor
Sistema Veterinaria - Universidad Distrital Francisco José de Caldas

## Versión
1.0

## Licencia
Proyecto académico - Uso educativo
