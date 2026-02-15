package analizador.analizador;

import java_cup.runtime.Symbol;

%%

%public
%unicode
%cup
%class Lexer
%line
%column

%{
    private Symbol symbol(int type) {
      return new Symbol(type, yyline, yycolumn, yytext());
    }

%}



/*EXPRESIONES A UTILIZAR*/

DIGITO = [0-9]
SEPARADORES = [ \n\r\t]
LETTER = [a-zA-Z]
COMILLAS = \"[^\"]*\"
COMENTARIO = "#"[^(\r\n)]*
ENTERO = {DIGITO}+
DECIMAL = {DIGITO}+\.{DIGITO}+
ID = {LETTER}({LETTER} | {DIGITO} | "_")*

%%

/*TOKENS A UTILIZAR*/

/*EXPRESIONES PARA SEPARAR Y NUMEROS ENTEROS Y DECIMALES*/
{SEPARADORES}                    {/*  ignoramos */}

{DECIMAL}        { return symbol(sym.DECIMAL); }
{ENTERO}         { return symbol(sym.ENTERO); }

/*PALABRAS RESERVADAS*/
"INICIO"         { return symbol(sym.INICIO); }
"FIN"            { return symbol(sym.FIN); }
"SI"             { return symbol(sym.SI); }
"ENTONCES"       { return symbol(sym.ENTONCES); }
"MIENTRAS"       { return symbol(sym.MIENTRAS); }
"FINSI"          { return symbol(sym.FINSI); }
"FINMIENTRAS"    { return symbol(sym.FINMIENTRAS); }
"HACER"          { return symbol(sym.HACER); }
"MOSTRAR"        { return symbol(sym.MOSTRAR); }
"LEER"           { return symbol(sym.LEER); }
"VAR"            { return symbol(sym.VAR); }


/*SIMBOLOS*/
"=="             { return symbol(sym.IGUALIGUAL); }
"!="             { return symbol(sym.DIFERENTE); }
">="             { return symbol(sym.MAYORIGUAL); }
"<="             { return symbol(sym.MENORIGUAL); }
"&&"             { return symbol(sym.AND); }
"||"             { return symbol(sym.OR); }

"+"              { return symbol(sym.SUMA); }
"-"              { return symbol(sym.RESTA); }
"*"              { return symbol(sym.MULTI); }
"/"              { return symbol(sym.DIVISION); }
"="              { return symbol(sym.IGUAL); }
">"              { return symbol(sym.MAYOR); }
"<"              { return symbol(sym.MENOR); }
"("              { return symbol(sym.PARENTESIS_ABRE); }
")"              { return symbol(sym.PARENTESIS_CIERRA); }


/*COMILLAS Y COMENTARIOS*/
{COMILLAS}       { return symbol(sym.CADENA); }
{COMENTARIO}     { return symbol(sym.COMENTARIO); }

/*PARA VARIABLES*/
{ID}       { return symbol(sym.VARIABLE);}

/*PARA DECIR QUE ES UN ERROR*/
.                                       { return symbol(sym.ERROR); }

