package br.com.unitins.unico.model;

public class Endereco {

    // Testando requisição na API de CEP dos Correios

    String logradouro;
    String bairro;
    String localidade;

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    @Override
    public String toString() {
        return "Endereco{" + "logradouro=" + logradouro + ", bairro=" + bairro + ", localidade=" + localidade + '}';
    }
}