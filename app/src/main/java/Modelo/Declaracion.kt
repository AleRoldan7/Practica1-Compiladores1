package Modelo

class Declaracion(private var variable : String, private var valor : String) : Instrucciones() {

    fun getVariable(): String {
        return variable
    }

    fun getValor(): String {
        return valor
    }

    override fun getTipo(): String {
        return "DECLARACION"
    }

    override fun toString(): String {
        return "VAR $variable = $valor"
    }
}