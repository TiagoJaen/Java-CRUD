package Modelo;

public class Alumno {
    private int id, edad;
    private String nombre, apellido, email;

    public Alumno() {
    }

    public Alumno(int id) {
        this.id = id;
    }

    public Alumno(int id, int edad) {
        this.id = id;
        this.edad = edad;
    }

    public Alumno(int id, String nombre, String apellido, int edad, String email) {
        this.id = id;
        this.edad = edad;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    //SETTER Y GETTER
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\n----------" +
                "\nid= " + id +
                "\nnombre= "+ '"' + nombre + '"' +
                "\napellido= "+ '"' + apellido + '"' +
                "\nedad= " + edad +
                "\nemail= "+ '"' + email + '"' +
                "\n----------\n";
    }
}
