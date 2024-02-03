package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionSQL {

    Connection conectar = null;

    public Connection conexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/tienda", "root", "12345678");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de Conexion" + e.getMessage());
        }

        return conectar;
    }
}
