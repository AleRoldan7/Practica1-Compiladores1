package configuracion

import android.graphics.Color

data class ConfiguracionColor(val r: Int, val g: Int, val b: Int) {

    fun colorInt(): Int = Color.rgb(r, g, b)

    companion object {
        fun parse(str: String): ConfiguracionColor {
            return if (str.startsWith("H", ignoreCase = true)) {
                fromhex(str)
            } else {
                val parts = str.split(",").map { it.trim().toInt() }
                ConfiguracionColor(parts[0], parts[1], parts[2])
            }
        }

        fun fromhex(hex: String): ConfiguracionColor {
            val limpiar = hex.removePrefix("H").removePrefix("h").padEnd(6, '0')
            val r = limpiar.substring(0, 2).toInt(16)
            val g = limpiar.substring(2, 4).toInt(16)
            val b = limpiar.substring(4, 6).toInt(16)
            return ConfiguracionColor(r, g, b)
        }
    }
}