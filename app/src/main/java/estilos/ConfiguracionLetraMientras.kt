package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import configuracion.Configuracion
import configuracion.LetraFuente

class ConfiguracionLetraMientras(private val letra: LetraFuente, indice: Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Mientras) {
                inst.tipoLetra = letra
            }

            inst.obtenerSubInstrucciones()?.let { aplicarConfiguracion(it) }
        }
    }
}