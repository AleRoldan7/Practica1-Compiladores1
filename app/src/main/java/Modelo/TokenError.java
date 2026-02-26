package Modelo;

import java.io.Serializable;

public class TokenError implements Serializable {

    public String lexema;
    public int linea;
    public int columna;
    public String tipo;
    public String descripcion;

    public TokenError(String lexema, int linea, int columna, String tipo, String descripcion) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String toString() {
        return tipo + " -> " + lexema + " (" + linea + "," + columna + ")";
    }
}
