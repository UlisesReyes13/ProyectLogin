package ProyectoLogin_Controller;

import ProyectLogin_BD.ConexionMySQL;
import ProyectoLogin_Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ulise
 */
public class ControllerRegistro {

    public int insert(Usuario u) throws Exception{
        
        String sql = "INSERT INTO usuario (nombre, apePaterno, apeMaterno, nombreUsuario, contrasenia)"
                + "VALUES (?,?,?,?,?)";
        
        int idUsuarioGenerado = -1;
       
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn =  connMySQL.open();
        
        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        
        ResultSet rs = null;
        
        pstmt.setString(1, u.getNombre());
        pstmt.setString(2, u.getApePaterno());
        pstmt.setString(3, u.getApeMaterno());
        pstmt.setString(4, u.getNombreUsuario());
        pstmt.setString(5, u.getContrasenia());
        
        pstmt.executeUpdate();
        
        rs = pstmt.getGeneratedKeys();
        
        if(rs.next()){
            idUsuarioGenerado = rs.getInt(1);
            u.setIdUsuario(idUsuarioGenerado);
        }
        
        rs.close();
        pstmt.close();
        connMySQL.close();
        conn.close();
        
        return idUsuarioGenerado;
    }
    
}
