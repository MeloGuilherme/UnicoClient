package br.com.unitins.unico.testes;

import br.com.unitins.unico.controller.CursoController;
import br.com.unitins.unico.controller.EnderecoController;
import br.com.unitins.unico.model.Curso;

import java.io.IOException;

public class TestadorDeMetodos {

//    public static void main(String[] args) throws IOException {
//
////        EnderecoController ec = new EnderecoController();
////
////        System.out.println(ec.buscarEnderecoPorCep("77001256"));
//
//        Curso curso = new Curso("https://www.cursos.com.br/algprogintermed",
//                "Algoritmos e Programacao (Intermediário)", true);
//
//        CursoController cc = new CursoController();
//
////        cc.cadastrarCurso(curso);
//        System.out.println(cc.cadastrarCurso2(curso));
//    }

    public static void main(String[] args) throws IOException {

        CursoController cc = new CursoController();

        Curso c = new Curso();

        c.setTitulo("Teste titulo");
        c.setUrl("Teste url");
        c.setAtivo(true);

        cc.cadastrarCurso2(c);
    }
}
