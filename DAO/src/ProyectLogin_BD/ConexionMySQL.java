
package ProyectLogin_BD;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ulise
 */
// Esta clase nos permitira abrir y cerrar la conxiòn con MySQL
public class ConexionMySQL {

    // Aqui guardamos y gestionamos la conexión con MySQL.
    Connection conexion;

    /*
     * Realizamos la conexion con la Base de Datos y devolvemos un objeto de tipo
     * Connection
     * 
     * @throws java.lang.Exception
     */

    public Connection open() throws Exception {
        // Establecemos el Driver de MySQL;
        String driver = "com.mysql.jdbc.Driver";

        // Establecemos la ruta de conexión:
        String url = "jdbc:mysql://127.0.0.1:3306/proyectologin";

        // Establecemos el usuario y contraseña:
        String usuario = "root";
        String contrasenia = ""; // Aqui depende de la contraseña establecida en el root

        // Registramos el Driver:
        Class.forName(driver);

        // Abrimos la conexión con MySQL:
        conexion = DriverManager.getConnection(url, usuario, contrasenia);

        // Devolvemos el objeto que mantiene la conexion con MySQL
        return conexion;
    }

    // Cerramos la conexión con MySQL:
    public void close() {
        try {
            if (conexion != null)
                conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}