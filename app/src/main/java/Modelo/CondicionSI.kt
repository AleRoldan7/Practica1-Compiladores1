package Modelo

class CondicionSI(private var condicion: String) : Instrucciones() {

    fun getCondicion(): String {
        return condicion
    }

    override fun getTipo(): String {
        return "SI"
    }

    override fun toString(): String {
        return "SI ($condicion)"
    }
}