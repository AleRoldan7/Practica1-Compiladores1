package reportes

import Modelo.TokenError
import analizador.analizador.Parser

object GestorReportes {

    fun tieneErrores() : Boolean = Parser.listaErrores.isNotEmpty()

    fun getReporteErrores() : List<TokenError> = Parser.listaErrores

    fun getReporteOperadores() : List<ReporteOperador> {
        return if (tieneErrores()) emptyList() else Parser.listaOperador
    }

    fun getReporteEstructuras() : List<ReporteEstructurasControl> {
        return if (tieneErrores()) emptyList() else Parser.listaEstructuras
    }

    fun limpiar() {
        Parser.listaErrores.clear()
        Parser.listaOperador.clear()
        Parser.listaEstructuras.clear()
        Parser.indiceContador = 1
    }
}