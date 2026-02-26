package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si
import configuracion.LetraFuente
import kotlin.collections.forEach

class ConfiguracionLetraBloque(val letra : LetraFuente, val indice : Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst !is Mientras && inst !is Si) {
                inst.tipoLetra = letra
            }

            inst.obtenerSubInstrucciones()?.let { aplicarConfiguracion(it) }
        }
    }
}