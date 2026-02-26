package reportes

import java.io.Serializable

class ReporteOperador(val operador : String, val linea : Int, val columna : Int,
                      val ocurrencia : String) : Serializable {
}