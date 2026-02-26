package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si
import configuracion.TipoFigura
import kotlin.collections.forEach

class ConfiguracionFiguraBloque(val figura : TipoFigura, val indice : Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst !is Mientras && inst !is Si) {
                inst.figura = figura
            }

            inst.obtenerSubInstrucciones()?.let { aplicarConfiguracion(it) }
        }
    }
}