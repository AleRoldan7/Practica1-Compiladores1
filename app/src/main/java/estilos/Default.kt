package estilos

import Modelo.Instrucciones
import android.graphics.Color
import configuracion.TipoFigura

class Default(val indice : Int) : ConfiguracionesUniversales() {
    override fun aplicarConfiguracion(lista: List<Instrucciones>) {
        lista.forEach { inst ->

            inst.colorFondo = Color.LTGRAY
            inst.colorTexto = Color.BLACK
            inst.tamLetra = 20f
            inst.figura = TipoFigura.RECTANGULO

            inst.obtenerSubInstrucciones()?.let {
                aplicarConfiguracion(it)
            }
        }

    }
}