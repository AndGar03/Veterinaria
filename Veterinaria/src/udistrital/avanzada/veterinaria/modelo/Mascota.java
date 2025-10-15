package udistrital.avanzada.veterinaria.modelo;

import java.io.Serializable;

/**
 * Clase que representa una mascota exótica en el sistema veterinario.
 * Contiene toda la información taxonómica y de alimentación de la mascota.
 * Implementa Serializable para permitir la serialización de objetos.
 * 
 * @author Sistema Veterinaria
 * @version 1.0
 */
public class Mascota implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Nombre común de la mascota
     */
    private String nombreComun;
    
    /**
     * Apodo o nombre personal de la mascota
     */
    private String apodo;
    
    /**
     * Clasificación taxonómica de la mascota
     */
    private Clasificacion clasificacion;
    
    /**
     * Familia taxonómica a la que pertenece
     */
    private String familia;
    
    /**
     * Género taxonómico
     */
    private String genero;
    
    /**
     * Especie taxonómica
     */
    private String especie;
    
    /**
     * Tipo de alimento principal que consume
     * Marcado como transient para excluirlo de la serialización
     */
    private transient TipoAlimento tipoAlimentoPrincipal;
    
    /**
     * Constructor por defecto
     */
    public Mascota() {
    }
    
    /**
     * Constructor completo con todos los parámetros
     * 
     * @param nombreComun Nombre común de la mascota
     * @param apodo Apodo de la mascota
     * @param clasificacion Clasificación taxonómica
     * @param familia Familia taxonómica
     * @param genero Género taxonómico
     * @param especie Especie taxonómica
     * @param tipoAlimentoPrincipal Tipo de alimento principal
     */
    public Mascota(String nombreComun, String apodo, Clasificacion clasificacion, 
                   String familia, String genero, String especie, TipoAlimento tipoAlimentoPrincipal) {
        this.nombreComun = nombreComun;
        this.apodo = apodo;
        this.clasificacion = clasificacion;
        this.familia = familia;
        this.genero = genero;
        this.especie = especie;
        this.tipoAlimentoPrincipal = tipoAlimentoPrincipal;
    }
    
    /**
     * Obtiene el nombre común de la mascota
     * 
     * @return El nombre común
     */
    public String getNombreComun() {
        return nombreComun;
    }
    
    /**
     * Establece el nombre común de la mascota
     * 
     * @param nombreComun El nombre común a establecer
     */
    public void setNombreComun(String nombreComun) {
        this.nombreComun = nombreComun;
    }
    
    /**
     * Obtiene el apodo de la mascota
     * 
     * @return El apodo
     */
    public String getApodo() {
        return apodo;
    }
    
    /**
     * Establece el apodo de la mascota
     * 
     * @param apodo El apodo a establecer
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }
    
    /**
     * Obtiene la clasificación taxonómica
     * 
     * @return La clasificación
     */
    public Clasificacion getClasificacion() {
        return clasificacion;
    }
    
    /**
     * Establece la clasificación taxonómica
     * 
     * @param clasificacion La clasificación a establecer
     */
    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }
    
    /**
     * Obtiene la familia taxonómica
     * 
     * @return La familia
     */
    public String getFamilia() {
        return familia;
    }
    
    /**
     * Establece la familia taxonómica
     * 
     * @param familia La familia a establecer
     */
    public void setFamilia(String familia) {
        this.familia = familia;
    }
    
    /**
     * Obtiene el género taxonómico
     * 
     * @return El género
     */
    public String getGenero() {
        return genero;
    }
    
    /**
     * Establece el género taxonómico
     * 
     * @param genero El género a establecer
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    /**
     * Obtiene la especie taxonómica
     * 
     * @return La especie
     */
    public String getEspecie() {
        return especie;
    }
    
    /**
     * Establece la especie taxonómica
     * 
     * @param especie La especie a establecer
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    /**
     * Obtiene el tipo de alimento principal
     * 
     * @return El tipo de alimento
     */
    public TipoAlimento getTipoAlimentoPrincipal() {
        return tipoAlimentoPrincipal;
    }
    
    /**
     * Establece el tipo de alimento principal
     * 
     * @param tipoAlimentoPrincipal El tipo de alimento a establecer
     */
    public void setTipoAlimentoPrincipal(TipoAlimento tipoAlimentoPrincipal) {
        this.tipoAlimentoPrincipal = tipoAlimentoPrincipal;
    }
    
    /**
     * Compara dos mascotas para determinar si son iguales.
     * Dos mascotas son iguales si tienen el mismo apodo.
     * 
     * @param obj El objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Mascota mascota = (Mascota) obj;
        return apodo != null ? apodo.equals(mascota.apodo) : mascota.apodo == null;
    }
    
    /**
     * Genera un código hash basado en el apodo de la mascota
     * 
     * @return El código hash
     */
    @Override
    public int hashCode() {
        return apodo != null ? apodo.hashCode() : 0;
    }
    
    /**
     * Representación en cadena de la mascota
     * 
     * @return Una cadena con la información de la mascota
     */
    @Override
    public String toString() {
        return "Mascota{" +
                "nombreComun='" + nombreComun + '\'' +
                ", apodo='" + apodo + '\'' +
                ", clasificacion=" + clasificacion +
                ", familia='" + familia + '\'' +
                ", genero='" + genero + '\'' +
                ", especie='" + especie + '\'' +
                ", tipoAlimentoPrincipal=" + tipoAlimentoPrincipal +
                '}';
    }
}
