package configuracion

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si

class ConfiguracionLetraBloque(private val letra: LetraFuente, indice: Int) : Configuracion(indice) {
    override fun aplicarA(e: Instrucciones) {
        if (e !is Si && e !is Mientras) e.tipoLetra = letra
    }
}