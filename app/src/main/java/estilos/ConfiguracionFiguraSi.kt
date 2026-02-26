package estilos

import Modelo.Instrucciones
import Modelo.Si
import configuracion.TipoFigura
import kotlin.collections.forEach

class ConfiguracionFiguraSi (private val figura: TipoFigura, indice: Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Si) {
                inst.figura = figura
            }

            inst.obtenerSubInstrucciones()?.let {
                aplicarConfiguracion(it)
            }
        }
    }
}