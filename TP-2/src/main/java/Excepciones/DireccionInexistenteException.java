package Excepciones;

public class DireccionInexistenteException extends RuntimeException {
  public DireccionInexistenteException() {
    super("La direccion ingresada no existe.");
  }
}
