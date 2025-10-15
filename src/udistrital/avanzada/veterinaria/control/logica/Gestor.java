package udistrital.avanzada.veterinaria.control.logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Properties;
import udistrital.avanzada.veterinaria.modelo.DAO.MascotaDAO;
import udistrital.avanzada.veterinaria.modelo.modelo.MascotaVO;

/**
 * Clase Gestor que implementa la lógica de negocio del sistema de gestión
 * de mascotas exóticas. Actúa como controlador principal siguiendo el patrón MVC.
 * 
 * @author AndGar03, SanSantax
 * @version 2.0
 * @since 2024
 */
public class Gestor {

    private MascotaDAO mascotaDAO;

    /**
     * Constructor por defecto del Gestor.
     * Inicializa el DAO y carga los datos iniciales desde el archivo properties.
     */
    public Gestor() {
        mascotaDAO = new MascotaDAO();
        cargarDatosIniciales();
    }

    /**
     * Carga los datos iniciales desde el archivo properties.
     * Si algún registro está incompleto, se debe completar desde la interfaz.
     */
    private void cargarDatosIniciales() {
        Properties propiedades = new Properties();
        try (FileInputStream fis = new FileInputStream("data/mascotas_iniciales.properties")) {
            propiedades.load(fis);
            
            for (String clave : propiedades.stringPropertyNames()) {
                if (clave.startsWith("mascota.")) {
                    String datos = propiedades.getProperty(clave);
                    MascotaVO mascota = parsearDatosMascota(datos);
                    if (mascota != null && datosCompletos(mascota)) {
                        if (!mascotaDAO.existeMascota(mascota.getIdMascota())) {
                            mascotaDAO.insertarMascota(mascota);
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Error al cargar el archivo properties
        }
    }

    /**
     * Parsea los datos de una mascota desde una cadena de texto.
     * 
     * @param datos Cadena con los datos separados por comas
     * @return Objeto MascotaVO parseado o null si hay error
     */
    private MascotaVO parsearDatosMascota(String datos) {
        try {
            String[] campos = datos.split(",");
            if (campos.length >= 10) {
                MascotaVO mascota = new MascotaVO();
                mascota.setIdMascota(campos[0].trim());
                mascota.setApodo(campos[1].trim());
                mascota.setClasificacion(campos[2].trim());
                mascota.setFamilia(campos[3].trim());
                mascota.setGenero(campos[4].trim());
                mascota.setEspecie(campos[5].trim());
                mascota.setTipoAlimento(campos[6].trim());
                
                if (!campos[7].trim().isEmpty()) {
                    mascota.setEdad(Integer.parseInt(campos[7].trim()));
                }
                
                if (!campos[8].trim().isEmpty()) {
                    mascota.setPeso(Double.parseDouble(campos[8].trim()));
                }
                
                mascota.setObservaciones(campos[9].trim());
                return mascota;
            }
        } catch (Exception e) {
            // Error al parsear los datos
        }
        return null;
    }

    /**
     * Verifica si los datos de una mascota están completos.
     * 
     * @param mascota Mascota a verificar
     * @return true si los datos están completos, false en caso contrario
     */
    private boolean datosCompletos(MascotaVO mascota) {
        return mascota.getIdMascota() != null && !mascota.getIdMascota().isEmpty() &&
               mascota.getApodo() != null && !mascota.getApodo().isEmpty() &&
               mascota.getClasificacion() != null && !mascota.getClasificacion().isEmpty() &&
               mascota.getFamilia() != null && !mascota.getFamilia().isEmpty() &&
               mascota.getGenero() != null && !mascota.getGenero().isEmpty() &&
               mascota.getEspecie() != null && !mascota.getEspecie().isEmpty() &&
               mascota.getTipoAlimento() != null && !mascota.getTipoAlimento().isEmpty() &&
               mascota.getEdad() > 0 && mascota.getPeso() > 0;
    }

    /**
     * Registra una nueva mascota en el sistema.
     * 
     * @param mascota Mascota a registrar
     * @return true si se registró exitosamente, false si ya existe o hay error
     */
    public boolean registrarMascota(MascotaVO mascota) {
        if (mascotaDAO.existeMascota(mascota.getIdMascota())) {
            return false; // Mascota ya existe
        }
        return mascotaDAO.insertarMascota(mascota);
    }

    /**
     * Busca mascotas por apodo.
     * 
     * @param apodo Apodo o parte del apodo a buscar
     * @return Lista de mascotas que coinciden con el criterio
     */
    public ArrayList<MascotaVO> buscarMascotasPorApodo(String apodo) {
        return mascotaDAO.consultarMascotasPorApodo(apodo);
    }

    /**
     * Busca mascotas por clasificación.
     * 
     * @param clasificacion Clasificación a buscar
     * @return Lista de mascotas que pertenecen a la clasificación
     */
    public ArrayList<MascotaVO> buscarMascotasPorClasificacion(String clasificacion) {
        return mascotaDAO.consultarMascotasPorClasificacion(clasificacion);
    }

    /**
     * Busca mascotas por familia.
     * 
     * @param familia Familia biológica a buscar
     * @return Lista de mascotas que pertenecen a la familia
     */
    public ArrayList<MascotaVO> buscarMascotasPorFamilia(String familia) {
        return mascotaDAO.consultarMascotasPorFamilia(familia);
    }

    /**
     * Busca mascotas por tipo de alimento.
     * 
     * @param tipoAlimento Tipo de alimento a buscar
     * @return Lista de mascotas que consumen el tipo de alimento
     */
    public ArrayList<MascotaVO> buscarMascotasPorTipoAlimento(String tipoAlimento) {
        return mascotaDAO.consultarMascotasPorTipoAlimento(tipoAlimento);
    }

    /**
     * Modifica los datos de una mascota existente.
     * No permite modificar familia, género y especie.
     * 
     * @param mascota Mascota con los datos actualizados
     * @return true si se modificó exitosamente, false en caso contrario
     */
    public boolean modificarMascota(MascotaVO mascota) {
        return mascotaDAO.modificarMascota(mascota);
    }

    /**
     * Elimina una mascota del sistema.
     * 
     * @param idMascota ID de la mascota a eliminar
     * @return true si se eliminó exitosamente, false en caso contrario
     */
    public boolean eliminarMascota(String idMascota) {
        return mascotaDAO.eliminarMascota(idMascota);
    }

    /**
     * Obtiene todas las mascotas registradas en el sistema.
     * 
     * @return Lista con todas las mascotas
     */
    public ArrayList<MascotaVO> obtenerTodasLasMascotas() {
        return mascotaDAO.obtenerTodasLasMascotas();
    }

    /**
     * Consulta una mascota específica por su ID.
     * 
     * @param idMascota ID de la mascota a consultar
     * @return Mascota encontrada o null si no existe
     */
    public MascotaVO consultarMascotaPorId(String idMascota) {
        return mascotaDAO.consultarMascotaPorId(idMascota);
    }

    /**
     * Serializa todas las mascotas a un archivo, excluyendo el tipo de alimento.
     * 
     * @param nombreArchivo Nombre del archivo donde guardar la serialización
     * @return true si la serialización fue exitosa, false en caso contrario
     */
    public boolean serializarMascotas(String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new java.io.FileOutputStream(nombreArchivo))) {
            ArrayList<MascotaVO> mascotasSinAlimento = new ArrayList<>();
            ArrayList<MascotaVO> todasLasMascotas = mascotaDAO.obtenerTodasLasMascotas();
            
            for (MascotaVO mascota : todasLasMascotas) {
                MascotaVO mascotaSinAlimento = new MascotaVO();
                mascotaSinAlimento.setIdMascota(mascota.getIdMascota());
                mascotaSinAlimento.setApodo(mascota.getApodo());
                mascotaSinAlimento.setClasificacion(mascota.getClasificacion());
                mascotaSinAlimento.setFamilia(mascota.getFamilia());
                mascotaSinAlimento.setGenero(mascota.getGenero());
                mascotaSinAlimento.setEspecie(mascota.getEspecie());
                mascotaSinAlimento.setTipoAlimento(""); // Excluir tipo de alimento
                mascotaSinAlimento.setEdad(mascota.getEdad());
                mascotaSinAlimento.setPeso(mascota.getPeso());
                mascotaSinAlimento.setObservaciones(mascota.getObservaciones());
                mascotasSinAlimento.add(mascotaSinAlimento);
            }
            
            oos.writeObject(mascotasSinAlimento);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Crea un archivo de acceso aleatorio con el estado final de la base de datos.
     * 
     * @param nombreArchivo Nombre del archivo donde guardar los datos
     * @return true si se creó exitosamente, false en caso contrario
     */
    public boolean crearArchivoAccesoAleatorio(String nombreArchivo) {
        try (RandomAccessFile raf = new RandomAccessFile(nombreArchivo, "rw")) {
            ArrayList<MascotaVO> todasLasMascotas = mascotaDAO.obtenerTodasLasMascotas();
            
            // Escribir el número de registros
            raf.writeInt(todasLasMascotas.size());
            
            // Escribir cada mascota
            for (MascotaVO mascota : todasLasMascotas) {
                escribirMascotaEnArchivo(raf, mascota);
            }
            
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Escribe los datos de una mascota en un archivo de acceso aleatorio.
     * 
     * @param raf Archivo de acceso aleatorio
     * @param mascota Mascota a escribir
     * @throws IOException Si hay error al escribir
     */
    private void escribirMascotaEnArchivo(RandomAccessFile raf, MascotaVO mascota) throws IOException {
        raf.writeUTF(mascota.getIdMascota());
        raf.writeUTF(mascota.getApodo());
        raf.writeUTF(mascota.getClasificacion());
        raf.writeUTF(mascota.getFamilia());
        raf.writeUTF(mascota.getGenero());
        raf.writeUTF(mascota.getEspecie());
        raf.writeUTF(mascota.getTipoAlimento());
        raf.writeInt(mascota.getEdad());
        raf.writeDouble(mascota.getPeso());
        raf.writeUTF(mascota.getObservaciones());
    }

    /**
     * Verifica si existe una mascota con el ID especificado.
     * 
     * @param idMascota ID a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existeMascota(String idMascota) {
        return mascotaDAO.existeMascota(idMascota);
    }
}

