# Sistema de Gestión de Mascotas Exóticas

Proyecto de escritorio en Java (Swing + JDBC) que gestiona mascotas exóticas con arquitectura MVC y patrón DAO.

## Arquitectura (MVC + DAO)

Estructura actual de paquetes en `src/udistrital/avanzada/veterinaria/`:

```
control/
  logica/
    Gestor.java
    Launcher.java
  vista/
    ControladorVistaPrincipal.java
modelo/
  conexion/
    Conexion.java
  DAO/
    MascotaDAO.java
  modelo/
    MascotaVO.java
vista/
  vista/
    VistaPrincipal.java
```

## Configuración de Base de Datos

- Host: `localhost`
- Puerto: `3307`
- Base de datos: `veterinaria_exotica`
- Tabla principal: `mascotas`

Script: `data/script_bd.sql` (crea la BD y la tabla con índices y datos de ejemplo).

La aplicación usa la URL: `jdbc:mysql://localhost:3307/veterinaria_exotica` (ver `modelo/conexion/Conexion.java`).

## Ejecución

1) Crear BD y tabla:
```bash
mysql -u root -p < data/script_bd.sql
```
2) Ejecutar desde tu IDE (clase `udistrital.avanzada.veterinaria.control.logica.Launcher`) o con Ant/NetBeans.

## Funcionalidades

- Carga inicial desde `data/mascotas_iniciales.properties` (vía `Gestor`).
- CRUD completo (Adicionar, Consultar, Modificar, Eliminar) mediante `MascotaDAO`.
- Búsquedas por Apodo, Clasificación, Familia y Tipo de Alimento.
- Serialización (excluye tipo de alimento) y archivo de acceso aleatorio al salir.

## Interfaz

- `VistaPrincipal` (Swing) + `ControladorVistaPrincipal` (eventos desacoplados).
- Tabla de resultados, formulario, barra de estado y panel de búsqueda.

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

## Pruebas (resumen)

- Suite JUnit con casos de Registro, Consulta, Modificación, Eliminación, Serialización e Integración.
- Resultados y estructura detallados en `docs/resultados_pruebas_junit.md`.

## Correcciones y alineación MVC (resumen)

- Separación estricta de responsabilidades y eventos en controlador.
- Punto de entrada único en `Launcher`.
- Vista sin `main` ni lógica de negocio.
- Conexión apuntando a `veterinaria_exotica` en puerto 3307.

## Autores y versión

- Autores: AndGar03, SanSantax
- Versión: 2.0

## Archivos útiles

- `data/script_bd.sql` (BD y datos ejemplo)
- `data/mascotas_iniciales.properties` (carga inicial)
- `docs/resultados_pruebas_junit.md` (detalle de pruebas)
- `ARQUITECTURA_MVC_CORRECTA.md` y `CORRECCIONES_CRITICAS.md` (detalle extendido de arquitectura y correcciones)

