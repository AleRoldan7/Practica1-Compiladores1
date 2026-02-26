package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si
import kotlin.collections.forEach

class ConfiguracionTamaLetraMientras(private val size: Float, indice: Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Mientras) {
                inst.tamLetra = size
            }

            inst.obtenerSubInstrucciones()?.let {
                aplicarConfiguracion(it)
            }
        }
    }
}