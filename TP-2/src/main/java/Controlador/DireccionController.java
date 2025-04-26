package Controlador;

import Modelo.Direccion;
import Modelo.DireccionDAO;

import java.util.List;

public class DireccionController {
    DireccionDAO dao;

    //CONSTRUCTOR
    public DireccionController() {
        this.dao = new DireccionDAO();
    }

    //METODOS
    public void agregarDireccion(String calle, int altura, int alumno_id) {
    dao.agregarDireccion(new Direccion(0, calle, altura, alumno_id));}

    public void eliminarDireccion(String calle, int altura){
        dao.eliminarDireccion(new Direccion(calle, altura));}

    public List<Direccion> listarDirecciones(){return dao.listarDirecciones();}
}
