package br.com.unitins.unico.application;

import java.io.BufferedReader;
import java.io.IOException;

public class Util {

    public static String converterJsonEmString(BufferedReader bufferedReader) throws IOException {

        String resposta, jsonEmString = "";

        while ((resposta = bufferedReader.readLine()) != null) {

            jsonEmString += resposta;
        }

        return jsonEmString;
    }

    public static StringBuilder jsonBuffer(BufferedReader bufferedReader) throws IOException {

        var builder = new StringBuilder();

        String line;

        while ((line = bufferedReader.readLine()) != null) {

            builder.append(line);
            builder.append(System.lineSeparator());
        }

        return builder;
    }
}
