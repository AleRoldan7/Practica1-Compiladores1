package Modelo

import configuracion.LetraFuente
import configuracion.TipoFigura
import java.io.Serializable


abstract class Instrucciones(open val indice : Int, open var colorTexto: Int? = null,
                             open var colorFondo: Int? = null, open var figura: TipoFigura = TipoFigura.RECTANGULO,
                             open var tipoLetra: LetraFuente = LetraFuente.ARIAL, open var tamLetra: Float = 36f) : Serializable {


    open fun obtenerSubInstrucciones(): List<Instrucciones>? = null
}



