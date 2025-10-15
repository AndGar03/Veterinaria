@echo off
echo Compilando Sistema de Gestion de Mascotas Exoticas...

REM Crear directorio de clases si no existe
if not exist "build\classes" mkdir "build\classes"

REM Compilar todas las clases Java
javac -cp "src;Libreria\mysql-connector-java-5.1.46.jar" -d build\classes src\udistrital\avanzada\veterinaria\modelo\*.java
javac -cp "src;Libreria\mysql-connector-java-5.1.46.jar" -d build\classes src\udistrital\avanzada\veterinaria\vista\*.java
javac -cp "src;Libreria\mysql-connector-java-5.1.46.jar" -d build\classes src\udistrital\avanzada\veterinaria\controlador\*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilacion exitosa!
    echo.
echo Para ejecutar la aplicacion:
echo java -cp "build\classes;Libreria\mysql-connector-java-5.1.46.jar" udistrital.avanzada.veterinaria.controlador.Launcher
) else (
    echo Error en la compilacion!
)

pause
