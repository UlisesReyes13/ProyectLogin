package ProyectoLogin_AppService;

import DAO.BooksDAO;
import ProectoLogin_ViewModels.LibroViewModel;
import ProyectoLogin_CQRS.BooksCQRS;
import ProyectoLogin_Model.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BooksAppService {

    public List<Book> getAll(String filtro) throws Exception {
        try {
            BooksDAO bdao = new BooksDAO();
            List<Book> books = bdao.getAll(filtro);
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int idLibro) throws Exception {
        BooksCQRS bcqrs = new BooksCQRS();
        bcqrs.delete(idLibro);
    }

    public void update(Book b) throws Exception {
        BooksCQRS bcqrs = new BooksCQRS();
        bcqrs.update(b);
    }

    public int insert(Book b) throws Exception {
        BooksCQRS bcqrs = new BooksCQRS();
        int id = bcqrs.insert(b);
        return id;
    }

    public List<LibroViewModel> getAllViewModel(String filtro) throws Exception {
        BooksDAO bdao = new BooksDAO();
        List<Book> books = bdao.getAll(filtro);

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

    /////////////////////////////////////////////////////////////////////////////////////////////
    public StringBuilder getAllLibrosURL() throws Exception {
        URL url = new URL("http://192.168.53.53:8084/proyecto_arso/api/libro/mostrarLibrosMapeados");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setRequestMethod("GET");
        httpCon.setDoInput(true);

        StringBuilder response = new StringBuilder();

        BufferedReader entrada = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
        String lineas;

        while ((lineas = entrada.readLine()) != null) {
            response.append(lineas);
        }

        entrada.close();

        return response;
    }

    public void insertExternBook(LibroViewModel l) throws MalformedURLException, UnsupportedEncodingException, IOException {

        URL url = new URL("http://192.168.53.53:8084/proyecto_arso/api/libro/registrarLibro");

        Map<String, Object> params = new LinkedHashMap<>();

        params.put("idLibro", l.getId());
        params.put("nombreLibro", l.getNombre_libro());
        params.put("descripcion", l.getDescripcion_libro());
        params.put("tema", l.getTema_libro());

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length",
                String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        for (int c = in.read(); c != -1; c = in.read()) {
            System.out.print((char) c);
        }
    }

    public void updateExternBook(LibroViewModel l) throws MalformedURLException, UnsupportedEncodingException, IOException {

        URL url = new URL("http://192.168.53.53:8084/proyecto_arso/api/libro/actualizarLibro");

        Map<String, Object> params = new LinkedHashMap<>();

        params.put("idLibro", l.getId());
        params.put("nombreLibro", l.getNombre_libro());
        params.put("descripcion", l.getDescripcion_libro());
        params.put("tema", l.getTema_libro());

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length",
                String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        for (int c = in.read(); c != -1; c = in.read()) {
            System.out.print((char) c);
        }
    }

    public void deleteExternBook(Book l) throws MalformedURLException, UnsupportedEncodingException, IOException {

        URL url = new URL("http://192.168.53.53:8084/proyecto_arso/api/libro/");

        Map<String, Object> params = new LinkedHashMap<>();

        params.put("idLibro", l.getIdLibro());

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length",
                String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        for (int c = in.read(); c != -1; c = in.read()) {
            System.out.print((char) c);
        }
    }
}
