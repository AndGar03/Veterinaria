package udistrital.avanzada.veterinaria.modelo.modelo;

import java.io.Serializable;

/**
 * Clase que representa una mascota exótica en el sistema veterinario.
 * Implementa Serializable para permitir la serialización de objetos.
 * 
 * @author Estudiantes - Universidad Distrital Francisco José de Caldas
 * @version 1.0
 * @since 2024
 */
public class MascotaVO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String idMascota;
    private String apodo;
    private String clasificacion;
    private String familia;
    private String genero;
    private String especie;
    private String tipoAlimento;
    private int edad;
    private double peso;
    private String observaciones;

    /**
     * Constructor por defecto de MascotaVO.
     */
    public MascotaVO() {
    }

    /**
     * Constructor completo de MascotaVO.
     * 
     * @param idMascota Identificador único de la mascota
     * @param apodo Nombre o apodo de la mascota
     * @param clasificacion Clasificación taxonómica de la mascota
     * @param familia Familia biológica a la que pertenece
     * @param genero Género de la mascota
     * @param especie Especie específica
     * @param tipoAlimento Tipo de alimento que consume
     * @param edad Edad en años
     * @param peso Peso en kilogramos
     * @param observaciones Observaciones adicionales
     */
    public MascotaVO(String idMascota, String apodo, String clasificacion, 
                    String familia, String genero, String especie, 
                    String tipoAlimento, int edad, double peso, String observaciones) {
        this.idMascota = idMascota;
        this.apodo = apodo;
        this.clasificacion = clasificacion;
        this.familia = familia;
        this.genero = genero;
        this.especie = especie;
        this.tipoAlimento = tipoAlimento;
        this.edad = edad;
        this.peso = peso;
        this.observaciones = observaciones;
    }

    /**
     * Obtiene el identificador único de la mascota.
     * 
     * @return El ID de la mascota
     */
    public String getIdMascota() {
        return idMascota;
    }

    /**
     * Establece el identificador único de la mascota.
     * 
     * @param idMascota El ID de la mascota
     */
    public void setIdMascota(String idMascota) {
        this.idMascota = idMascota;
    }

    /**
     * Obtiene el apodo de la mascota.
     * 
     * @return El apodo de la mascota
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * Establece el apodo de la mascota.
     * 
     * @param apodo El apodo de la mascota
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    /**
     * Obtiene la clasificación taxonómica de la mascota.
     * 
     * @return La clasificación de la mascota
     */
    public String getClasificacion() {
        return clasificacion;
    }

    /**
     * Establece la clasificación taxonómica de la mascota.
     * 
     * @param clasificacion La clasificación de la mascota
     */
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * Obtiene la familia biológica de la mascota.
     * 
     * @return La familia de la mascota
     */
    public String getFamilia() {
        return familia;
    }

    /**
     * Establece la familia biológica de la mascota.
     * 
     * @param familia La familia de la mascota
     */
    public void setFamilia(String familia) {
        this.familia = familia;
    }

    /**
     * Obtiene el género de la mascota.
     * 
     * @return El género de la mascota
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Establece el género de la mascota.
     * 
     * @param genero El género de la mascota
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la especie de la mascota.
     * 
     * @return La especie de la mascota
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * Establece la especie de la mascota.
     * 
     * @param especie La especie de la mascota
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * Obtiene el tipo de alimento de la mascota.
     * 
     * @return El tipo de alimento
     */
    public String getTipoAlimento() {
        return tipoAlimento;
    }

    /**
     * Establece el tipo de alimento de la mascota.
     * 
     * @param tipoAlimento El tipo de alimento
     */
    public void setTipoAlimento(String tipoAlimento) {
        this.tipoAlimento = tipoAlimento;
    }

    /**
     * Obtiene la edad de la mascota en años.
     * 
     * @return La edad de la mascota
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Establece la edad de la mascota en años.
     * 
     * @param edad La edad de la mascota
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Obtiene el peso de la mascota en kilogramos.
     * 
     * @return El peso de la mascota
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Establece el peso de la mascota en kilogramos.
     * 
     * @param peso El peso de la mascota
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Obtiene las observaciones adicionales de la mascota.
     * 
     * @return Las observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Establece las observaciones adicionales de la mascota.
     * 
     * @param observaciones Las observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Representación en cadena de texto del objeto MascotaVO.
     * 
     * @return Cadena con la información de la mascota
     */
    @Override
    public String toString() {
        return "MascotaVO{" +
                "idMascota='" + idMascota + '\'' +
                ", apodo='" + apodo + '\'' +
                ", clasificacion='" + clasificacion + '\'' +
                ", familia='" + familia + '\'' +
                ", genero='" + genero + '\'' +
                ", especie='" + especie + '\'' +
                ", tipoAlimento='" + tipoAlimento + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", observaciones='" + observaciones + '\'' +
                '}';
    }

    /**
     * Compara dos objetos MascotaVO por su ID.
     * 
     * @param obj Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MascotaVO mascotaVO = (MascotaVO) obj;
        return idMascota != null ? idMascota.equals(mascotaVO.idMascota) : mascotaVO.idMascota == null;
    }

    /**
     * Genera código hash basado en el ID de la mascota.
     * 
     * @return Código hash del objeto
     */
    @Override
    public int hashCode() {
        return idMascota != null ? idMascota.hashCode() : 0;
    }
}

