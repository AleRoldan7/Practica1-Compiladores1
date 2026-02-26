package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si
import configuracion.Configuracion
import configuracion.LetraFuente

class ConfiguracionLetraSi(private val letra: LetraFuente, indice: Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Si) {
                inst.tipoLetra = letra
            }

            inst.obtenerSubInstrucciones()?.let { aplicarConfiguracion(it) }
        }
    }
}