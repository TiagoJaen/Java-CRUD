package Excepciones;

public class InputVacioException extends RuntimeException {
    public InputVacioException() {
        super("El valor ingresado no puede estar vacio.");
    }
}
