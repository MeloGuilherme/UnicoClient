package br.com.unitins.unico.controller;

import br.com.unitins.unico.application.Util;
import br.com.unitins.unico.model.Endereco;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EnderecoController implements Serializable {

    private static final long serialVersionUID = -4170983865804153695L;

    private static final String WS_CEP = "https://viacep.com.br/ws/";

    public String buscarEnderecoPorCep(String cep) {

        String url = WS_CEP + cep + "/json";



        try {

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            if (conn.getResponseCode() != 200)
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String jsonEmString = Util.converterJsonEmString(br);

            Gson gson = new Gson();
            Endereco endereco = gson.fromJson(jsonEmString, Endereco.class);

            return endereco.toString();
        }

        catch (Exception e) {

            e.printStackTrace();
            return "\nDeu erro aí meu brother KKK";
        }
    }
}
