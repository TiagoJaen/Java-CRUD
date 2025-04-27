package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConexion {
    private static final String URL = "jdbc:sqlite:TP2Programacion.db";
    private static Connection connection;

    //CONSTRUCTOR
    private SQLiteConexion() {
    }

    //METODOS
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

        return connection;
    }

    public static void cerrarConexion() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
