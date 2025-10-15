/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package udistrital.avanzada.veterinaria.control.logica;

import java.util.ArrayList;
import udistrital.avanzada.veterinaria.modelo.DAO.EstudianteDAO;
import udistrital.avanzada.veterinaria.modelo.modelo.EstudianteVO;

public class Gestor {

    private EstudianteDAO miEstudianteDAO;

    public Gestor() {
        //registrarEstudiante();
        obtenerRegistros();
        buscarEstudiante();
        buscarInicial();
        //eliminarEstudiante();
        modificarEstudiante();


    }
private void registrarEstudiante() {
        miEstudianteDAO = new EstudianteDAO();
        EstudianteVO miEstudiante1 = new EstudianteVO();
        miEstudiante1.setCodigo("202210200032");
        miEstudiante1.setNombre("Pepe Rojas");
        miEstudiante1.setEdad(1);
        miEstudianteDAO.insertarDatos(miEstudiante1);
        EstudianteVO miEstudiante2 = new EstudianteVO();
        miEstudiante2.setCodigo("202210200033");
        miEstudiante2.setNombre("Teresa Castro");
        miEstudiante2.setEdad(2);
        miEstudianteDAO.insertarDatos(miEstudiante2);
    }
private void obtenerRegistros() {
        miEstudianteDAO = new EstudianteDAO();
        EstudianteVO miEstudiante;
        ArrayList<EstudianteVO> listaEstudiantes = miEstudianteDAO.listaDeEstudiantes();
        if (!listaEstudiantes.isEmpty()) {
            int numeroEstudiante = 0;
            for (int i = 0; i < listaEstudiantes.size(); i++) {
                numeroEstudiante++;
                miEstudiante = listaEstudiantes.get(i);
                System.out.println("****************Estudiante No. " + numeroEstudiante + "**********");
                System.out.println("Codigo Estudiante: " + miEstudiante.getCodigo());
                System.out.println("Nombre Estudiante: " + miEstudiante.getNombre());
                System.out.println("Edad Estudiante: " + miEstudiante.getEdad());
                System.out.println("*************************************************\n");
            }
        } else {
            System.out.println("Actualmente no existen registros de estudiantes");
        }
    }
private void buscarEstudiante() {
        miEstudianteDAO = new EstudianteDAO();
        String codigo = "202210200030";
        EstudianteVO estudianteEncontrada = miEstudianteDAO.consultarEstudiante(codigo);
        if (estudianteEncontrada != null) {
            System.out.println("**************** Estudiante Consultado *************************");
            System.out.println("Codigo Estudiante: " + estudianteEncontrada.getCodigo());
            System.out.println("Nombre Estudiante: " + estudianteEncontrada.getNombre());
            System.out.println("Edad Estudiante : " + estudianteEncontrada.getEdad());
            System.out.println("*************************************************\n");

        } else {
            System.out.println("No existen un estudiante con ese codigo");
        }
    }
private void buscarInicial() {
        miEstudianteDAO = new EstudianteDAO();
        String nombre = "J";
        EstudianteVO estudianteEncontrada = miEstudianteDAO.consultarLetra(nombre);
        if (estudianteEncontrada != null) {
            System.out.println("**************** Estudiante Consultado *************************");
            System.out.println("Codigo Estudiante: " + estudianteEncontrada.getCodigo());
            System.out.println("Nombre Estudiante: " + estudianteEncontrada.getNombre());
            System.out.println("Edad Estudiante : " + estudianteEncontrada.getEdad());
            System.out.println("*************************************************\n");

        } else {
            System.out.println("No existen un estudiante con esa inicial");
        }
    }
 private void eliminarEstudiante() {
        String codigo = "202210200033";
        EstudianteVO estudianteEncontrado = miEstudianteDAO.consultarEstudiante(codigo);
        if (estudianteEncontrado != null) {
            System.out.println("************Mascota a Eliminar****************");
            System.out.println("Id Mascota: " + estudianteEncontrado.getCodigo());
            System.out.println("Nombre Mascota: " + estudianteEncontrado.getNombre());
            System.out.println("Edad Mascota: " + estudianteEncontrado.getEdad());
            System.out.println("********************************************\n");
            if (miEstudianteDAO.eliminarEstudiante(codigo)) {
                System.out.println("Estudiante Eliminado");
            } else {
                System.out.println("No se pudo eliminar el estudiante");
            }
        } else {
            System.out.println("No existen un estudiante con ese codigo");
        }
    }
private void modificarEstudiante() {
        String codigo = "202210200031";
        EstudianteVO estudianteEncontrado = miEstudianteDAO.consultarEstudiante(codigo);
        if (estudianteEncontrado != null) {
            System.out.println("****************Estudiante a Modificar****************");
            System.out.println("Codigo Estudiante: " + estudianteEncontrado.getCodigo());
            System.out.println("Nombre Estudiante: " + estudianteEncontrado.getNombre());
            System.out.println("Edad Estudiante: " + estudianteEncontrado.getEdad());
            System.out.println("*************************************************\n");
           
            if (miEstudianteDAO.modificarEstudiante(codigo)) {
                System.out.println("Estudiante Modificado");
                estudianteEncontrado = miEstudianteDAO.consultarEstudiante(codigo);
                System.out.println("****************Estudiante Modificado****************");
                System.out.println("Codigo Estudiante: " + estudianteEncontrado.getCodigo());
                System.out.println("Nombre Estudiante: " + estudianteEncontrado.getNombre());
                System.out.println("Edad Estudiante: " + estudianteEncontrado.getEdad());
                System.out.println("*************************************************\n");
            } else {
                System.out.println("No se pudo modificar el estudiante");
            }
        } else {
            System.out.println("No existen una mascota con ese codigo");
        }
    }

}

