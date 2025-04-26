package Interfaces;

import Excepciones.AlumnoInexistenteException;
import Modelo.Alumno;

public interface VerificarAlumno {
    void verificarAlumno(int alumno_id) throws AlumnoInexistenteException;
}
