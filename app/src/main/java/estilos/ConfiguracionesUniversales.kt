package estilos

import Modelo.Instrucciones

abstract class ConfiguracionesUniversales {

    abstract fun aplicarConfiguracion(lista: List<Instrucciones>)
}