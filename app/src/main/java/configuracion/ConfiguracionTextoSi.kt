package configuracion

import Modelo.Instrucciones
import Modelo.Si

class ConfiguracionTextoSi(private val color: ConfiguracionColor, indice: Int) : Configuracion(indice) {

    override fun aplicarA(elemento: Instrucciones) {
        if (elemento is Si) {
            elemento.colorTexto = color.colorInt()
        }
    }

    override fun aplicarConfiguracion(lista: List<Instrucciones>) {
        aplicarATodos(lista)
    }

    private fun aplicarATodos(lista: List<Instrucciones>) {
        lista.forEach { instruccion ->
            if (instruccion is Si) {
                instruccion.colorTexto = color.colorInt()
            }
        }
    }

}