package Modelo

class Leer(val variable : String, override val indice: Int) : Instrucciones(indice) {

    override fun toString() = "$variable"
}


/*
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

 */