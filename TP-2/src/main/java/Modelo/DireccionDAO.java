package Modelo;

import Excepciones.AlumnoInexistenteException;
import Excepciones.DireccionInexistenteException;
import Excepciones.InputVacioException;
import Interfaces.VerificarAlumno;
import Interfaces.VerificarDireccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DireccionDAO implements VerificarAlumno, VerificarDireccion {
    private Connection conn;

    //CONSTRUCTOR
    public DireccionDAO(){
        conn = SQLiteConexion.getConnection();
        crearTablaSiNoExiste();
    }

    //METODOS
    private void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS direcciones (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "calle TEXT NOT NULL, " +
                     "altura INTEGER NOT NULL, " +
                     "alumno_id INTEGER NOT NULL, " +
                     "FOREIGN KEY (alumno_id) REFERENCES alumnos(id) ON DELETE CASCADE)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creando tabla: " + e.getMessage());
        }
    }

    public void agregarDireccion(Direccion direccion) {
        String sql = "INSERT INTO direcciones(calle, altura, alumno_id) VALUES(?, ?, ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            if (direccion.getCalle() == "") throw new InputVacioException();

            verificarAlumno(direccion.getAlumno_id());

            pstmt.setString(1, direccion.getCalle());
            pstmt.setInt(2, direccion.getAltura());
            pstmt.setInt(3, direccion.getAlumno_id());
            pstmt.execute();
        }catch (SQLException e){
            System.out.println("El id del alumno ingresado no existe.");
        }catch (InputVacioException e){
            System.out.println(e.getMessage());
        }catch (AlumnoInexistenteException e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminarDireccion(Direccion direccion) {
        String sql = "DELETE FROM direcciones WHERE calle = ? AND altura = ?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            verificarDireccion(direccion.getCalle(), direccion.getAltura());

            pstmt.setString(1, direccion.getCalle());
            pstmt.setInt(2, direccion.getAltura());
            pstmt.execute();
            System.out.println("Direccion eliminada con éxito.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch (DireccionInexistenteException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Direccion> listarDirecciones(){
        String sql = "SELECT * FROM direcciones";
        List<Direccion> direcciones = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()){
                direcciones.add(
                        new Direccion(
                                rs.getInt("id"),
                                rs.getString("calle"),
                                rs.getInt("altura"),
                                rs.getInt("alumno_id")
                        )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return direcciones;
    }

    //INTERFACES
    //VERIFICAR ALUMNO
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

    //VERIFICAR DIRECCION

    @Override
    public void verificarDireccion(String calle, int altura) throws DireccionInexistenteException {
        String sql = "SELECT id FROM direcciones WHERE calle = ? AND altura = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, calle);
            pstmt.setInt(2, altura);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.next()) throw new DireccionInexistenteException();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            throw  new DireccionInexistenteException();
        }
    }
}
