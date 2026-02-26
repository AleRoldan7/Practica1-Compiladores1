package Modelo

import configuracion.LetraFuente
import configuracion.TipoFigura


class Si(val condicion : String, val cuerpoCondicion : List<Instrucciones>, override val indice: Int)
    : Instrucciones(indice, figura = TipoFigura.ROMBO) {

    override fun toString() = "$condicion"

    fun getTextoCondicion(): String {
        return "¿$condicion?"
    }

    override fun obtenerSubInstrucciones(): List<Instrucciones>? {
        return cuerpoCondicion
    }
}



/*
class CondicionSI(
    private val condicion: String,
    private val entonces: Instrucciones?
) : Instrucciones() {

    fun getCondicion(): String = condicion
    fun getEntonces(): Instrucciones? = entonces

    override fun getTipo(): String = "SI"

    override fun toString(): String {
        return "SI ($condicion) ENTONCES ${entonces?.toString() ?: "sin cuerpo"}"
    }
}

 */