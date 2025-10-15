package udistrital.avanzada.veterinaria.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de la interfaz MascotaDAO que maneja las operaciones CRUD
 * para la entidad Mascota utilizando SQLite como base de datos.
 * Utiliza PreparedStatement para prevenir inyección SQL.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
public class MascotaDAOImpl implements MascotaDAO {
    
    private ConexionBD conexionBD;
    
    /**
     * Constructor que inicializa la conexión a la base de datos
     */
    public MascotaDAOImpl() {
        this.conexionBD = ConexionBD.getInstance();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void agregarMascota(Mascota mascota) throws Exception {
        String sql = "INSERT INTO mascotas (nombre_comun, apodo, clasificacion, familia, genero, especie, tipo_alimento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, mascota.getNombreComun());
            pstmt.setString(2, mascota.getApodo());
            pstmt.setString(3, mascota.getClasificacion().name());
            pstmt.setString(4, mascota.getFamilia());
            pstmt.setString(5, mascota.getGenero());
            pstmt.setString(6, mascota.getEspecie());
            pstmt.setString(7, mascota.getTipoAlimentoPrincipal().name());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("Error al agregar mascota: " + e.getMessage());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Mascota consultarPorApodo(String apodo) throws Exception {
        String sql = "SELECT * FROM mascotas WHERE apodo = ?";
        Mascota mascota = null;
        
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, apodo);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                mascota = mapearResultSetAMascota(rs);
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al consultar mascota por apodo: " + e.getMessage());
        }
        
        return mascota;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mascota> consultarPorClasificacion(Clasificacion clasificacion) throws Exception {
        String sql = "SELECT * FROM mascotas WHERE clasificacion = ?";
        List<Mascota> mascotas = new ArrayList<>();
        
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, clasificacion.name());
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                mascotas.add(mapearResultSetAMascota(rs));
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al consultar mascotas por clasificación: " + e.getMessage());
        }
        
        return mascotas;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mascota> consultarPorFamilia(String familia) throws Exception {
        String sql = "SELECT * FROM mascotas WHERE familia = ?";
        List<Mascota> mascotas = new ArrayList<>();
        
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, familia);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                mascotas.add(mapearResultSetAMascota(rs));
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al consultar mascotas por familia: " + e.getMessage());
        }
        
        return mascotas;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mascota> consultarPorTipoAlimento(TipoAlimento tipoAlimento) throws Exception {
        String sql = "SELECT * FROM mascotas WHERE tipo_alimento = ?";
        List<Mascota> mascotas = new ArrayList<>();
        
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tipoAlimento.name());
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                mascotas.add(mapearResultSetAMascota(rs));
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al consultar mascotas por tipo de alimento: " + e.getMessage());
        }
        
        return mascotas;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Mascota> obtenerTodasLasMascotas() throws Exception {
        String sql = "SELECT * FROM mascotas ORDER BY apodo";
        List<Mascota> mascotas = new ArrayList<>();
        
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                mascotas.add(mapearResultSetAMascota(rs));
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al obtener todas las mascotas: " + e.getMessage());
        }
        
        return mascotas;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void modificarMascota(Mascota mascota) throws Exception {
        String sql = "UPDATE mascotas SET nombre_comun = ?, clasificacion = ?, familia = ?, genero = ?, especie = ?, tipo_alimento = ? WHERE apodo = ?";
        
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, mascota.getNombreComun());
            pstmt.setString(2, mascota.getClasificacion().name());
            pstmt.setString(3, mascota.getFamilia());
            pstmt.setString(4, mascota.getGenero());
            pstmt.setString(5, mascota.getEspecie());
            pstmt.setString(6, mascota.getTipoAlimentoPrincipal().name());
            pstmt.setString(7, mascota.getApodo());
            
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new Exception("No se encontró la mascota con apodo: " + mascota.getApodo());
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al modificar mascota: " + e.getMessage());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void eliminarMascota(String apodo) throws Exception {
        String sql = "DELETE FROM mascotas WHERE apodo = ?";
        
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, apodo);
            
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas == 0) {
                throw new Exception("No se encontró la mascota con apodo: " + apodo);
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al eliminar mascota: " + e.getMessage());
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existeMascota(Mascota mascota) throws Exception {
        String sql = "SELECT COUNT(*) FROM mascotas WHERE apodo = ?";
        
        try (Connection conn = conexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, mascota.getApodo());
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            
        } catch (SQLException e) {
            throw new Exception("Error al verificar existencia de mascota: " + e.getMessage());
        }
        
        return false;
    }
    
    /**
     * Mapea un ResultSet a un objeto Mascota
     * 
     * @param rs El ResultSet con los datos de la mascota
     * @return Un objeto Mascota mapeado
     * @throws SQLException Si ocurre un error al leer los datos
     */
    private Mascota mapearResultSetAMascota(ResultSet rs) throws SQLException {
        Mascota mascota = new Mascota();
        mascota.setNombreComun(rs.getString("nombre_comun"));
        mascota.setApodo(rs.getString("apodo"));
        mascota.setClasificacion(Clasificacion.valueOf(rs.getString("clasificacion")));
        mascota.setFamilia(rs.getString("familia"));
        mascota.setGenero(rs.getString("genero"));
        mascota.setEspecie(rs.getString("especie"));
        mascota.setTipoAlimentoPrincipal(TipoAlimento.valueOf(rs.getString("tipo_alimento")));
        
        return mascota;
    }
}
