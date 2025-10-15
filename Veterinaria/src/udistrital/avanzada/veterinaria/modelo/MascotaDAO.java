package udistrital.avanzada.veterinaria.modelo;

import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Mascota.
 * Implementa el patrón DAO (Data Access Object) para abstraer el acceso a la base de datos.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
public interface MascotaDAO {
    
    /**
     * Agrega una nueva mascota a la base de datos
     * 
     * @param mascota La mascota a agregar
     * @throws Exception Si ocurre un error durante la inserción
     */
    void agregarMascota(Mascota mascota) throws Exception;
    
    /**
     * Consulta una mascota por su apodo
     * 
     * @param apodo El apodo de la mascota a buscar
     * @return La mascota encontrada o null si no existe
     * @throws Exception Si ocurre un error durante la consulta
     */
    Mascota consultarPorApodo(String apodo) throws Exception;
    
    /**
     * Consulta todas las mascotas de una clasificación específica
     * 
     * @param clasificacion La clasificación a buscar
     * @return Lista de mascotas que pertenecen a esa clasificación
     * @throws Exception Si ocurre un error durante la consulta
     */
    List<Mascota> consultarPorClasificacion(Clasificacion clasificacion) throws Exception;
    
    /**
     * Consulta todas las mascotas de una familia específica
     * 
     * @param familia La familia a buscar
     * @return Lista de mascotas que pertenecen a esa familia
     * @throws Exception Si ocurre un error durante la consulta
     */
    List<Mascota> consultarPorFamilia(String familia) throws Exception;
    
    /**
     * Consulta todas las mascotas que consumen un tipo de alimento específico
     * 
     * @param tipoAlimento El tipo de alimento a buscar
     * @return Lista de mascotas que consumen ese tipo de alimento
     * @throws Exception Si ocurre un error durante la consulta
     */
    List<Mascota> consultarPorTipoAlimento(TipoAlimento tipoAlimento) throws Exception;
    
    /**
     * Obtiene todas las mascotas registradas en la base de datos
     * 
     * @return Lista con todas las mascotas
     * @throws Exception Si ocurre un error durante la consulta
     */
    List<Mascota> obtenerTodasLasMascotas() throws Exception;
    
    /**
     * Modifica los datos de una mascota existente
     * 
     * @param mascota La mascota con los datos actualizados
     * @throws Exception Si ocurre un error durante la actualización
     */
    void modificarMascota(Mascota mascota) throws Exception;
    
    /**
     * Elimina una mascota de la base de datos por su apodo
     * 
     * @param apodo El apodo de la mascota a eliminar
     * @throws Exception Si ocurre un error durante la eliminación
     */
    void eliminarMascota(String apodo) throws Exception;
    
    /**
     * Verifica si una mascota ya existe en la base de datos
     * 
     * @param mascota La mascota a verificar
     * @return true si la mascota existe, false en caso contrario
     * @throws Exception Si ocurre un error durante la verificación
     */
    boolean existeMascota(Mascota mascota) throws Exception;
}
