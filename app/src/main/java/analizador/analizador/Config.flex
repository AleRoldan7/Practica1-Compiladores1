package analizador.analizador;

import java_cup.runtime.*;
import java.util.ArrayList;
import Modelo.*;

%%

%public
%unicode
%cup
%class Config
%line
%column
%char

%{
            private Symbol symbol(int type) {
              return new Symbol(type, yyline + 1, yycolumn + 1, yytext());
            }

            private Symbol symbol(int type, Object value) {
                return new Symbol(type, yyline + 1, yycolumn +1, value);
            }

            public ArrayList<TokenError> errores = new ArrayList<>();

            private void error(String mensaje) {
                errores.add(new TokenError(yytext(), yyline+1, yycolumn+1, "Léxico", mensaje));
            }
%}

%eofval{
    return symbol(sym.EOF);
%eofval}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* Comentarios */
Comment = "#" {InputCharacter}* {LineTerminator}?

/* Números */
Digit = [0-9]
Integer = {Digit}+
Decimal = {Digit}* "." {Digit}+ | {Digit}+ "." {Digit}*
ExpNum  = [eE] [+-]? {Digit}+
Number  = {Integer} | {Decimal} ({ExpNum})?

/* Identificadores */
Identifier = [a-zA-Z_][a-zA-Z0-9_]*

/* Colores hexadecimales */
HexDigit = [0-9a-fA-F]
HexColor = H[0-9a-fA-F]{6}
/* Figuras */
Figura = ELIPSE | CIRCULO | PARALELOGRAMO | RECTANGULO | ROMBO | RECTANGULO_REDONDEADO

/* Fuentes */
Fuente = ARIAL | TIMES_NEW_ROMAN | COMIC_SANS | VERDANA

%%

<YYINITIAL> {

    /* Instrucciones de configuración */
    "%%%%"                  { return symbol(sym.SEPARADORSECCION); }
    "%DEFAULT="             { return symbol(sym.DEFAULT); }
    "%COLOR_TEXTO_SI="      { return symbol(sym.COLOR_TEXTO_SI); }
    "%COLOR_SI="            { return symbol(sym.COLOR_SI); }
    "%FIGURA_SI="           { return symbol(sym.FIGURA_SI); }
    "%LETRA_SI="            { return symbol(sym.LETRA_SI); }
    "%LETRA_SIZE_SI="       { return symbol(sym.LETRA_SIZE_SI); }

    "%COLOR_TEXTO_MIENTRAS=" { return symbol(sym.COLOR_TEXTO_MIENTRAS); }
    "%COLOR_MIENTRAS="       { return symbol(sym.COLOR_MIENTRAS); }
    "%FIGURA_MIENTRAS="      { return symbol(sym.FIGURA_MIENTRAS); }
    "%LETRA_MIENTRAS="       { return symbol(sym.LETRA_MIENTRAS); }
    "%LETRA_SIZE_MIENTRAS="  { return symbol(sym.LETRA_SIZE_MIENTRAS); }

    "%COLOR_TEXTO_BLOQUE="   { return symbol(sym.COLOR_TEXTO_BLOQUE); }
    "%COLOR_BLOQUE="         { return symbol(sym.COLOR_BLOQUE); }
    "%FIGURA_BLOQUE="        { return symbol(sym.FIGURA_BLOQUE); }
    "%LETRA_BLOQUE="         { return symbol(sym.LETRA_BLOQUE); }
    "%LETRA_SIZE_BLOQUE="    { return symbol(sym.LETRA_SIZE_BLOQUE); }

    /* Separadores y terminadores */
    "="                     { return symbol(sym.IGUAL); }
    "|"                     { return symbol(sym.PIPE); }
    ","                     { return symbol(sym.COMA); }
    "("                     { return symbol(sym.PARENTESIS_ABRE); }
    ")"                     { return symbol(sym.PARENTESIS_CIERRA); }

    /* Operadores aritméticos */
    "+"                     { return symbol(sym.SUMA); }
    "-"                     { return symbol(sym.RESTA); }
    "*"                     { return symbol(sym.MULTI); }
    "/"                     { return symbol(sym.DIVISION); }

    /* Literales numéricos */
    {Integer} { return symbol(sym.ENTERO, Integer.parseInt(yytext())); }
    {Decimal} { return symbol(sym.DECIMAL, Double.parseDouble(yytext())); }

    /* Colores hexadecimales */
    {HexColor}              { return symbol(sym.HEXCOLOR, yytext()); }

    /* Figuras y fuentes */
    {Figura}                { return symbol(sym.FIGURA, yytext()); }
    {Fuente}                { return symbol(sym.FUENTE, yytext()); }

    /* Ignorar espacios y comentarios */
    {WhiteSpace}            { /* ignorar */ }
    {Comment}               { /* ignorar */ }

    /* Cualquier otro carácter error */
    .                       {
        error("Carácter no reconocido");

    }
}

