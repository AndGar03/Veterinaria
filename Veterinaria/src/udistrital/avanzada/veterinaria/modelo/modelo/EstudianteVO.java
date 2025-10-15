/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.veterinaria.modelo.modelo;

public class EstudianteVO {
    private String codigo;
    private String nombre;
    private int edad;

    public EstudianteVO() {   }
    public EstudianteVO(String codigo, String nombre, int edad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getCodigo() {       return codigo;    }
    public void setCodigo(String codigo) {        this.codigo = codigo;    }
    public String getNombre() {        return nombre;    }
    public void setNombre(String nombre) {        this.nombre = nombre;    }
    public int getEdad() {        return edad;    }
    public void setEdad(int edad) {        this.edad = edad;    }  
}

