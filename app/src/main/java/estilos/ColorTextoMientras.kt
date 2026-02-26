package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si
import estilos.ConfiguracionColor
import kotlin.collections.forEach

class ColorTextoMientras(private val color: ConfiguracionColor, indice: Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Mientras) {
                inst.colorTexto = color.colorInt()
            }

            inst.obtenerSubInstrucciones()?.let { aplicarConfiguracion(it) }
        }
    }
}