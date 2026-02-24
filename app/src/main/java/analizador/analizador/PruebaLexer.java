package analizador.analizador;

import java.io.IOException;
import java.io.StringReader;

public class PruebaLexer {

    public static void main(String[] args) throws Exception {

        String entrada = "VARa";

        StringReader lexer = new StringReader(entrada);

        Lexer lexico = new Lexer(lexer);

        System.out.println(lexico);


    }
}
