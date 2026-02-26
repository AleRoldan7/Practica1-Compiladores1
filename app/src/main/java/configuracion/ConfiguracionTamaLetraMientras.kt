package configuracion

import Modelo.Instrucciones
import Modelo.Mientras

class ConfiguracionTamaLetraMientras(private val size: Float, indice: Int) : Configuracion(indice) {
    override fun aplicarA(e: Instrucciones) { if (e is Mientras) e.tamLetra = size }
}