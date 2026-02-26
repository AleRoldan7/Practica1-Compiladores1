package analizador.analizador;

import Modelo.TokenError;
import Modelo.ModeloLexer;
import java_cup.runtime.Symbol;
import java.util.ArrayList;

%%

%public
%unicode
%cup
%class Lexer
%line
%column

%{
                private Symbol symbol(int type) {
                    return new Symbol(type, yyline + 1, yycolumn + 1, yytext());
                }
                private Symbol symbol(int type, Object value) {
                    return new Symbol(type, yyline + 1, yycolumn + 1, value);
                }

                public ArrayList<TokenError> errores = new ArrayList<>();

                private void error(String mensaje) {
                    errores.add(new TokenError(yytext(), yyline+1, yycolumn+1, "Léxico", mensaje));
                }
%}

%eofval{
    return new Symbol(sym.EOF);
%eofval}

/*EXPRESIONES A UTILIZAR*/

DIGITO = [0-9]
LETTER = [a-zA-Z]
SEPARADORES = [ \n\r\t]+

ENTERO = {DIGITO}+
DECIMAL = {DIGITO}+\.{DIGITO}+

ID = {LETTER}({LETTER}|{DIGITO}|"_")*

CADENA = \"[^\"]*\"

COMENTARIO = "#"[^(\r\n)]*

HEXCOLOR = "#"([0-9a-fA-F]{6})

FIGURA = ELIPSE|CIRCULO|PARALELOGRAMO|RECTANGULO|ROMBO|RECTANGULO_REDONDEADO
FUENTE = ARIAL|TIMES_NEW_ROMAN|COMIC_SANS|VERDANA

%%

/*  IGNORAR  */

{SEPARADORES}   { }
{COMENTARIO}    { }

/*  CONFIGURACIÓN  */

"%%%%"                     { return symbol(sym.SEPARADORSECCION); }

"%DEFAULT"                 { return symbol(sym.DEFAULT); }

"%COLOR_TEXTO_SI"          { return symbol(sym.COLOR_TEXTO_SI); }
"%COLOR_SI"                { return symbol(sym.COLOR_SI); }
"%FIGURA_SI"               { return symbol(sym.FIGURA_SI); }
"%LETRA_SI"                { return symbol(sym.LETRA_SI); }
"%LETRA_SIZE_SI"           { return symbol(sym.LETRA_SIZE_SI); }

"%COLOR_TEXTO_MIENTRAS"    { return symbol(sym.COLOR_TEXTO_MIENTRAS); }
"%COLOR_MIENTRAS"          { return symbol(sym.COLOR_MIENTRAS); }
"%FIGURA_MIENTRAS"         { return symbol(sym.FIGURA_MIENTRAS); }
"%LETRA_MIENTRAS"          { return symbol(sym.LETRA_MIENTRAS); }
"%LETRA_SIZE_MIENTRAS"     { return symbol(sym.LETRA_SIZE_MIENTRAS); }

"%COLOR_TEXTO_BLOQUE"      { return symbol(sym.COLOR_TEXTO_BLOQUE); }
"%COLOR_BLOQUE"            { return symbol(sym.COLOR_BLOQUE); }
"%FIGURA_BLOQUE"           { return symbol(sym.FIGURA_BLOQUE); }
"%LETRA_BLOQUE"            { return symbol(sym.LETRA_BLOQUE); }
"%LETRA_SIZE_BLOQUE"       { return symbol(sym.LETRA_SIZE_BLOQUE); }

/*  PALABRAS RESERVADAS  */

"INICIO"        { return symbol(sym.INICIO); }
"FIN"           { return symbol(sym.FIN); }
"SI"            { return symbol(sym.SI); }
"ENTONCES"      { return symbol(sym.ENTONCES); }
"MIENTRAS"      { return symbol(sym.MIENTRAS); }
"FINSI"         { return symbol(sym.FINSI); }
"FINMIENTRAS"   { return symbol(sym.FINMIENTRAS); }
"HACER"         { return symbol(sym.HACER); }
"MOSTRAR"       { return symbol(sym.MOSTRAR); }
"LEER"          { return symbol(sym.LEER); }
"VAR"           { return symbol(sym.VAR); }

/* OPERADORES  */

"=="            { return symbol(sym.IGUALIGUAL); }
"!="            { return symbol(sym.DIFERENTE); }
">="            { return symbol(sym.MAYORIGUAL); }
"<="            { return symbol(sym.MENORIGUAL); }

"&&"            { return symbol(sym.AND); }
"||"            { return symbol(sym.OR); }

"="             { return symbol(sym.IGUAL); }
">"             { return symbol(sym.MAYOR); }
"<"             { return symbol(sym.MENOR); }

"+"             { return symbol(sym.SUMA); }
"-"             { return symbol(sym.RESTA); }
"*"             { return symbol(sym.MULTI); }
"/"             { return symbol(sym.DIVISION); }

"!"             { return symbol(sym.NOT); }

"("             { return symbol(sym.PARENTESIS_ABRE); }
")"             { return symbol(sym.PARENTESIS_CIERRA); }

","             { return symbol(sym.COMA); }
"|"             { return symbol(sym.PIPE); }


/*  LITERALES  */

{DECIMAL}       { return symbol(sym.DECIMAL, yytext()); }
{ENTERO}        { return symbol(sym.ENTERO, yytext()); }

{CADENA}        { return symbol(sym.CADENA, yytext()); }

{HEXCOLOR}      { return symbol(sym.HEXCOLOR, yytext()); }

{FIGURA}        { return symbol(sym.FIGURA, yytext()); }
{FUENTE}        { return symbol(sym.FUENTE, yytext()); }

/* IDENTIFICADORES  */

{ID}            { return symbol(sym.VARIABLE, yytext()); }

/* ERROR  */

. {
    ModeloLexer.listaErrores.add(
        new TokenError(
            yytext(),
            yyline + 1,
            yycolumn + 1,
            "Léxico",
            "Símbolo no existe en el lenguaje"
        )
    );
}