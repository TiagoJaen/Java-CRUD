package Excepciones;

public class AlumnoInexistenteException extends RuntimeException {
    public AlumnoInexistenteException() {
        super("El id del alumno ingresado no existe.");
    }
}
