package ProyectoLogin_Controller;

import ProyectLogin_BD.ConexionMySQL;
import ProyectoLogin_Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ulise
 */
public class ControllerLogin {
    public Usuario login(String username, String password) throws Exception{
        
        String sql = "SELECT * FROM usuario WHERE nombreUsuario = ? AND contrasenia = ?";
        
        Usuario u = null;
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        Connection conn = connMySQL.open();
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = null;
        
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        
        rs = pstmt.executeQuery();
        
        if(rs.next()){
            u = fillUser(rs);
            
        }
        
        rs.close();
        pstmt.close();
        conn.close();
        connMySQL.close();
        
        return u;
    }
    
    public Usuario fillUser(ResultSet rs) throws Exception{
        
        Usuario u = new Usuario();
        
        u.setNombre(rs.getString("nombre"));
        u.setApePaterno(rs.getString("apePaterno"));
        u.setApeMaterno(rs.getString("apeMaterno"));
        u.setNombreUsuario(rs.getString("nombreUsuario"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setIdUsuario(rs.getInt("idUsuario"));
        
        return u;
    }
}
