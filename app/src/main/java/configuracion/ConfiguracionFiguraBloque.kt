package configuracion

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si

class ConfiguracionFiguraBloque(private val figura: TipoFigura, indice: Int) : Configuracion(indice) {
    override fun aplicarA(e: Instrucciones) {
        if (e !is Si && e !is Mientras) e.figura = figura
    }
}