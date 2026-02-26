package Modelo

class Condicion(val variable : String, val signo : String, val variableCierre: String)  {

    override fun toString() = "($variable $signo $variableCierre)"
}