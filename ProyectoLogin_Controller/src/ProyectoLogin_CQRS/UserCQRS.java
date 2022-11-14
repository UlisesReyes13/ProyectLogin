package ProyectoLogin_CQRS;

import DAO.UserDAO;
import ProyectoLogin_Model.Usuario;

/**
 *
 * @author ulise
 */
public class UserCQRS {

    public int insert(Usuario u) throws Exception {
        UserDAO ud = new UserDAO();
        
       if(ud.getByUsername(u.getNombreUsuario())){
       UserDAO udao = new UserDAO();
       int id = udao.insert(u);
       return(id);
       } else{
           int id = 1;
             return id;  
            }
    }

}
