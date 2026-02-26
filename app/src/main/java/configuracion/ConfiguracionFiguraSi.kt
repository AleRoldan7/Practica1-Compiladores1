import Modelo.Instrucciones
import Modelo.Si
import configuracion.Configuracion
import configuracion.TipoFigura

class ConfiguracionFiguraSi(private val figura: TipoFigura, indice: Int) : Configuracion(indice) {
    override fun aplicarA(e: Instrucciones) { if (e is Si) e.figura = figura }
}