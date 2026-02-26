package configuracion

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si
import java.io.Serializable

abstract class Configuracion(val indice: Int) : Serializable {

    abstract fun aplicarA(elemento: Instrucciones)

    open fun aplicarConfiguracion(lista: List<Instrucciones>) {
        val elemento = buscarPorIndice(lista, indice) ?: return
        aplicarA(elemento)
    }

    private fun buscarPorIndice(lista: List<Instrucciones>, target: Int): Instrucciones? {
        for (inst in lista) {
            if (inst.indice == target) return inst

            if (inst is Si) {
                buscarPorIndice(inst.cuerpoCondicion, target)?.let { return it }
            }
            if (inst is Mientras) {
                buscarPorIndice(inst.cuerpo, target)?.let { return it }
            }
        }
        return null
    }
}