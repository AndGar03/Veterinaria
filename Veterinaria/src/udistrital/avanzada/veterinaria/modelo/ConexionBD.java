package udistrital.avanzada.veterinaria.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase singleton que gestiona la conexión única a la base de datos SQLite.
 * Implementa el patrón Singleton para asegurar una única instancia de conexión.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
public class ConexionBD {
    
    private static ConexionBD instancia;
    private Connection conexion;
    private static final String URL_BD = "jdbc:sqlite:data/veterinaria.db";
    
    /**
     * Constructor privado para implementar el patrón Singleton
     */
    private ConexionBD() {
        inicializarBaseDatos();
    }
    
    /**
     * Obtiene la instancia única de ConexionBD
     * 
     * @return La instancia única de ConexionBD
     */
    public static synchronized ConexionBD getInstance() {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        return instancia;
    }
    
    /**
     * Obtiene la conexión a la base de datos
     * 
     * @return La conexión a la base de datos
     * @throws SQLException Si ocurre un error al conectar
     */
    public Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL_BD);
        }
        return conexion;
    }
    
    /**
     * Inicializa la base de datos y crea la tabla si no existe
     */
    private void inicializarBaseDatos() {
        try {
            // Crear la carpeta data si no existe
            java.io.File dataDir = new java.io.File("data");
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }
            
            // Conectar a la base de datos
            conexion = DriverManager.getConnection(URL_BD);
            
            // Crear la tabla mascotas si no existe
            crearTablaMascotas();
            
        } catch (SQLException e) {
            System.err.println("Error al inicializar la base de datos: " + e.getMessage());
        }
    }
    
    /**
     * Crea la tabla mascotas en la base de datos
     */
    private void crearTablaMascotas() {
        String sql = "CREATE TABLE IF NOT EXISTS mascotas (" +
                    "nombre_comun TEXT NOT NULL, " +
                    "apodo TEXT PRIMARY KEY, " +
                    "clasificacion TEXT NOT NULL, " +
                    "familia TEXT NOT NULL, " +
                    "genero TEXT NOT NULL, " +
                    "especie TEXT NOT NULL, " +
                    "tipo_alimento TEXT NOT NULL" +
                    ")";
        
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error al crear la tabla mascotas: " + e.getMessage());
        }
    }
    
    /**
     * Cierra la conexión a la base de datos
     */
    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
