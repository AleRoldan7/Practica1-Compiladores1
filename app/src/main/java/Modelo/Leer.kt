package Modelo

class Leer(private var variable : String) : Instrucciones() {

    fun getVariable() : String {
        return variable
    }

    override fun getTipo(): String {
        return "LEER"
    }

    override fun toString(): String {
        return "LEER $variable"
    }
}