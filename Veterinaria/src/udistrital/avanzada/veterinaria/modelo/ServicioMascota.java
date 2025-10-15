package udistrital.avanzada.veterinaria.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Servicio que maneja la lógica de negocio relacionada con la carga y procesamiento de mascotas.
 * Implementa el principio de responsabilidad única (SRP) separando la lógica de carga de datos
 * de la lógica de presentación.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
public class ServicioMascota {
    
    private static final String ARCHIVO_MASCOTAS = "data/mascotas.properties";
    private MascotaDAO mascotaDAO;
    
    /**
     * Constructor que inicializa el servicio con el DAO
     * 
     * @param mascotaDAO El DAO para operaciones de base de datos
     */
    public ServicioMascota(MascotaDAO mascotaDAO) {
        this.mascotaDAO = mascotaDAO;
    }
    
    /**
     * Carga las mascotas desde el archivo de propiedades
     * 
     * @return Lista de mascotas cargadas
     * @throws IOException Si ocurre un error al leer el archivo
     */
    public List<Mascota> cargarMascotasDesdeArchivo() throws IOException {
        List<Mascota> mascotas = new ArrayList<>();
        
        // Verificar que el archivo existe
        File archivo = new File(ARCHIVO_MASCOTAS);
        if (!archivo.exists()) {
            System.out.println("Archivo " + ARCHIVO_MASCOTAS + " no encontrado. Iniciando con base de datos vacía.");
            return mascotas;
        }
        
        Properties propiedades = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            propiedades.load(reader);
        }
        
        // Procesar cada entrada del archivo
        for (String clave : propiedades.stringPropertyNames()) {
            String valor = propiedades.getProperty(clave);
            Mascota mascota = parsearLineaMascota(clave, valor);
            if (mascota != null) {
                mascotas.add(mascota);
            }
        }
        
        System.out.println("Se cargaron " + mascotas.size() + " mascotas desde el archivo");
        return mascotas;
    }
    
    /**
     * Procesa las mascotas con datos incompletos
     * 
     * @param mascotas Lista de mascotas a procesar
     * @return Lista de mascotas con datos completos
     */
    public List<Mascota> procesarMascotasIncompletas(List<Mascota> mascotas) {
        List<Mascota> mascotasCompletas = new ArrayList<>();
        
        for (Mascota mascota : mascotas) {
            if (tieneDatosIncompletos(mascota)) {
                // Marcar para completar después - la vista se encargará de esto
                mascotasCompletas.add(mascota);
            } else {
                mascotasCompletas.add(mascota);
            }
        }
        
        return mascotasCompletas;
    }
    
    /**
     * Inserta las mascotas en la base de datos
     * 
     * @param mascotas Lista de mascotas a insertar
     * @return Número de mascotas insertadas exitosamente
     */
    public int insertarMascotasEnBD(List<Mascota> mascotas) {
        int insertadas = 0;
        int duplicadas = 0;
        
        for (Mascota mascota : mascotas) {
            try {
                if (!mascotaDAO.existeMascota(mascota)) {
                    mascotaDAO.agregarMascota(mascota);
                    insertadas++;
                } else {
                    duplicadas++;
                    System.out.println("Mascota duplicada (apodo: " + mascota.getApodo() + ") - no se insertó");
                }
            } catch (Exception e) {
                System.err.println("Error al insertar mascota " + mascota.getApodo() + ": " + e.getMessage());
            }
        }
        
        System.out.println("Mascotas insertadas: " + insertadas);
        System.out.println("Mascotas duplicadas (no insertadas): " + duplicadas);
        return insertadas;
    }
    
    /**
     * Parsea una línea del archivo de propiedades en un objeto Mascota
     * 
     * @param clave La clave de la propiedad
     * @param valor El valor de la propiedad
     * @return Un objeto Mascota o null si hay error
     */
    private Mascota parsearLineaMascota(String clave, String valor) {
        try {
            String[] campos = valor.split(",");
            
            if (campos.length < 7) {
                System.out.println("Línea incompleta para " + clave + ": " + valor);
                // Crear mascota con campos vacíos para completar después
                return crearMascotaIncompleta(campos);
            }
            
            // Crear mascota completa
            Mascota mascota = new Mascota();
            mascota.setNombreComun(campos[0].trim());
            mascota.setApodo(campos[1].trim());
            mascota.setClasificacion(Clasificacion.valueOf(campos[2].trim().toUpperCase()));
            mascota.setFamilia(campos[3].trim());
            mascota.setGenero(campos[4].trim());
            mascota.setEspecie(campos[5].trim());
            mascota.setTipoAlimentoPrincipal(TipoAlimento.valueOf(campos[6].trim().toUpperCase()));
            
            return mascota;
            
        } catch (Exception e) {
            System.err.println("Error al parsear línea " + clave + ": " + valor + " - " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Crea una mascota incompleta con los campos disponibles
     * 
     * @param campos Los campos disponibles
     * @return Una mascota con campos vacíos donde faltan datos
     */
    private Mascota crearMascotaIncompleta(String[] campos) {
        Mascota mascota = new Mascota();
        
        // Asignar campos disponibles
        if (campos.length > 0) mascota.setNombreComun(campos[0].trim());
        if (campos.length > 1) mascota.setApodo(campos[1].trim());
        if (campos.length > 2 && !campos[2].trim().isEmpty()) {
            try {
                mascota.setClasificacion(Clasificacion.valueOf(campos[2].trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Dejar null para completar después
            }
        }
        if (campos.length > 3) mascota.setFamilia(campos[3].trim());
        if (campos.length > 4) mascota.setGenero(campos[4].trim());
        if (campos.length > 5) mascota.setEspecie(campos[5].trim());
        if (campos.length > 6 && !campos[6].trim().isEmpty()) {
            try {
                mascota.setTipoAlimentoPrincipal(TipoAlimento.valueOf(campos[6].trim().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // Dejar null para completar después
            }
        }
        
        return mascota;
    }
    
    /**
     * Verifica si una mascota tiene datos incompletos
     * 
     * @param mascota La mascota a verificar
     * @return true si tiene datos incompletos, false en caso contrario
     */
    public boolean tieneDatosIncompletos(Mascota mascota) {
        return mascota.getNombreComun() == null || mascota.getNombreComun().isEmpty() ||
               mascota.getApodo() == null || mascota.getApodo().isEmpty() ||
               mascota.getClasificacion() == null ||
               mascota.getFamilia() == null || mascota.getFamilia().isEmpty() ||
               mascota.getGenero() == null || mascota.getGenero().isEmpty() ||
               mascota.getEspecie() == null || mascota.getEspecie().isEmpty() ||
               mascota.getTipoAlimentoPrincipal() == null;
    }
}
