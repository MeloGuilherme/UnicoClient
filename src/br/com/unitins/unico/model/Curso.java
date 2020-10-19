package br.com.unitins.unico.model;

public class Curso {

    private Integer id;
    private String url;
    private String titulo;
    private Boolean ativo;

    public Curso() {

    }

    public Curso(String url, String titulo, Boolean ativo) {

        this.url = url;
        this.titulo = titulo;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
