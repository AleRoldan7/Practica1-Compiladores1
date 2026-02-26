package Modelo

import configuracion.LetraFuente
import configuracion.TipoFigura


class Declaracion(val variable : String, val valor : String,
                  override val indice: Int) : Instrucciones(indice) {

    override fun toString() = "$variable = $valor"
}


/*
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

 */