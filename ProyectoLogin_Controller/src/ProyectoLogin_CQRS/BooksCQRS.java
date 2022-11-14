
package ProyectoLogin_CQRS;

import DAO.BooksDAO;
import ProyectoLogin_Model.Book;

public class BooksCQRS {
    
    public void delete(int idLibro) throws Exception {
        BooksDAO bdao = new BooksDAO();
        
        bdao.delete(idLibro);
        
    }
    
    public void update(Book b) throws Exception {
        BooksDAO bdao = new BooksDAO();
        bdao.update(b);
    }
    
    public int insert(Book b) throws Exception {
      BooksDAO bdao = new BooksDAO();
       int id = bdao.insert(b);
       return id;
    }
}
