package com.example.practica1_compi1

import Modelo.Instrucciones
import Modelo.Leer
import Modelo.Mientras
import Modelo.Si
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import configuracion.TipoFigura
import kotlin.math.max

class DiagramaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var instrucciones: List<Instrucciones> = emptyList()
        set(value) {
            field = value
            Log.d(TAG, "Nueva lista asignada, tamaño = ${value.size}")
            invalidate()
        }

    private val TAG = "DiagramaView"

    // Paints
    private val paintBorde = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
        isAntiAlias = true
    }

    private val paintTexto = Paint().apply {
        color = Color.BLACK
        textSize = 38f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }

    private val paintFlecha = Paint().apply {
        color = Color.BLACK
        strokeWidth = 4f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private val paintFondo = Paint().apply {
        color = Color.parseColor("#F5F5F5")
        style = Paint.Style.FILL
    }

    private val paintTextoCondicion = Paint().apply {
        color = Color.BLUE
        textSize = 28f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width <= 0 || height <= 0) return

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintFondo)

        val centroX = width / 2f
        var y = 80f

        dibujarOvalo(canvas, "INICIO", centroX, y)
        y += 180f

        if (instrucciones.isEmpty()) {
            canvas.drawText("No hay instrucciones", centroX, y + 100f, paintTexto)
        } else {
            val resultado = dibujarSecuencia(canvas, instrucciones, y, centroX)
            y = resultado.y
        }

        dibujarOvalo(canvas, "FIN", centroX, y)
    }

    private data class ResultadoDibujo(val y: Float, val xFin: Float = 0f)

    private fun dibujarSecuencia(
        canvas: Canvas,
        lista: List<Instrucciones>,
        yInicial: Float,
        centroX: Float
    ): ResultadoDibujo {

        var yActual = yInicial
        val resultadoFinal = ResultadoDibujo(yActual)

        lista.forEach { inst ->
            when (inst) {
                is Si -> {
                    val altura = when (inst.figura) {
                        TipoFigura.ROMBO -> dibujarRombo(canvas, inst, inst.getTextoCondicion(), centroX, yActual)
                        else -> dibujarFiguraGenerica(canvas, inst, inst.getTextoCondicion(), yActual, centroX)
                    }

                    val yCentro = yActual + altura / 2

                    val xVerdadero = centroX + 340f
                    dibujarFlecha(canvas, centroX + 60f, yCentro, xVerdadero - 50f, yCentro)
                    dibujarTextoFlecha(canvas, "Sí", (centroX + xVerdadero)/2, yCentro - 25f, Color.GREEN)

                    val resVerdadero = dibujarBloque(canvas, inst.cuerpoCondicion, xVerdadero, yCentro - 40f)

                    val yFalso = yActual + altura + 140f
                    dibujarFlecha(canvas, centroX, yCentro + altura/2 + 20f, centroX, yFalso - 30f)
                    dibujarTextoFlecha(canvas, "No", centroX + 30f, (yCentro + yFalso)/2, Color.RED)

                    // Conectar rama verdadera de vuelta al flujo principal (antes del siguiente elemento)
                    if (resVerdadero.xFin > 0) {
                        paintFlecha.color = Color.GRAY
                        paintFlecha.strokeWidth = 3f
                        canvas.drawLine(
                            resVerdadero.xFin, resVerdadero.y,
                            centroX, resVerdadero.y + 60f,
                            paintFlecha
                        )
                    }

                    yActual = max(resVerdadero.y, yFalso) + 60f
                }

                is Mientras -> {
                    val yInicio = yActual

                    val altura = when (inst.figura) {
                        TipoFigura.ROMBO -> dibujarRombo(canvas, inst, inst.getTextoCondicion(), centroX, yActual)
                        else -> dibujarFiguraGenerica(canvas, inst, inst.getTextoCondicion(), yActual, centroX)
                    }

                    val yCentro = yActual + altura / 2

                    val xCuerpo = centroX + 340f
                    dibujarFlecha(canvas, centroX + 60f, yCentro, xCuerpo - 50f, yCentro)
                    dibujarTextoFlecha(canvas, "Sí", (centroX + xCuerpo)/2, yCentro - 25f, Color.GREEN)

                    val resCuerpo = dibujarBloque(canvas, inst.cuerpo, xCuerpo, yCentro - 40f)

                    paintFlecha.color = Color.parseColor("#FF5722")
                    paintFlecha.strokeWidth = 4.5f

                    val pathRetorno = Path().apply {
                        moveTo(resCuerpo.xFin, resCuerpo.y)
                        lineTo(resCuerpo.xFin + 140f, resCuerpo.y)
                        lineTo(resCuerpo.xFin + 140f, yInicio + 40f)
                        lineTo(centroX - 120f, yInicio + 40f)
                        lineTo(centroX - 80f, yInicio + 20f)
                    }
                    canvas.drawPath(pathRetorno, paintFlecha)

                    canvas.drawLine(centroX - 80f, yInicio + 20f, centroX - 120f, yInicio + 40f, paintFlecha)
                    canvas.drawLine(centroX - 80f, yInicio + 20f, centroX - 120f, yInicio, paintFlecha)

                    val ySalida = yActual + altura + 160f
                    dibujarFlecha(canvas, centroX, yCentro + altura/2 + 30f, centroX, ySalida - 30f)
                    dibujarTextoFlecha(canvas, "No", centroX + 30f, (yCentro + ySalida)/2, Color.RED)

                    yActual = ySalida + 80f
                }

                else -> {
                    val figura = if (inst is Leer) TipoFigura.PARALELOGRAMO else inst.figura
                    val altura = when (figura) {
                        TipoFigura.PARALELOGRAMO -> dibujarParalelogramo(canvas, inst, inst.toString(), yActual, centroX)
                        else -> dibujarFiguraGenerica(canvas, inst, inst.toString(), yActual, centroX)
                    }
                    yActual += altura + 70f
                }
            }
        }

        return ResultadoDibujo(yActual)
    }

    private fun dibujarBloque(
        canvas: Canvas,
        lista: List<Instrucciones>,
        x: Float,
        yInicial: Float
    ): ResultadoDibujo {
        var y = yInicial
        var xFin = x

        lista.forEach { inst ->
            val figuraFinal = if (inst is Leer) TipoFigura.PARALELOGRAMO else inst.figura
            val altura = when (figuraFinal) {
                TipoFigura.PARALELOGRAMO -> dibujarParalelogramo(canvas, inst, inst.toString(), y, x)
                else -> dibujarFiguraGenerica(canvas, inst, inst.toString(), y, x)
            }
            y += altura + 50f
        }

        return ResultadoDibujo(y = y, xFin = xFin)
    }


    private fun dibujarFiguraGenerica(
        canvas: Canvas, inst: Instrucciones, texto: String, y: Float, centroX: Float
    ): Float {
        return when (inst.figura) {
            TipoFigura.RECTANGULO -> dibujarRectangulo(canvas, inst, texto, y, centroX)
            TipoFigura.ROMBO -> dibujarRombo(canvas, inst, texto, centroX, y)
            TipoFigura.PARALELOGRAMO -> dibujarParalelogramo(canvas, inst, texto, y, centroX)
            TipoFigura.CIRCULO -> dibujarCirculo(canvas, inst, texto, centroX, y)
            TipoFigura.RECTANGULO_REDONDEADO -> dibujarRectanguloRedondeado(canvas, inst, texto, y, centroX)
            else -> dibujarRectangulo(canvas, inst, texto, y, centroX)
        }
    }


    private fun dibujarOvalo(canvas: Canvas, texto: String, cx: Float, cy: Float): Float {
        val ancho = 380f
        val alto = 120f
        val rect = RectF(cx - ancho/2, cy, cx + ancho/2, cy + alto)

        paintBorde.style = Paint.Style.FILL
        paintBorde.color = Color.parseColor("#E3F2FD")
        canvas.drawOval(rect, paintBorde)

        paintBorde.style = Paint.Style.STROKE
        paintBorde.color = Color.BLACK
        canvas.drawOval(rect, paintBorde)

        paintTexto.color = Color.BLACK
        canvas.drawText(texto, cx, cy + alto * 0.62f, paintTexto)

        return alto
    }

    private fun dibujarRectangulo(
        canvas: Canvas,
        inst: Instrucciones,
        texto: String,
        y: Float,
        centroX: Float
    ): Float {
        val ancho = 360f
        val alto = 100f
        val left = centroX - ancho / 2

        paintBorde.style = Paint.Style.FILL
        paintBorde.color = inst.colorFondo ?: Color.WHITE
        canvas.drawRect(left, y, left + ancho, y + alto, paintBorde)

        paintBorde.style = Paint.Style.STROKE
        paintBorde.color = Color.BLACK
        canvas.drawRect(left, y, left + ancho, y + alto, paintBorde)

        paintTexto.color = inst.colorTexto ?: Color.BLACK
        paintTexto.textSize = inst.tamLetra
        canvas.drawText(texto, centroX, y + alto * 0.62f, paintTexto)

        return alto
    }

    private fun dibujarParalelogramo(
        canvas: Canvas,
        inst: Instrucciones,
        texto: String,
        y: Float,
        centroX: Float
    ): Float {
        val ancho = 380f
        val alto = 120f
        val slant = 60f
        val left = centroX - ancho / 2

        val path = Path().apply {
            moveTo(left + slant, y)
            lineTo(left + ancho, y)
            lineTo(left + ancho - slant, y + alto)
            lineTo(left, y + alto)
            close()
        }

        paintBorde.style = Paint.Style.FILL
        paintBorde.color = inst.colorFondo ?: Color.WHITE
        canvas.drawPath(path, paintBorde)

        paintBorde.style = Paint.Style.STROKE
        paintBorde.color = Color.BLACK
        canvas.drawPath(path, paintBorde)

        paintTexto.color = inst.colorTexto ?: Color.BLACK
        paintTexto.textSize = inst.tamLetra
        canvas.drawText(texto, centroX, y + alto * 0.62f, paintTexto)

        return alto
    }

    private fun dibujarRombo(
        canvas: Canvas,
        inst: Instrucciones,
        texto: String,
        cx: Float,
        cy: Float
    ): Float {
        val ancho = 400f
        val alto = 160f
        val mitadAncho = ancho / 2f
        val mitadAlto = alto / 2f

        val path = Path().apply {
            moveTo(cx, cy)
            lineTo(cx + mitadAncho, cy + mitadAlto)
            lineTo(cx, cy + alto)
            lineTo(cx - mitadAncho, cy + mitadAlto)
            close()
        }

        paintBorde.style = Paint.Style.FILL
        paintBorde.color = inst.colorFondo ?: Color.parseColor("#FFF9C4")
        canvas.drawPath(path, paintBorde)

        paintBorde.style = Paint.Style.STROKE
        paintBorde.color = Color.BLACK
        canvas.drawPath(path, paintBorde)

        paintTexto.color = inst.colorTexto ?: Color.BLACK
        paintTexto.textSize = inst.tamLetra
        canvas.drawText(texto, cx, cy + mitadAlto + 15f, paintTexto)

        return alto
    }

    private fun dibujarCirculo(
        canvas: Canvas,
        inst: Instrucciones,
        texto: String,
        cx: Float,
        cy: Float
    ): Float {
        val radio = 70f

        paintBorde.style = Paint.Style.FILL
        paintBorde.color = inst.colorFondo ?: Color.WHITE
        canvas.drawCircle(cx, cy + radio, radio, paintBorde)

        paintBorde.style = Paint.Style.STROKE
        paintBorde.color = Color.BLACK
        canvas.drawCircle(cx, cy + radio, radio, paintBorde)

        paintTexto.color = inst.colorTexto ?: Color.BLACK
        paintTexto.textSize = inst.tamLetra
        canvas.drawText(texto, cx, cy + radio + 15f, paintTexto)

        return radio * 2
    }

    private fun dibujarRectanguloRedondeado(
        canvas: Canvas,
        inst: Instrucciones,
        texto: String,
        y: Float,
        centroX: Float
    ): Float {
        val ancho = 360f
        val alto = 100f
        val left = centroX - ancho / 2
        val rect = RectF(left, y, left + ancho, y + alto)

        paintBorde.style = Paint.Style.FILL
        paintBorde.color = inst.colorFondo ?: Color.WHITE
        canvas.drawRoundRect(rect, 30f, 30f, paintBorde)

        paintBorde.style = Paint.Style.STROKE
        paintBorde.color = Color.BLACK
        canvas.drawRoundRect(rect, 30f, 30f, paintBorde)

        paintTexto.color = inst.colorTexto ?: Color.BLACK
        paintTexto.textSize = inst.tamLetra
        canvas.drawText(texto, centroX, y + alto * 0.62f, paintTexto)

        return alto
    }

    private fun dibujarFlechaConTexto(
        canvas: Canvas,
        x1: Float, y1: Float,
        x2: Float, y2: Float,
        texto: String,
        tamFlecha: Float = 20f
    ) {
        canvas.drawLine(x1, y1, x2, y2, paintFlecha)

        val dx = (x2 - x1).toDouble()
        val dy = (y2 - y1).toDouble()
        val angulo = Math.atan2(dy, dx).toFloat()

        val cos = Math.cos(angulo.toDouble()).toFloat()
        val sin = Math.sin(angulo.toDouble()).toFloat()

        canvas.drawLine(
            x2, y2,
            x2 - tamFlecha * (cos * 0.8f - sin * 0.5f),
            y2 - tamFlecha * (sin * 0.8f + cos * 0.5f),
            paintFlecha
        )
        canvas.drawLine(
            x2, y2,
            x2 - tamFlecha * (cos * 0.8f + sin * 0.5f),
            y2 - tamFlecha * (sin * 0.8f - cos * 0.5f),
            paintFlecha
        )

        paintTextoCondicion.color = if (texto == "V") Color.GREEN else Color.RED
        paintTextoCondicion.textSize = 24f
        canvas.drawText(texto, (x1 + x2) / 2, (y1 + y2) / 2 - 10f, paintTextoCondicion)
    }

    private fun dibujarFlecha(
        canvas: Canvas,
        x1: Float, y1: Float,
        x2: Float, y2: Float,
        color: Int = Color.BLACK
    ) {
        paintFlecha.color = color
        canvas.drawLine(x1, y1, x2, y2, paintFlecha)

        // Punta de flecha
        val dx = x2 - x1
        val dy = y2 - y1
        val longitud = 20f
        val angulo = Math.atan2(dy.toDouble(), dx.toDouble()).toFloat()

        canvas.drawLine(
            x2, y2,
            x2 - longitud * Math.cos(angulo - Math.PI / 6).toFloat(),
            y2 - longitud * Math.sin(angulo - Math.PI / 6).toFloat(),
            paintFlecha
        )
        canvas.drawLine(
            x2, y2,
            x2 - longitud * Math.cos(angulo + Math.PI / 6).toFloat(),
            y2 - longitud * Math.sin(angulo + Math.PI / 6).toFloat(),
            paintFlecha
        )
    }

    private fun dibujarTextoFlecha(
        canvas: Canvas,
        texto: String,
        x: Float,
        y: Float,
        color: Int = Color.BLACK
    ) {
        paintTextoCondicion.color = color
        paintTextoCondicion.textSize = 28f
        canvas.drawText(texto, x, y, paintTextoCondicion)
    }

    companion object {
        private const val TAG = "DiagramaView"
    }
}