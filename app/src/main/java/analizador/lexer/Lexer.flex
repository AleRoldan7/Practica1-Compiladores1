package analizador.lexer;


%%

%{
    StringBuilder resultado = new StringBuilder();

    public String getResultado() {
        return resultado.toString();
    }
%}



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
"INICIO"                                {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"FIN"                                   {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"SI"                                    {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"ENTONCES"                              {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"MIENTRAS"                              {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"FINSI"                                 {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"FINMIENTRAS"                           {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"HACER"                                 {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"MOSTRAR"                               {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"LEER"                                  {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}
"VAR"                                   {resultado.append("PALABRA RESERVADA: ").append(yytext()).append("\n");}


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

