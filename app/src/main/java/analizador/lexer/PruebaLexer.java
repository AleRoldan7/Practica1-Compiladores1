package analizador.lexer;

import java.io.IOException;
import java.io.StringReader;

public class PruebaLexer {

    public static void main(String[] args) throws IOException {

        String entrada = """
                INICIO
                VAR a = 10
                VAR b = 20
                && | <= >= ==
                # HOLA MUNDO
                #HOLA MUNDO
                #HOLAMUNDO
                SI (a < b) ENTONCES
                MOSTRAR "a es menor que b"
                FIN SI
                MIENTRAS (a < 15) HACER
                a = a + 1
                MOSTRAR a
                FIN MIENTRAS
                MOSTRAR "Fin del programa"
                FIN
                         """;



        StringReader reader = new StringReader(entrada);

        Lexer lexer = new Lexer(reader);

        while (lexer.yylex() != -1) {

        }

    }
}
