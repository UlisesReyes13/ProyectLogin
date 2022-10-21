
package DAO;

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
public class UserDAO {
    
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
