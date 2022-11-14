package ProyectoLogin_Controller;

import DAO.UserDAO;
import ProyectoLogin_AppService.UserAppService;
import ProyectoLogin_CQRS.UserCQRS;
import ProyectoLogin_Model.Usuario;


/**
 *
 * @author ulise
 */
public class ControllerLogin {
    
    public Usuario login(String usuario, String contraseña) throws Exception {
        Usuario u = new Usuario();
        UserAppService ups = new UserAppService();
        
        u = ups.login(usuario, contraseña);
        return u;
    }
}
