package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import configuracion.Configuracion
import configuracion.ConfiguracionColor

class ConfiguracionColorMientras(private val color: ConfiguracionColor, indice: Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Mientras) {
                inst.colorFondo = color.colorInt()
            }

            inst.obtenerSubInstrucciones()?.let { aplicarConfiguracion(it) }
        }
    }
}