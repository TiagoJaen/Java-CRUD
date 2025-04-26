package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import Excepciones.AlumnoInexistenteException;
import Excepciones.InputVacioException;
import Interfaces.VerificarAlumno;
import Modelo.Alumno;

public class AlumnoDAO implements VerificarAlumno {
    private Connection conn;

    public AlumnoDAO() {
        conn = MySQLConexion.getInstancia().getConnection();
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS alumnos (id INTEGER PRIMARY KEY AUTO_INCREMENT, nombre TEXT NOT NULL, apellido TEXT NOT NULL, edad INTEGER NOT NULL, email TEXT UNIQUE NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creando tabla: " + e.getMessage());
        }
    }

    public void agregarAlumno(Alumno alumno){
        String sql = "INSERT INTO alumnos(nombre, apellido, edad, email) VALUES (?, ?, ?, ?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            if (alumno.getNombre() == "" || alumno.getApellido() == "" || alumno.getEmail() == "")
                throw new InputVacioException();

            stmt.setString(1, alumno.getNombre());
            stmt.setString(2, alumno.getApellido());
            stmt.setInt(3, alumno.getEdad());
            stmt.setString(4, alumno.getEmail());
            stmt.execute();

            System.out.println("Alumno agregado con exito.");
        } catch (SQLException e){
            System.out.printf(e.getMessage());
        }catch (InputVacioException e){
            System.out.println(e.getMessage());
        }
    }
    public void eliminarAlumno(int id){
        String sql = "DELETE FROM alumnos WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql)){
            verificarAlumno(id);
            stmt.setInt(1, id);
            stmt.execute();

            System.out.println("Alumno eliminado con exito.");
        } catch (SQLException e){
            System.out.printf(e.getMessage());
        }catch (AlumnoInexistenteException e){
            System.out.println(e.getMessage());
        }
    }

    public void actualizarEdad(int id, int edad){
        String sql = "UPDATE alumnos SET edad = ? WHERE id = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            verificarAlumno(id);

            pstmt.setInt(1, edad);
            pstmt.setInt(2, id);
            pstmt.execute();

            System.out.println("Edad actualizada con exito.");
        }catch (SQLException e){
            System.out.printf(e.getMessage());
        }catch (AlumnoInexistenteException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Alumno> listarAlumnos(){
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alumnos.add(new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("edad"),
                        rs.getString("email")));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return alumnos;
    }

    //INTERFACES

    @Override
    public void verificarAlumno(int alumno_id) throws AlumnoInexistenteException{
        String sql = "SELECT id FROM alumnos WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, alumno_id);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()) throw new AlumnoInexistenteException();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw  new AlumnoInexistenteException();
        }
    }
}
