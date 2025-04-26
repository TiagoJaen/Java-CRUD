package Vista;

import Controlador.AlumnoController;
import Controlador.DireccionController;
import Modelo.Alumno;
import Modelo.Direccion;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private AlumnoController alumnoController;
    private DireccionController direccionController;

    //CONSTRUCTOR


    public Menu() {
        this.scanner = new Scanner(System.in);
        this.alumnoController = new AlumnoController();
        this.direccionController = new DireccionController();
    }

    public void ejecutar() {
        int opcion = 0;
        do {
            try {
                System.out.println("\n--- MENÚ USUARIOS ---");
                System.out.println("1. Agregar alumno.");
                System.out.println("2. Agregar direccion.");
                System.out.println("3. Listar alumnos.");
                System.out.println("4. Listar direcciones.");
                System.out.println("5. Actualizar edad de alumno.");
                System.out.println("6. Eliminar alumno.");
                System.out.println("7. Eliminar direccion.");
                System.out.println("0. Terminar programa.");
                System.out.print("Opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // limpia el buffer

                switch (opcion) {
                    case 1 -> agregarAlumno();
                    case 2 -> agregarDireccion();
                    case 3 -> listarAlumnos();
                    case 4 -> listarDirecciones();
                    case 5 -> actualizarEdadAlumno();
                    case 6 -> eliminarAlumno();
                    case 7 -> eliminarDireccion();
                    case 0 -> System.out.println("¡Chau!");
                    default -> System.out.println("Opción inválida");
                }
            }catch (InputMismatchException e){
                System.out.println("Has ingresado un tipo de dato incorrecto :(");
                scanner.nextLine();
            }
        }while (opcion != 0);
    }

    //METODOS DE ALUMNOS
    public void agregarAlumno() {
        String nombre, apellido, email;
        int edad;
        try {
            System.out.println("-----AGREGAR ALUMNO-----");
            System.out.println("Ingrese el nombre:");
            nombre = scanner.nextLine();

            System.out.println("Ingrese el apellido:");
            apellido = scanner.nextLine();

            System.out.println("Ingrese el edad:");
            edad = scanner.nextInt();
            scanner.nextLine(); // limpia el buffer

            System.out.println("Ingrese el email:");
            email = scanner.nextLine();

            alumnoController.agregarAlumno(nombre, apellido, edad, email);
        }catch (InputMismatchException e){
            System.out.println("Has ingresado un tipo de dato incorrecto :(");
            scanner.nextLine();
        }
    }

    public void eliminarAlumno() {
        int id;

        try {
            System.out.println("-----ELIMINAR ALUMNO-----");
            System.out.println("Ingrese el id del alumno:");
            id = scanner.nextInt();
            scanner.nextLine(); // limpia el buffer

            alumnoController.eliminarAlumno(id);
        }catch (InputMismatchException e){
            System.out.println("Has ingresado un tipo de dato incorrecto :(");
            scanner.nextLine();
        }
    }

    public void actualizarEdadAlumno(){
        int id, edad;

        try {
            System.out.println("-----ACTUALIZAR EDAD ALUMNO-----");
            System.out.println("Ingrese el id del alumno:");
            id = scanner.nextInt();
            scanner.nextLine(); // limpia el buffer

            System.out.println("Ingrese la nueva edad:");
            edad = scanner.nextInt();
            scanner.nextLine(); // limpia el buffer

            alumnoController.actualizarEdad(id, edad);
        }catch (InputMismatchException e){
            System.out.println("Has ingresado un tipo de dato incorrecto :(");
            scanner.nextLine();
        }
    }

    public void listarAlumnos() {
        List<Alumno> alumnos = alumnoController.listarAlumnos();
        System.out.println("-----ALUMNOS-----");
        for(Alumno a : alumnos){
            System.out.println(a);
        }
    }

    //METODOS DE DIRECCIONES
    public void agregarDireccion() {
        try{
            String calle;
            int altura, alumno_id;

            System.out.println("-----AGREGAR DIRECCION-----");
            System.out.println("Ingrese la calle:");
            calle = scanner.nextLine();

            System.out.println("Ingrese la altura:");
            altura = scanner.nextInt();
            scanner.nextLine(); // limpia el buffer

            System.out.println("Ingrese el id del alumno:");
            alumno_id = scanner.nextInt();
            scanner.nextLine(); // limpia el buffer

            direccionController.agregarDireccion(calle, altura, alumno_id);
        }catch (InputMismatchException e){
            System.out.println("Has ingresado un tipo de dato incorrecto :(");
            scanner.nextLine(); // limpia el buffer
        }
    }

    public void eliminarDireccion() {
        try {
            String calle;
            int altura;

            System.out.println("-----ELIMINAR DIRECCION-----");
            System.out.println("Ingrese la calle:");
            calle = scanner.nextLine();

            System.out.println("Ingrese la altura:");
            altura = scanner.nextInt();
            scanner.nextLine(); // limpia el buffer

            direccionController.eliminarDireccion(calle, altura);
        }catch (InputMismatchException e){
            System.out.println("Has ingresado un tipo de dato incorrecto :(");
            scanner.nextLine();
        }
    }

    public void listarDirecciones() {
        List<Direccion> direcciones = direccionController.listarDirecciones();
        System.out.println("-----DIRECCIONES-----");
        for(Direccion d : direcciones){
            System.out.println(d);
        }
    }


}
