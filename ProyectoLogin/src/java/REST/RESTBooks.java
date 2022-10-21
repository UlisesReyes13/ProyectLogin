package REST;

import DAO.BooksDAO;
import ProyectoLogin_Controller.ControllerBooks;
import ProyectoLogin_Model.Book;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
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
@Path("libros")
public class RESTBooks {

    @Path("save")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@FormParam("idLibro") @DefaultValue("0") int id,
            @FormParam("titulo") @DefaultValue("") String titulo,
            @FormParam("tema") @DefaultValue("") String tema,
            @FormParam("descripcion") @DefaultValue("") String descripcion) {
        String out = null;

        ControllerBooks cb = new ControllerBooks();

        Book book = new Book();

        try {
            book.setIdLibro(id);
            book.setTitulo(titulo);
            book.setTema(tema);
            book.setDescripcion(descripcion);

            if (book.getIdLibro() == 0) {
                cb.insert(book);
            } else {
                cb.update(book);
            }
            out = new Gson().toJson(book);
        } catch (Exception e) {
            //Imprimimos el error en la consola del servidor:
            e.printStackTrace();

            //Devolvemos una descripción del Error 
            out = "{\"error\":\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@FormParam("idLibro") @DefaultValue("0") int id) {
        String out = null;

        ControllerBooks cb = new ControllerBooks();

        try {
            cb.delete(id);
        } catch (Exception e) {
            //Imprimimos el error en la consola del servidor:
            e.printStackTrace();

            //Devolvemos una descripción del Error 
            out = "{\"error\":\"" + e.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("getAll")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("filtro") @DefaultValue("") String filtro) {
        String out = null;
        ControllerBooks cb = new ControllerBooks();
        try {
            List<Book> productos = null;
            productos = cb.getAll(filtro);
            out = new Gson().toJson(productos);

        } catch (Exception e) {
            e.printStackTrace();
            out = "{\"error\":\"" + e.toString() + "\"}";
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("getAllAPI")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAPI() throws Exception {
        String out = null;
        URL url = new URL("");
        
        HttpURLConnection conHttp = (HttpURLConnection) url.openConnection();
        conHttp.setRequestMethod("GET");
        conHttp.setDoInput(true);
        
        StringBuilder response = new StringBuilder();
        
        BufferedReader entrada = new BufferedReader(new InputStreamReader(conHttp.getInputStream()));
        
        String lineas;
        
        while((lineas = entrada.readLine()) != null){
            response.append(lineas);
        }
        
        entrada.close();
        
        try{
            out = response.toString();
        }catch(Exception ex){
            ex.printStackTrace();
            out = "{\"error\":\"" + ex.toString() + "\"}";
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
