/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import DAO.UserDAO;
import ProyectoLogin_Controller.ControllerRegistro;
import ProyectoLogin_Model.Usuario;
import com.google.gson.Gson;
import javax.ws.rs.DefaultValue;
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
@Path("registro")
public class RESTRegistrar {

    @Path("Registrar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrar(@QueryParam("idUsuario") @DefaultValue("0") int idUsuario,
            @QueryParam("nombre") String nombre,
            @QueryParam("apePaterno") String apePaterno,
            @QueryParam("apeMaterno") String apeMaterno,
            @QueryParam("nombreUsuario") String nombreUsuario,
            @QueryParam("contrasenia") String contrasenia) {

        String out = null;

        ControllerRegistro cr = new ControllerRegistro();
        Usuario u = new Usuario();

        try {
            u.setIdUsuario(idUsuario);
            u.setNombre(nombre);
            u.setApePaterno(apePaterno);
            u.setApeMaterno(apeMaterno);
            u.setNombreUsuario(nombreUsuario);
            u.setContrasenia(contrasenia);
            System.out.println(nombre);

            if (cr.insert(u) != 2) {
                if (u.getIdUsuario() == 0) {
                    cr.insert(u);
                }
                out = new Gson().toJson(u);
            } else {
                out = "{\"error\":\"Usuario previamente registrado\"}";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            out = "{\"error\":\"" + ex.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }

}
