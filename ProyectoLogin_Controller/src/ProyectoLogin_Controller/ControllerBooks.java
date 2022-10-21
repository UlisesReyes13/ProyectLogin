package ProyectoLogin_Controller;

import DAO.BooksDAO;
import ProyectoLogin_Model.Book;
import java.util.List;

/**
 *
 * @author ulise
 */
public class ControllerBooks {
    public int insert(Book b) throws Exception {
        BooksDAO bdao = new BooksDAO();
        int id = bdao.insert(b);
        return id;
    }
    
    public int update(Book b) throws Exception {
        BooksDAO bdao = new BooksDAO();
        int id = bdao.update(b);
        return id;
    } 
    
    public void delete(int idLibro) throws Exception {
        BooksDAO bdao = new BooksDAO();
        
        bdao.delete(idLibro);
        
    }
    
    public List<Book> getAll(String filtro) throws Exception{
        try{
            BooksDAO bdao = new BooksDAO();
            List<Book> books = bdao.getAll(filtro);
            return books;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
