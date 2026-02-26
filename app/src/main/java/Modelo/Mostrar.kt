package Modelo




class Mostrar(val texto : String, override val indice: Int) : Instrucciones(indice) {

    override fun toString() = "$texto"
}

/*
class Mostrar(private var texto : String) : Instrucciones() {

    fun getTexto(): String {
        return texto
    }
    override fun getTipo(): String {
        return "MOSTRAR"
    }

    override fun toString(): String {
        return "MOSTRAR \"$texto\""
    }
}

 */