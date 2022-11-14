package ProyectoLogin_Controller;

import DAO.BooksDAO;
import ProectoLogin_ViewModels.LibroViewModel;
import ProyectoLogin_AppService.BooksAppService;
import ProyectoLogin_Model.Book;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ulise
 */
public class ControllerBooks {
    public int insert(Book b) throws Exception {
       BooksAppService bas = new BooksAppService();
       int id = bas.insert(b);
       return id;
    }
    
    public void update(Book b) throws Exception {
        BooksAppService bas = new BooksAppService();
        bas.update(b);
    } 
    
    public void delete(int idLibro) throws Exception {
        BooksAppService bas = new BooksAppService();
        
        bas.delete(idLibro);
        
    }
    
    public List<Book> getAll(String filtro) throws Exception{
        try{
            BooksAppService bas = new BooksAppService();
            List<Book> books = bas.getAll(filtro);
            return books;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    //////////////////////////////////////////////////////7
    public List<LibroViewModel> getAllViewModel(String filtro) throws Exception{
        BooksAppService baps = new BooksAppService();
        List<Book> books = baps.getAll(filtro);
        
        List<LibroViewModel> libroMap = new ArrayList<LibroViewModel>();
        
        for (int i = 0; i < books.size(); i++) {
            LibroViewModel item = new LibroViewModel(
            books.get(i).getIdLibro(),
            books.get(i).getTitulo(),
            books.get(i).getTema(),
            books.get(i).getDescripcion(),
            books.get(i).getEstatus()
            );
        
            libroMap.add(item);
        }
        return libroMap;
    }
    
    public void insertOther(LibroViewModel u) throws Exception{
          BooksAppService ap = new BooksAppService();
          ap.insertExternBook(u);
      } 
      
      public void upDateOther(LibroViewModel u) throws Exception{
          BooksAppService ap = new BooksAppService();
          ap.updateExternBook(u);
      }
      
      public void deleteOther(Book u) throws Exception{
          BooksAppService ap = new BooksAppService();
          ap.deleteExternBook(u);
      }
    
}
