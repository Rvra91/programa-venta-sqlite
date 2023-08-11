package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection conectar() {
          try {
            Class.forName("org.sqlite.JDBC");
            String dbPath = System.getProperty("user.dir") + "/database/a.db";
            Connection cn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            System.out.println("Conexion Correcta");
            return cn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error en la conexion: " + e.getMessage());
        }
        return null;
    }
}
