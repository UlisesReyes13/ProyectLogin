package ProyectoLogin_Controller;

import DAO.UserDAO;
import ProyectoLogin_Model.Usuario;


/**
 *
 * @author ulise
 */
public class ControllerRegistro {

   public int insert(Usuario u) throws Exception {
       UserDAO ud = new UserDAO();
       
       if(ud.getByUsername(u.getNombreUsuario())){
           int id = ud.insert(u);
           return id;
       }else {
           int id = 1;
           return id;
       }
   }
}
