package Modelo

class CicloMientras(private val condicion : String) : Instrucciones() {

    fun getCondicion() : String {
        return condicion
    }

    override fun getTipo(): String {
        return "MIENTRAS"
    }

    override fun toString(): String {
        return "MIENTRAS ($condicion)"
    }
}