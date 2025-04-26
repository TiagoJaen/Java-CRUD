package Controlador;

import Modelo.AlumnoDAO;
import Modelo.Alumno;
import java.util.List;

public class AlumnoController {
    private AlumnoDAO dao;

    public AlumnoController() {
        this.dao = new AlumnoDAO();
    }

    public void agregarAlumno(String nombre, String apellido, int edad, String email) {
        dao.agregarAlumno(new Alumno(0, nombre, apellido, edad, email));
    }
    public void eliminarAlumno(int id){
        dao.eliminarAlumno(id);
    }
    public void actualizarEdad(int id, int edad){
        dao.actualizarEdad(id, edad);
    }
    public List<Alumno> listarAlumnos(){
        return dao.listarAlumnos();
    }


}
