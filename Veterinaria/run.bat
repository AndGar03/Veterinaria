@echo off
echo Ejecutando Sistema de Gestion de Mascotas Exoticas...

REM Verificar que las clases esten compiladas
if not exist "build\classes\udistrital\avanzada\veterinaria\controlador\Launcher.class" (
    echo Las clases no estan compiladas. Ejecutando compilacion...
    call compile.bat
    if %ERRORLEVEL% NEQ 0 (
        echo Error en la compilacion. No se puede ejecutar la aplicacion.
        pause
        exit /b 1
    )
)

REM Crear directorio data si no existe
if not exist "data" mkdir "data"

REM Ejecutar la aplicacion
java -cp "build\classes;Libreria\mysql-connector-java-5.1.46.jar" udistrital.avanzada.veterinaria.controlador.Launcher

pause
