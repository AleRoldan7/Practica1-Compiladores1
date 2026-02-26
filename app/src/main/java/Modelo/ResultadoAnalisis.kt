package Modelo

import configuracion.Configuracion
import estilos.ConfiguracionesUniversales
import java.io.Serializable

data class ResultadoAnalisis(val instrucciones: List<Instrucciones>,
                            val configuracion : List<ConfiguracionesUniversales>) : Serializable {
}