package estilos

import Modelo.Instrucciones
import Modelo.Mientras
import configuracion.Configuracion
import configuracion.TipoFigura

class ConfiguracionFiguraMientras(private val figura: TipoFigura, indice: Int) : ConfiguracionesUniversales() {

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {

        lista.forEach { inst ->

            if (inst is Mientras) {
                inst.figura = figura
            }

            inst.obtenerSubInstrucciones()?.let {
                aplicarConfiguracion(it)
            }
        }
    }
}