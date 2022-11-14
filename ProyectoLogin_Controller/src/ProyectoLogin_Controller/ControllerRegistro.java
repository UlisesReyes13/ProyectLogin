package ProyectoLogin_Controller;

import DAO.UserDAO;
import ProyectoLogin_AppService.UserAppService;
import ProyectoLogin_Model.Usuario;


/**
 *
 * @author ulise
 */
public class ControllerRegistro {

   public int insert(Usuario u) throws Exception {
       UserAppService us = new UserAppService();
       
       int id;
       id = us.save(u);
       
       return id;
   }
}
