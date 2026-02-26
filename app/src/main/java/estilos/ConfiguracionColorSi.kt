package estilos

import Modelo.Instrucciones
import Modelo.Si
import configuracion.Configuracion
import configuracion.ConfiguracionColor

class ConfiguracionColorSi(private val color: ConfiguracionColor, indice: Int) : ConfiguracionesUniversales() {


    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Si) {
                inst.colorFondo = color.colorInt()
            }

            inst.obtenerSubInstrucciones()?.let { aplicarConfiguracion(it) }
        }
    }
}