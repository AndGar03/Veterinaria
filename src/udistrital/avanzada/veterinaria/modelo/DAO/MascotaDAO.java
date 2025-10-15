package udistrital.avanzada.veterinaria.modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import udistrital.avanzada.veterinaria.modelo.conexion.Conexion;
import udistrital.avanzada.veterinaria.modelo.modelo.MascotaVO;

/**
 * Clase DAO (Data Access Object) para el manejo de operaciones CRUD
 * de mascotas exóticas en la base de datos.
 * Implementa el patrón DAO para separar la lógica de acceso a datos.
 * 
 * @author AndGar03, SanSantax
 * @version 2.0
 * @since 2024
 */
public class MascotaDAO {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Constructor por defecto de MascotaDAO.
     * Inicializa las variables de conexión en null.
     */
    public MascotaDAO() {
        con = null;
        ps = null;
        rs = null;
    }

    /**
     * Inserta una nueva mascota en la base de datos.
     * 
     * @param mascota Objeto MascotaVO con los datos a insertar
     * @return true si la inserción fue exitosa, false en caso contrario
     */
    public boolean insertarMascota(MascotaVO mascota) {
        String insercion = "INSERT INTO mascotas (id_mascota, apodo, clasificacion, familia, genero, especie, tipo_alimento, edad, peso, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConexion();
            if (con == null) return false;
            ps = con.prepareStatement(insercion);
            ps.setString(1, mascota.getIdMascota());
            ps.setString(2, mascota.getApodo());
            ps.setString(3, mascota.getClasificacion());
            ps.setString(4, mascota.getFamilia());
            ps.setString(5, mascota.getGenero());
            ps.setString(6, mascota.getEspecie());
            ps.setString(7, mascota.getTipoAlimento());
            ps.setInt(8, mascota.getEdad());
            ps.setDouble(9, mascota.getPeso());
            ps.setString(10, mascota.getObservaciones());
            
            int resultado = ps.executeUpdate();
            ps.close();
            Conexion.desconectar();
            return resultado > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Consulta una mascota por su ID.
     * 
     * @param idMascota ID de la mascota a consultar
     * @return Objeto MascotaVO si se encuentra, null en caso contrario
     */
    public MascotaVO consultarMascotaPorId(String idMascota) {
        MascotaVO mascota = null;
        String consulta = "SELECT * FROM mascotas WHERE id_mascota = ?";
        try {
            con = Conexion.getConexion();
            if (con == null) return null;
            ps = con.prepareStatement(consulta);
            ps.setString(1, idMascota);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                mascota = new MascotaVO();
                mascota.setIdMascota(rs.getString("id_mascota"));
                mascota.setApodo(rs.getString("apodo"));
                mascota.setClasificacion(rs.getString("clasificacion"));
                mascota.setFamilia(rs.getString("familia"));
                mascota.setGenero(rs.getString("genero"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setTipoAlimento(rs.getString("tipo_alimento"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setObservaciones(rs.getString("observaciones"));
            }
            ps.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            return null;
        }
        return mascota;
    }

    /**
     * Consulta mascotas por apodo (búsqueda parcial).
     * 
     * @param apodo Apodo o parte del apodo a buscar
     * @return Lista de mascotas que coinciden con el criterio
     */
    public ArrayList<MascotaVO> consultarMascotasPorApodo(String apodo) {
        ArrayList<MascotaVO> mascotas = new ArrayList<>();
        String consulta = "SELECT * FROM mascotas WHERE apodo LIKE ?";
        try {
            con = Conexion.getConexion();
            if (con == null) return mascotas;
            ps = con.prepareStatement(consulta);
            ps.setString(1, "%" + apodo + "%");
            rs = ps.executeQuery();
            
            while (rs.next()) {
                MascotaVO mascota = new MascotaVO();
                mascota.setIdMascota(rs.getString("id_mascota"));
                mascota.setApodo(rs.getString("apodo"));
                mascota.setClasificacion(rs.getString("clasificacion"));
                mascota.setFamilia(rs.getString("familia"));
                mascota.setGenero(rs.getString("genero"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setTipoAlimento(rs.getString("tipo_alimento"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setObservaciones(rs.getString("observaciones"));
                mascotas.add(mascota);
            }
            ps.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            return mascotas;
        }
        return mascotas;
    }

    /**
     * Consulta mascotas por clasificación.
     * 
     * @param clasificacion Clasificación a buscar
     * @return Lista de mascotas que pertenecen a la clasificación
     */
    public ArrayList<MascotaVO> consultarMascotasPorClasificacion(String clasificacion) {
        ArrayList<MascotaVO> mascotas = new ArrayList<>();
        String consulta = "SELECT * FROM mascotas WHERE clasificacion = ?";
        try {
            con = Conexion.getConexion();
            if (con == null) return mascotas;
            ps = con.prepareStatement(consulta);
            ps.setString(1, clasificacion);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                MascotaVO mascota = new MascotaVO();
                mascota.setIdMascota(rs.getString("id_mascota"));
                mascota.setApodo(rs.getString("apodo"));
                mascota.setClasificacion(rs.getString("clasificacion"));
                mascota.setFamilia(rs.getString("familia"));
                mascota.setGenero(rs.getString("genero"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setTipoAlimento(rs.getString("tipo_alimento"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setObservaciones(rs.getString("observaciones"));
                mascotas.add(mascota);
            }
            ps.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            return mascotas;
        }
        return mascotas;
    }

    /**
     * Consulta mascotas por familia.
     * 
     * @param familia Familia biológica a buscar
     * @return Lista de mascotas que pertenecen a la familia
     */
    public ArrayList<MascotaVO> consultarMascotasPorFamilia(String familia) {
        ArrayList<MascotaVO> mascotas = new ArrayList<>();
        String consulta = "SELECT * FROM mascotas WHERE familia = ?";
        try {
            con = Conexion.getConexion();
            if (con == null) return mascotas;
            ps = con.prepareStatement(consulta);
            ps.setString(1, familia);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                MascotaVO mascota = new MascotaVO();
                mascota.setIdMascota(rs.getString("id_mascota"));
                mascota.setApodo(rs.getString("apodo"));
                mascota.setClasificacion(rs.getString("clasificacion"));
                mascota.setFamilia(rs.getString("familia"));
                mascota.setGenero(rs.getString("genero"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setTipoAlimento(rs.getString("tipo_alimento"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setObservaciones(rs.getString("observaciones"));
                mascotas.add(mascota);
            }
            ps.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            return mascotas;
        }
        return mascotas;
    }

    /**
     * Consulta mascotas por tipo de alimento.
     * 
     * @param tipoAlimento Tipo de alimento a buscar
     * @return Lista de mascotas que consumen el tipo de alimento
     */
    public ArrayList<MascotaVO> consultarMascotasPorTipoAlimento(String tipoAlimento) {
        ArrayList<MascotaVO> mascotas = new ArrayList<>();
        String consulta = "SELECT * FROM mascotas WHERE tipo_alimento = ?";
        try {
            con = Conexion.getConexion();
            if (con == null) return mascotas;
            ps = con.prepareStatement(consulta);
            ps.setString(1, tipoAlimento);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                MascotaVO mascota = new MascotaVO();
                mascota.setIdMascota(rs.getString("id_mascota"));
                mascota.setApodo(rs.getString("apodo"));
                mascota.setClasificacion(rs.getString("clasificacion"));
                mascota.setFamilia(rs.getString("familia"));
                mascota.setGenero(rs.getString("genero"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setTipoAlimento(rs.getString("tipo_alimento"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setObservaciones(rs.getString("observaciones"));
                mascotas.add(mascota);
            }
            ps.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            return mascotas;
        }
        return mascotas;
    }

    /**
     * Obtiene todas las mascotas registradas en la base de datos.
     * 
     * @return Lista con todas las mascotas
     */
    public ArrayList<MascotaVO> obtenerTodasLasMascotas() {
        ArrayList<MascotaVO> mascotas = new ArrayList<>();
        String consulta = "SELECT * FROM mascotas ORDER BY apodo";
        try {
            con = Conexion.getConexion();
            if (con == null) return mascotas;
            ps = con.prepareStatement(consulta);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                MascotaVO mascota = new MascotaVO();
                mascota.setIdMascota(rs.getString("id_mascota"));
                mascota.setApodo(rs.getString("apodo"));
                mascota.setClasificacion(rs.getString("clasificacion"));
                mascota.setFamilia(rs.getString("familia"));
                mascota.setGenero(rs.getString("genero"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setTipoAlimento(rs.getString("tipo_alimento"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setObservaciones(rs.getString("observaciones"));
                mascotas.add(mascota);
            }
            ps.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            return mascotas;
        }
        return mascotas;
    }

    /**
     * Modifica los datos de una mascota existente.
     * No permite modificar familia, género y especie según los requerimientos.
     * 
     * @param mascota Objeto MascotaVO con los datos actualizados
     * @return true si la modificación fue exitosa, false en caso contrario
     */
    public boolean modificarMascota(MascotaVO mascota) {
        String actualizacion = "UPDATE mascotas SET apodo = ?, clasificacion = ?, tipo_alimento = ?, edad = ?, peso = ?, observaciones = ? WHERE id_mascota = ?";
        try {
            con = Conexion.getConexion();
            if (con == null) return false;
            ps = con.prepareStatement(actualizacion);
            ps.setString(1, mascota.getApodo());
            ps.setString(2, mascota.getClasificacion());
            ps.setString(3, mascota.getTipoAlimento());
            ps.setInt(4, mascota.getEdad());
            ps.setDouble(5, mascota.getPeso());
            ps.setString(6, mascota.getObservaciones());
            ps.setString(7, mascota.getIdMascota());
            
            int resultado = ps.executeUpdate();
            ps.close();
            Conexion.desconectar();
            return resultado > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Elimina una mascota de la base de datos.
     * 
     * @param idMascota ID de la mascota a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean eliminarMascota(String idMascota) {
        String eliminacion = "DELETE FROM mascotas WHERE id_mascota = ?";
        try {
            con = Conexion.getConexion();
            if (con == null) return false;
            ps = con.prepareStatement(eliminacion);
            ps.setString(1, idMascota);
            
            int resultado = ps.executeUpdate();
            ps.close();
            Conexion.desconectar();
            return resultado > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    /**
     * Verifica si existe una mascota con el ID especificado.
     * 
     * @param idMascota ID a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existeMascota(String idMascota) {
        String consulta = "SELECT COUNT(*) FROM mascotas WHERE id_mascota = ?";
        try {
            con = Conexion.getConexion();
            if (con == null) return false;
            ps = con.prepareStatement(consulta);
            ps.setString(1, idMascota);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int count = rs.getInt(1);
                ps.close();
                Conexion.desconectar();
                return count > 0;
            }
            ps.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            return false;
        }
        return false;
    }
}
