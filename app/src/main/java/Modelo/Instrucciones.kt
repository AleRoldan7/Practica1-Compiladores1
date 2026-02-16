package Modelo

import java.io.Serializable


sealed class Instrucciones : Serializable

data class Declaracion(val variable : String, val valor : String) : Instrucciones()
data class Mostrar(val texto : String) : Instrucciones()
data class Leer(val variable : String) : Instrucciones()
data class Si(val condicion : String) : Instrucciones()
data class Mientras(val condicion : String) : Instrucciones()