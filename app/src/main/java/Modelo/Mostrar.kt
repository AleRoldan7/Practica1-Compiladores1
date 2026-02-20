package Modelo

import android.R

class Mostrar(private var texto : String) : Instrucciones() {

    fun getTexto(): String {
        return texto
    }
    override fun getTipo(): String {
        return "MOSTRAR"
    }

    override fun toString(): String {
        return "MOSTRAR \"$texto\""
    }
}