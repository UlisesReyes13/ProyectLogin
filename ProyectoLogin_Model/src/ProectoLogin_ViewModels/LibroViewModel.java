package ProectoLogin_ViewModels;
/**
 *
 * @author ulise
 */
public class LibroViewModel {
    
    int id;
    String nombre_libro;
    String tema_libro;
    String descripcion_libro;
    int estatus_libro;

    public LibroViewModel() {
    }

    public LibroViewModel(int id, String nombre_libro, String tema_libro, String descripcion_libro, int estatus_libro) {
        this.id = id;
        this.nombre_libro = nombre_libro;
        this.tema_libro = tema_libro;
        this.descripcion_libro = descripcion_libro;
        this.estatus_libro = estatus_libro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_libro() {
        return nombre_libro;
    }

    public void setNombre_libro(String nombre_libro) {
        this.nombre_libro = nombre_libro;
    }

    public String getTema_libro() {
        return tema_libro;
    }

    public void setTema_libro(String tema_libro) {
        this.tema_libro = tema_libro;
    }

    public String getDescripcion_libro() {
        return descripcion_libro;
    }

    public void setDescripcion_libro(String descripcion_libro) {
        this.descripcion_libro = descripcion_libro;
    }

    public int getEstatus_libro() {
        return estatus_libro;
    }

    public void setEstatus_libro(int estatus_libro) {
        this.estatus_libro = estatus_libro;
    }
    
    
}
