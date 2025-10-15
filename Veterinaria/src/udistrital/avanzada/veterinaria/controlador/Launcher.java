package udistrital.avanzada.veterinaria.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import udistrital.avanzada.veterinaria.modelo.Mascota;
import udistrital.avanzada.veterinaria.modelo.MascotaDAO;
import udistrital.avanzada.veterinaria.modelo.MascotaDAOImpl;
import udistrital.avanzada.veterinaria.modelo.ServicioMascota;
import udistrital.avanzada.veterinaria.vista.VentanaCompletarDatos;
import udistrital.avanzada.veterinaria.vista.VentanaPrincipal;

/**
 * Clase principal que inicia la aplicación de gestión de mascotas exóticas.
 * Implementa el principio de responsabilidad única (SRP) al encargarse únicamente
 * de la inicialización del programa y coordinación de componentes.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
public class Launcher {
    
    /**
     * Método principal que inicia la aplicación
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        try {
            // Inicializar componentes del modelo
            MascotaDAO mascotaDAO = new MascotaDAOImpl();
            ServicioMascota servicioMascota = new ServicioMascota(mascotaDAO);
            
            // Cargar y procesar mascotas iniciales
            cargarMascotasIniciales(servicioMascota);
            
            // Inicializar y mostrar la interfaz principal
            inicializarInterfaz(mascotaDAO);
            
        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Error al iniciar la aplicación: " + e.getMessage(), 
                "Error", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Carga las mascotas iniciales desde el archivo de propiedades
     * 
     * @param servicioMascota El servicio para manejar mascotas
     * @throws IOException Si ocurre un error al leer el archivo
     */
    private static void cargarMascotasIniciales(ServicioMascota servicioMascota) throws IOException {
        // Cargar mascotas desde archivo
        List<Mascota> mascotas = servicioMascota.cargarMascotasDesdeArchivo();
        
        // Procesar mascotas incompletas
        List<Mascota> mascotasCompletas = servicioMascota.procesarMascotasIncompletas(mascotas);
        
        // Completar datos incompletos si es necesario
        mascotasCompletas = completarDatosIncompletos(mascotasCompletas, servicioMascota);
        
        // Insertar en base de datos
        servicioMascota.insertarMascotasEnBD(mascotasCompletas);
    }
    
    /**
     * Completa los datos incompletos de las mascotas usando la interfaz gráfica
     * 
     * @param mascotas Lista de mascotas a procesar
     * @param servicioMascota El servicio para verificar datos incompletos
     * @return Lista de mascotas con datos completos
     */
    private static List<Mascota> completarDatosIncompletos(List<Mascota> mascotas, ServicioMascota servicioMascota) {
        List<Mascota> mascotasCompletas = new ArrayList<>();
        
        for (Mascota mascota : mascotas) {
            if (servicioMascota.tieneDatosIncompletos(mascota)) {
                // Mostrar ventana para completar datos
                VentanaCompletarDatos ventanaCompletar = new VentanaCompletarDatos(null, mascota);
                ventanaCompletar.setVisible(true);
                
                Mascota mascotaCompletada = ventanaCompletar.getMascotaCompletada();
                if (mascotaCompletada != null) {
                    mascotasCompletas.add(mascotaCompletada);
                }
            } else {
                mascotasCompletas.add(mascota);
            }
        }
        
        return mascotasCompletas;
    }
    
    /**
     * Inicializa y muestra la interfaz principal de la aplicación
     * 
     * @param mascotaDAO El DAO para operaciones de base de datos
     */
    private static void inicializarInterfaz(MascotaDAO mascotaDAO) {
        // Crear la ventana principal
        VentanaPrincipal ventana = new VentanaPrincipal();
        
        // Crear el controlador
        Controlador controlador = new Controlador(ventana);
        
        // Mostrar la ventana
        ventana.setVisible(true);
        
        System.out.println("Aplicación iniciada correctamente");
    }
}
