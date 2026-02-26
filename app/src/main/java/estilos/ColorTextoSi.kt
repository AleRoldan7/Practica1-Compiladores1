package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si
import configuracion.Configuracion
import configuracion.ConfiguracionColor

class ColorTextoSi(private val color: ConfiguracionColor, indice: Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Si) {
                inst.colorTexto = color.colorInt()
            }

            inst.obtenerSubInstrucciones()?.let { aplicarConfiguracion(it) }
        }
    }
}