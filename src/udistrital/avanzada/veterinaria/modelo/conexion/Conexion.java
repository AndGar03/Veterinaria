package udistrital.avanzada.veterinaria.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection cn = null;
    private static String URLBD = "jdbc:mysql://localhost:3307/veterinaria_exotica";
    private static String usuario = "root";
    private static String contrasena = "";

    public static Connection getConexion() {
        try {
cn = DriverManager.getConnection(URLBD, usuario, contrasena);
        } catch (SQLException ex) {
            System.out.println("No se pudo establecer conexión con la base de datos: " + ex.getMessage());
        }
        return cn;
    }

    public static void desconectar() {
        cn = null;
    }
}

