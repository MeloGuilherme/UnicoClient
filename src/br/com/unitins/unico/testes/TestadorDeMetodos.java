package br.com.unitins.unico.testes;

import br.com.unitins.unico.controller.EnderecoController;

import java.io.IOException;

public class TestadorDeMetodos {

    public static void main(String[] args) throws IOException {

        EnderecoController ec = new EnderecoController();

        System.out.println(ec.buscarEnderecoPorCep("77001032"));
    }

}
