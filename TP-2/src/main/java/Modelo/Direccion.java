package Modelo;

public class Direccion {
    private int id, altura, alumno_id;
    private String calle;

    //CONSTRUCTOR
    public Direccion(String calle, int altura) {
        this.calle = calle;
        this.altura = altura;
    }

    public Direccion(int id, String calle, int altura, int alumno_id) {
        this.id = id;
        this.altura = altura;
        this.alumno_id = alumno_id;
        this.calle = calle;
    }
    //GETTER Y SETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAlumno_id() {
        return alumno_id;
    }

    public void setAlumno_id(int alumno_id) {
        this.alumno_id = alumno_id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Override
    public String toString() {
        return "\n----------" +
                "\nid= " + id +
                "\ncalle= "+ '"' + calle + '"' +
                "\naltura= " + altura +
                "\nalumno_id= " + alumno_id +
                "\n----------\n";
    }
}
