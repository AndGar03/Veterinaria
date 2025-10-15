# Sistema de Gestión de Mascotas Exóticas

## Descripción del Proyecto

Este proyecto corresponde al Primer Parcial de Programación Avanzada de la Universidad Distrital Francisco José de Caldas. El tema es la gestión de animales exóticos como mascotas, desarrollado en Java utilizando Swing para la interfaz gráfica y JDBC para la conexión a base de datos.

## Arquitectura

El proyecto sigue estrictamente la arquitectura **MVC (Model-View-Controller)** con la implementación de los patrones **DAO** y **Singleton**, aplicando los principios **SOLID** y buenas prácticas de **POO**.

### Estructura de Paquetes

```
Veterinaria/
└── Source Packages/
    ├── udistrital.avanzada.veterinaria.control.logica/
    │   ├── Gestor.java
    │   └── Launcher.java   (NO modificable)
    ├── udistrital.avanzada.veterinaria.control.vista/
    │   └── VistaPrincipal.java
    ├── udistrital.avanzada.veterinaria.modelo.DAO/
    │   └── MascotaDAO.java
    ├── udistrital.avanzada.veterinaria.modelo.conexion/
    │   └── Conexion.java   (NO modificable)
    ├── udistrital.avanzada.veterinaria.modelo.modelo/
    │   └── MascotaVO.java
    └── udistrital.avanzada.veterinaria.vista.vista/
```

## Características Principales

### Funcionalidades Implementadas

1. **Carga inicial desde archivo .properties**
   - Lectura automática de registros de animales al iniciar el sistema
   - Creación de objetos Mascota y guardado en base de datos mediante DAO
   - Validación de datos completos antes de continuar

2. **Operaciones CRUD completas**
   - **Adicionar**: Insertar nueva mascota con validación de duplicados
   - **Consultar**: Búsqueda por apodo, clasificación, familia o tipo de alimento
   - **Modificar**: Cambio de datos (excepto familia, género y especie)
   - **Eliminar**: Eliminación de mascota seleccionada con actualización de lista
   - **Serializar**: Exportación de todas las mascotas (excluyendo tipo de alimento)
   - **Salir**: Creación de archivo de acceso aleatorio con estado final
   - **Limpiar**: Vaciar campos de texto de la interfaz

3. **Interfaz gráfica Swing**
   - Sin uso de JOptionPane, System.out o Scanner
   - Paneles organizados y visualmente atractivos
   - Listeners y eventos totalmente desacoplados
   - Componentes Swing exclusivos para entrada y salida de datos

### Requerimientos Técnicos Cumplidos

- ✅ Arquitectura MVC estricta
- ✅ Patrones DAO y Singleton obligatorios
- ✅ Principios SOLID aplicados
- ✅ Documentación JavaDoc completa
- ✅ Código limpio, coherente y legible
- ✅ Pruebas unitarias JUnit implementadas

## Instalación y Configuración

### Prerrequisitos

- Java JDK 8 o superior
- MySQL Server
- IDE compatible con Java (NetBeans, Eclipse, IntelliJ)

### Configuración de Base de Datos

1. Ejecutar el script SQL ubicado en `data/script_bd.sql`
2. Verificar que la conexión esté configurada correctamente en `Conexion.java`
3. Asegurar que el puerto MySQL sea 3307 (configuración por defecto)

### Ejecución

1. Compilar el proyecto
2. Ejecutar la clase `Launcher` como punto de entrada
3. La interfaz gráfica se abrirá automáticamente

## Archivos de Datos

### Archivos de Entrada
- `data/mascotas_iniciales.properties`: Datos iniciales de mascotas exóticas
- `data/script_bd.sql`: Script de creación de base de datos

### Archivos de Salida
- `data/mascotas_serializadas.dat`: Archivo de serialización de mascotas
- `data/estado_final.dat`: Archivo de acceso aleatorio con estado final

## Pruebas Unitarias

Las pruebas unitarias están ubicadas en el paquete `control` e incluyen:

- Pruebas de registro de mascotas
- Pruebas de consulta y búsqueda
- Pruebas de modificación
- Pruebas de eliminación
- Pruebas de serialización
- Pruebas de integración CRUD

### Ejecutar Pruebas

```bash
# Compilar las pruebas
javac -cp "junit-platform-console-standalone.jar" src/udistrital/avanzada/veterinaria/control/*.java

# Ejecutar las pruebas
java -jar junit-platform-console-standalone.jar --class-path src --scan-classpath
```

## Documentación

### Archivos de Documentación
- `docs/integrantes.txt`: Información del equipo de desarrollo
- `specs/`: Diagramas UML y documentación de control de versiones

### JavaDoc
Todas las clases y métodos están documentados con JavaDoc siguiendo las mejores prácticas.

## Control de Versiones

El proyecto utiliza Git con las siguientes características:
- Ramas separadas por desarrollador
- Fusiones hacia master documentadas
- Log de commits y merges visible en `/specs`

## Principios SOLID Aplicados

### Single Responsibility Principle (SRP)
- Cada clase tiene una responsabilidad específica
- Separación clara entre modelo, vista y controlador

### Open/Closed Principle (OCP)
- Extensibilidad sin modificación del código existente
- Uso de interfaces para operaciones DAO

### Liskov Substitution Principle (LSP)
- Implementación correcta de herencia y polimorfismo
- Objetos intercambiables sin afectar funcionalidad

### Interface Segregation Principle (ISP)
- Interfaces específicas y cohesivas
- Evitar dependencias innecesarias

### Dependency Inversion Principle (DIP)
- Dependencias hacia abstracciones, no concreciones
- Inyección de dependencias en el patrón DAO

## Patrones de Diseño Implementados

### DAO (Data Access Object)
- Separación de la lógica de acceso a datos
- Abstracción de operaciones de base de datos

### Singleton
- Gestión única de conexiones a base de datos
- Control de recursos compartidos

### MVC (Model-View-Controller)
- Separación de responsabilidades
- Desacoplamiento entre componentes

## Consideraciones de Seguridad

- Uso de PreparedStatement para prevenir SQL Injection
- Validación de entrada de datos
- Manejo seguro de excepciones
- No exposición de credenciales sensibles

## Limitaciones y Restricciones

- No se permite modificar `Launcher.java` ni `Conexion.java`
- Estructura de paquetes debe mantenerse exactamente igual
- No uso de JOptionPane, System.out o Scanner en la vista
- Modificación de familia, género y especie no permitida

## Contribución

Para contribuir al proyecto:

1. Crear una rama separada para cada desarrollador
2. Seguir las convenciones de código establecidas
3. Documentar todos los cambios con JavaDoc
4. Ejecutar pruebas unitarias antes de commit
5. Documentar merges y cambios importantes

## Licencia

Este proyecto es desarrollado como parte del curso de Programación Avanzada de la Universidad Distrital Francisco José de Caldas.

## Contacto

Para consultas sobre el proyecto, contactar al equipo de desarrollo a través de los canales oficiales de la universidad.
