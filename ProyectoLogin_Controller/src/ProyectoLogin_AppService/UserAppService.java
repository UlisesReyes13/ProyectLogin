/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoLogin_AppService;

import DAO.UserDAO;
import ProyectoLogin_CQRS.UserCQRS;
import ProyectoLogin_Model.Usuario;

/**
 *
 * @author ulise
 */
public class UserAppService {
    
   public Usuario login(String usuario, String contraseña) throws Exception {
        Usuario u = new Usuario();
        UserDAO ud = new UserDAO();
        
        u = ud.login(usuario, contraseña);
        return u;
    }
   
   
   public int save(Usuario u) throws Exception{
       int id;
       
       UserCQRS uc = new UserCQRS();
       
       id = uc.insert(u);
       
       return id;
   }
    
}
