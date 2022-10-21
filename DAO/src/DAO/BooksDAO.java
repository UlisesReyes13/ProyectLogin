package DAO;

import ProyectLogin_BD.ConexionMySQL;
import ProyectoLogin_Model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ulise
 */
public class BooksDAO {

    public int insert(Book b) throws Exception {

        String sql = "INSERT INTO book (titulo, tema, descripcion,estatus) VALUES (?,?,?,?)";

        int idBookGenerado = -1;

        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = null;

        pstmt.setString(1, b.getTitulo());
        pstmt.setString(2, b.getTema());
        pstmt.setString(3, b.getDescripcion());
        pstmt.setInt(4, 1);

        pstmt.executeUpdate();

        rs = pstmt.getGeneratedKeys();

        if (rs.next()) {
            idBookGenerado = rs.getInt(1);
            b.setIdLibro(idBookGenerado);
        }

        pstmt.close();
        conn.close();
        connMySQL.close();
        rs.close();

        return idBookGenerado;
    }

    public int update(Book b) throws Exception {
        String sql = "UPDATE book SET titulo = ?, tema = ?, descripcion = ? WHERE idLibro = ?";

        ConexionMySQL connMySQL = new ConexionMySQL();
        
        int idBookGenerado = -1;

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, b.getTitulo());
        pstmt.setString(2, b.getTema());
        pstmt.setString(3, b.getDescripcion());
        pstmt.setInt(4, b.getIdLibro());

        pstmt.executeUpdate();
        
        ResultSet rs = null;
        
        rs = pstmt.getGeneratedKeys();
        
        if(rs.next()){
            idBookGenerado = rs.getInt(1);
            b.setIdLibro(idBookGenerado);
        }

        pstmt.close();
        conn.close();
        connMySQL.close();
        
        return idBookGenerado;
    }

    public void delete(int idLibro) throws Exception {
        String sql = "UPDATE book SET estatus = 0 WHERE idLibro = ?";

        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setInt(1, idLibro);

        pstmt.executeUpdate();

        conn.close();
        pstmt.close();
        connMySQL.close();
    }

    public List<Book> getAll(String filtro) throws Exception {

        String sql = "SELECT * FROM book WHERE estatus = 1";

        List<Book> books = new ArrayList<Book>();

        Book b = null;

        ConexionMySQL connMySQL = new ConexionMySQL();

        Connection conn = connMySQL.open();

        PreparedStatement pstmt = conn.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            b = fillBook(rs);
            books.add(b);
        }

        pstmt.close();
        rs.close();
        conn.close();
        connMySQL.close();

        return books;
    }

    private Book fillBook(ResultSet rs) throws Exception {

        Book b = new Book();

        b.setIdLibro(rs.getInt("IdLibro"));
        b.setTitulo(rs.getString("titulo"));
        b.setTema(rs.getString("tema"));
        b.setDescripcion(rs.getString("descripcion"));
        b.setEstatus(rs.getInt("estatus"));

        return b;
    }
}
