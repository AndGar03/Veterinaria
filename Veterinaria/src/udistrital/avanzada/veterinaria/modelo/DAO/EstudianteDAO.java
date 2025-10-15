/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.veterinaria.modelo.DAO;

/**
 *
 * @author Estudiantes
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import udistrital.avanzada.veterinaria.modelo.conexion.Conexion;
import udistrital.avanzada.veterinaria.modelo.modelo.EstudianteVO;

public class EstudianteDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    public EstudianteDAO() {
        con = null;
        st = null;
        rs = null;
    }
public void insertarDatos(EstudianteVO estudiante) {
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            String insercion = "INSERT INTO estudiantes VALUES('" + estudiante.getCodigo() + "','" + estudiante.getNombre() + "'," + estudiante.getEdad() + ")";
            st.executeUpdate(insercion);
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.print("No se pudo realizar la insercion");
        }
    }
public EstudianteVO consultarEstudiante(String codigo) {
        EstudianteVO estudiante = null;
        String consulta = "SELECT * FROM estudiantes where Código='" + codigo+"'";
        try {
            con = (Connection) Conexion.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                estudiante = new EstudianteVO();
                estudiante.setCodigo(rs.getString("Código"));
                estudiante.setNombre(rs.getString("Nombre"));
                estudiante.setEdad(rs.getInt("Edad"));
            }
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return estudiante;
    }
public ArrayList<EstudianteVO> listaDeEstudiantes() {
        ArrayList<EstudianteVO> misEstudiantes = new ArrayList<EstudianteVO>();
        String consulta = "SELECT * FROM estudiantes";
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                EstudianteVO estudiante = new EstudianteVO();
                estudiante.setCodigo(rs.getString("Código"));
                estudiante.setNombre(rs.getString("Nombre"));
                estudiante.setEdad(rs.getInt("Edad"));
                misEstudiantes.add(estudiante);
            }
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return misEstudiantes;
    }
public EstudianteVO consultarLetra(String nombre) {
        EstudianteVO estudiante = null;
        String consulta = "SELECT * FROM estudiantes where  Nombre like '" + nombre+"%'";
        try {
            con = (Connection) Conexion.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                estudiante = new EstudianteVO();
                estudiante.setCodigo(rs.getString("Código"));
                estudiante.setNombre(rs.getString("Nombre"));
                estudiante.setEdad(rs.getInt("Edad"));
            }
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la consulta");
        }
        return estudiante;
    }
 public boolean eliminarEstudiante(String codigo) {
        String consulta = "DELETE FROM Estudiantes where Código='" + codigo + "'";
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            st.executeUpdate(consulta);
            st.close();
            Conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la eliminacion");
        }
        return false;
    }
 public boolean modificarEstudiante(String codigo) {
        //Update estudiantes set nombre='Maria Perez' where codigo=202210200031
        String consulta = "update Estudiantes set edad=" + 45 + " where Código='" + codigo + "'";
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            st.executeUpdate(consulta);
            st.close();
            Conexion.desconectar();
            return true;
        } catch (SQLException ex) {
            System.out.println("No se pudo realizar la modifcacion");
        }
        return false;
    }
}


