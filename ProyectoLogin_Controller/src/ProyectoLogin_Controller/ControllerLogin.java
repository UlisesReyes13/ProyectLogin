package ProyectoLogin_Controller;

import DAO.UserDAO;
import ProyectoLogin_Model.Usuario;


/**
 *
 * @author ulise
 */
public class ControllerLogin {
    
    public Usuario login(String usuario, String contraseña) throws Exception {
        Usuario u = new Usuario();
        UserDAO ud = new UserDAO();
        
        u = ud.login(usuario, contraseña);
        return u;
    }
}
