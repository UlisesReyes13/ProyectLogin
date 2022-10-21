
package ProyectoLogin_Model;

/**
 *
 * @author ulise
 */
public class Book {
    
    int idLibro;
    String titulo;
    String tema;
    String descripcion;
    int Estatus;

    public Book() {
    }

    public Book(int idLibro, String titulo, String tema, String descripcion, int Estatus) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.tema = tema;
        this.descripcion = descripcion;
        this.Estatus = Estatus;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstatus() {
        return Estatus;
    }

    public void setEstatus(int Estatus) {
        this.Estatus = Estatus;
    }
    
}
