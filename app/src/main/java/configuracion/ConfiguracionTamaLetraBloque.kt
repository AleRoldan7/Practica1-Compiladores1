package configuracion

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si

class ConfiguracionTamaLetraBloque(private val size: Float, indice: Int) : Configuracion(indice) {
    override fun aplicarA(e: Instrucciones) {
        if (e !is Si && e !is Mientras) e.tamLetra = size
    }
}