package Interfaces;

import Excepciones.DireccionInexistenteException;

public interface VerificarDireccion {
    void verificarDireccion(String calle, int altura) throws DireccionInexistenteException;

}
