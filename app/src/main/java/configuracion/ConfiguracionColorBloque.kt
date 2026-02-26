package configuracion

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si

class ConfiguracionColorBloque(private val color: ConfiguracionColor, indice: Int) : Configuracion(indice) {
    override fun aplicarA(e: Instrucciones) {
        if (e !is Si && e !is Mientras) e.colorFondo = color.colorInt()
    }
}