package estilos

import Modelo.Instrucciones
import Modelo.Si
import configuracion.Configuracion

class ConfiguracionTamaLetraSi(private val size: Float, indice: Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Si) {
                inst.tamLetra = size
            }

            inst.obtenerSubInstrucciones()?.let {
                aplicarConfiguracion(it)
            }
        }
    }
}