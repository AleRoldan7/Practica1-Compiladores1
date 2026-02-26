package Modelo

class Asignacion(val varibale : String, val expresion : String, override val indice: Int) : Instrucciones(indice) {

    override fun toString() = "$varibale = $expresion"
}