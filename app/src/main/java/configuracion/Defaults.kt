package configuracion

import Modelo.Instrucciones
import Modelo.Mientras
import Modelo.Si
import android.graphics.Color

class Defaults(indice: Int) : Configuracion(indice) {
    override fun aplicarA(elemento: Instrucciones) {
        elemento.colorFondo = Color.WHITE
        elemento.colorTexto = Color.BLACK
        elemento.tipoLetra = LetraFuente.ARIAL
        elemento.tamLetra = 36f

        when (elemento) {
            is Si, is Mientras -> elemento.figura = TipoFigura.ROMBO
            else -> elemento.figura = TipoFigura.RECTANGULO
        }
    }
}