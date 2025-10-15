package udistrital.avanzada.veterinaria.control.logica;

import udistrital.avanzada.veterinaria.control.vista.ControladorVistaPrincipal;
import udistrital.avanzada.veterinaria.vista.vista.VistaPrincipal;

/**
 * Clase Launcher que inicia la aplicación de gestión de mascotas exóticas.
 * Esta clase no debe ser modificada según los requerimientos del proyecto.
 * 
 * @author Estudiantes - Universidad Distrital Francisco José de Caldas
 * @version 1.0
 * @since 2024
 */
public class Launcher {

    /**
     * Método principal que inicia la aplicación.
     * 
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // Crear la vista
        VistaPrincipal vista = new VistaPrincipal();
        
        // Crear el controlador que manejará los eventos
        ControladorVistaPrincipal controlador = new ControladorVistaPrincipal(vista);
        
        // Mostrar la vista
        vista.setVisible(true);
    }
}
