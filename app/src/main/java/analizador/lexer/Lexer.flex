package analizador.lexer;

%%
%public
%unicode
%class Lexer
%line
%column
%standalone

/*EXPRESIONES A UTILIZAR*/

DIGITO = [0-9]
SEPARADORES = [ \n\r\t]
LETTER = [a-zA-Z]
COMILLAS = \"[^\"]*\"
COMENTARIO = "#"[^(\r\n)]*
%%

/*TOKENS A UTILIZAR*/

/*EXPRESIONES PARA SEPARAR Y NUMEROS ENTEROS Y DECIMALES*/
{SEPARADORES}                    {/*  ignoramos */}
{DIGITO}+                        {System.out.println("Entero: " + yytext());}
{DIGITO}+(\.{DIGITO}+)?          {System.out.println("Decimal: " + yytext());}

/*PALABRAS RESERVADAS*/
"INICIO"                                {System.out.println("PALABRA RESERVADA: " + yytext());}
"FIN"                                   {System.out.println("PALABRA RESERVADA: " + yytext());}
"SI"                                    {System.out.println("PALABRA RESERVADA: " + yytext());}
"ENTONCES"                              {System.out.println("PALABRA RESERVADA: " + yytext());}
"MIENTRAS"                              {System.out.println("PALABRA RESERVADA: " + yytext());}
"FINSI"                                 {System.out.println("PALABRA RESERVADA: " + yytext());}
"FINMIENTRAS"                           {System.out.println("PALABRA RESERVADA: " + yytext());}
"HACER"                                 {System.out.println("PALABRA RESERVADA: " + yytext());}
"MOSTRAR"                               {System.out.println("PALABRA RESERVADA: " + yytext());}
"LEER"                                  {System.out.println("PALABRA RESERVADA: " + yytext());}
"VAR"                                   {System.out.println("PALABRA RESERVADA: " + yytext());}


/*SIMBOLOS*/
"+"                                     {System.out.println("SIMBOLO: " + yytext());}
"-"                                     {System.out.println("SIMBOLO: " + yytext());}
"*"                                     {System.out.println("SIMBOLO: " + yytext());}
"/"                                     {System.out.println("SIMBOLO: " + yytext());}
"="                                     {System.out.println("SIMBOLO: " + yytext());}
"!"                                     {System.out.println("SIMBOLO: " + yytext());}
"<"                                     {System.out.println("SIMBOLO: " + yytext());}
">"                                     {System.out.println("SIMBOLO: " + yytext());}
"=="                                    {System.out.println("IGUAL: " + yytext());}
"!="                                    {System.out.println("DISTINTO: " + yytext());}
">="                                    {System.out.println("MAYOR QUE: " + yytext());}
"<="                                    {System.out.println("MENOR QUE: " + yytext());}
"&&"                                    {System.out.println("SIMBOLO: " + yytext());}
"||"                                    {System.out.println("SIMBOLO: " + yytext());}
"("                                     {System.out.println("SIMBOLO: " + yytext());}
")"                                     {System.out.println("SIMBOLO: " + yytext());}


/*COMILLAS Y COMENTARIOS*/
{COMILLAS}                              {System.out.println("COMILLAS: " + yytext());}
{COMENTARIO}                            {System.out.println("COMENTARIO: " + yytext());}

/*PARA VARIABLES*/
{LETTER}+                                {System.out.println("Letra: " + yytext());}

/*PARA DECIR QUE ES UN ERROR*/
.                                       {System.out.println("Error: " + yytext());}

