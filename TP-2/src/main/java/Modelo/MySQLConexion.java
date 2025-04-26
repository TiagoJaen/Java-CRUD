package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConexion {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "tp2programacion";
    private static final String USER = "root";
    private static final String PASS = "cindyosaprincesa123";
    private static MySQLConexion instancia;
    private Connection connection;

    //CONSTRUCTOR

    private MySQLConexion() {
        try {
            connection = DriverManager.getConnection(URL + DB_NAME + "?useSSL=false&serverTimezone=UTC", USER, PASS);
            crearBaseSiNoExiste();
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    private static void crearBaseSiNoExiste() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
        } catch (SQLException e) {
            System.out.println("Error creando base de datos: " + e.getMessage());
        }
    }

    public static MySQLConexion getInstancia() {
        if (instancia == null)
            instancia = new MySQLConexion();
        return instancia;
    }

    public Connection getConnection() {
        return connection;
    }
}
