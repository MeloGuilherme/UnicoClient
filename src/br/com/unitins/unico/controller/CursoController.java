package br.com.unitins.unico.controller;

import br.com.unitins.unico.application.Util;
import br.com.unitins.unico.model.Curso;
import br.com.unitins.unico.model.Endereco;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
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

    private static final String URL_PRINCIPAL = "https://unicoapi-es.herokuapp.com/api/v1/cursos/";
    private static final String CHARSET_UTF8 = ";charset=utf-8";

    @PostConstruct
    public void init() {

        setCurso(new Curso());
    }

    public void buscarPorId(Integer id) {

        String url = URL_PRINCIPAL + id;

        try {

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200)
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            System.out.println(Util.converterJsonEmString(br));
        }

        catch (IOException e) {

            Logger.getLogger(CursoController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void cadastrarCurso() {

        Gson gson = new Gson();

        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {

            var request = new HttpPost(URL_PRINCIPAL);
            request.setHeader("Content-type", "application/json" + CHARSET_UTF8);
            request.setHeader("Authorization", "Token e15c3ef620ad56bc08d64193c411409bdcd9d8c7");
            request.setEntity(new StringEntity(gson.toJson(getCurso())));

            HttpResponse response = client.execute(request);

            var br = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent()));

            System.out.println(Util.jsonBuffer(br));
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void limpar() {

        setCurso(null);
        init();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}