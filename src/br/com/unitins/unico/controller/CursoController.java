package br.com.unitins.unico.controller;

import br.com.unitins.unico.model.Curso;
import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.primefaces.shaded.json.JSONObject;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CursoController implements Serializable {

    private static final long serialVersionUID = 2127281323462631207L;

    private Curso curso;

    private Integer id;

    public void buscar(Integer id) {

        String url = "https://unicoapi-es.herokuapp.com/api/v1/cursos/" + id;

        try {

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200)
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output = "";
            String line;

            while ((line = br.readLine()) != null) {

                output += line;
            }

            conn.disconnect();

            Gson gson = new Gson();

            setCurso(gson.fromJson(new String(output.getBytes()), Curso.class));

            System.out.println("\nURL da API (Requisição via GET): " + url + "\n");

            System.out.println("ID: " + getCurso().getId());
            System.out.println("URL: " + getCurso().getUrl());
            System.out.println("TITULO: " + getCurso().getTitulo());
            System.out.println("ATIVO: " + getCurso().getAtivo() + "\n");
        } catch (IOException e) {

            e.printStackTrace();

            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Boolean cadastrarCurso(Curso curso) throws IOException {

        try {

            String url = "http://localhost:8000/api/v1/cursos/";

            Gson gson = new Gson();
            HttpClient hp = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            StringEntity postString = new StringEntity(gson.toJson(curso));

            post.setEntity(postString);
            post.setHeader("Content-type", "application/json");

            HttpResponse response = hp.execute(post);

            return true;
        }

        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public Boolean cadastrarCurso2(Curso curso) throws IOException {

        JSONObject json = new JSONObject();

        json.put("titulo", curso.getTitulo());
        json.put("url", curso.getUrl());
        json.put("ativo", curso.getAtivo());

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {

            HttpPost request = new HttpPost("http://localhost:8000/api/v1/cursos/");
            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            httpClient.execute(request);

            return true;
        }

        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

        finally {

            httpClient.close();
        }
    }

    public Curso getCurso() {

        if (curso == null)
            curso = new Curso();

        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
