/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import ProyectoLogin_Controller.ControllerLogin;
import ProyectoLogin_Model.Usuario;
import com.google.gson.Gson;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ulise
 */
@Path("login")
public class RESTLogin {
    
    @Path("validar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response validar(@QueryParam("nombreUsuario") @DefaultValue("") String nombreUsuario,
                            @QueryParam("contrasenia") @DefaultValue("") String contrasenia){
        String out = "";
        
        ControllerLogin cl = new ControllerLogin();
        Usuario u = null;
        
        try{
            u = cl.login(nombreUsuario, contrasenia);
            
            if(u != null)
                out = new Gson().toJson(u);
            
            else 
                out = "{\"error\":\"Datos de acceso incorrectos. Intente nuevamente.\"}";
            
        }
        
        catch(Exception ex){
            ex.printStackTrace();
            out = "{\"error\":\"Ocurrió un error en el servidor. Intenta nuevamente o llama al Administrador de sistemas.\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    
    @Path("out")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response out(@FormParam ("idUsuario") @DefaultValue("0") String idUsuario)
    {
        String out = "";
        try
        {
            Usuario objU = new Usuario();
            objU.setIdUsuario(Integer.parseInt(idUsuario));
            out = "{\"result\":\"Ok\"}";
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            out = "{\"error\":\"Se generó un error en el cierre de sesión\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

}