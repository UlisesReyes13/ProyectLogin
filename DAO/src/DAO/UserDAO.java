
package DAO;

import ProyectLogin_BD.ConexionMySQL;
import ProyectoLogin_Model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author ulise
 */
public class UserDAO {
    
    public boolean getByUsername(String nombreUsuario) throws Exception{
        
        boolean valid = true;
        
        String sql = "SELECT * FROM usuario WHERE nombreusuario = ?";
        
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        
        ResultSet rs = null;
        
        pstmt.setString(1, nombreUsuario);
        
        rs = pstmt.executeQuery();
        
        while(rs.next()){
            valid = false;
        }
        
        pstmt.close();
        conn.close();
        connMySQL.close();
        
        return valid;
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
