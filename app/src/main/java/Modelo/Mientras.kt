package Modelo

import configuracion.LetraFuente
import configuracion.TipoFigura


class Mientras(val condicion: String, val cuerpo: List<Instrucciones>, override val indice: Int)
    : Instrucciones(indice, figura = TipoFigura.ROMBO) {

    override fun toString() = "$condicion"

    fun getTextoCondicion(): String {
        return "¿$condicion?"
    }

    override fun obtenerSubInstrucciones(): List<Instrucciones>? {
        return cuerpo
    }
}

/*
class CicloMientras(
    private val condicion: String,
    private val cuerpo: Instrucciones?
) : Instrucciones() {

    fun getCondicion(): String = condicion
    fun getCuerpo(): Instrucciones? = cuerpo

    override fun getTipo(): String = "MIENTRAS"

    override fun toString(): String {
        return "MIENTRAS ($condicion) HACER ${cuerpo?.toString() ?: "sin cuerpo"}"
    }
}

 */