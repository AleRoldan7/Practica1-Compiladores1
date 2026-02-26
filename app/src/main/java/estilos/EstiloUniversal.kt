package estilos

import android.graphics.Color
import configuracion.LetraFuente
import configuracion.TipoFigura

class EstiloUniversal(var colorTexto: Int = Color.BLACK,
                      var colorFondo: Int = Color.WHITE,
                      var figuraDefault: TipoFigura = TipoFigura.RECTANGULO,
                      var tipoLetra: LetraFuente = LetraFuente.ARIAL,
                      var tamLetra: Float = 36f
) {

}
