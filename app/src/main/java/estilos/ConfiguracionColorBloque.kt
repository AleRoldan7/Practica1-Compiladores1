package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si
import estilos.ConfiguracionColor
import configuracion.LetraFuente
import kotlin.collections.forEach

class ConfiguracionColorBloque(val color : ConfiguracionColor, val indice : Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst !is Mientras && inst !is Si) {
                inst.colorFondo = color.colorInt()
            }

            inst.obtenerSubInstrucciones()?.let { aplicarConfiguracion(it) }
        }
    }
}