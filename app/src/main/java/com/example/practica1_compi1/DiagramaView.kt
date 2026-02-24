package com.example.practica1_compi1

//import Modelo.CicloMientras
//import Modelo.CondicionSI
import Modelo.Declaracion
//import Modelo.Fin
//import Modelo.Inicio
import Modelo.Instrucciones
import Modelo.Leer
import Modelo.Mostrar
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View

class DiagramaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var instrucciones: List<Instrucciones> = emptyList()
        set(value) {
            field = value
            Log.d(TAG, "Nueva lista asignada = tamaño = ${value.size}")
            invalidate()
        }

    private val TAG = "DiagramaView_DEBUG"

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
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val paintFondoDebug = Paint().apply {
        color = Color.parseColor("#FFF8E1")
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        Log.d(TAG, "onDraw() llamado | width = $width  height = $height | instrucciones.size = ${instrucciones.size}")

        if (width <= 0 || height <= 0) {
            Log.w(TAG, "Vista sin dimensiones válidas aún → esperando layout")
            return
        }

        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paintFondoDebug)

        val centroX = width / 2f
        val margenIzq = 100f
        var y = 60f  // empezamos un poco más arriba

        Log.d(TAG, "Dibujando INICIO forzado")
        val altoInicio = dibujarOvalo(canvas, "INICIO", centroX, y)
        y += altoInicio + 60f
        dibujarFlechaVertical(canvas, centroX, y - 60f, centroX, y)

        if (instrucciones.isEmpty()) {
            Log.w(TAG, "Lista de instrucciones está VACÍA → solo se verá INICIO")
            canvas.drawText("Lista vacía", centroX, y + 100f, paintTexto)
        } else {
            Log.d(TAG, "Procesando ${instrucciones.size} instrucciones...")
            y = dibujarSecuencia(canvas, instrucciones, margenIzq, y, centroX)
        }

        Log.d(TAG, "Dibujando FIN forzado")
        dibujarOvalo(canvas, "FIN", centroX, y)
        // flecha hacia FIN
        dibujarFlechaVertical(canvas, centroX, y - 60f, centroX, y)
    }

    private fun dibujarSecuencia(
        canvas: Canvas,
        lista: List<Instrucciones>,
        x: Float,
        yInicial: Float,
        centroX: Float
    ): Float {

        var y = yInicial

        lista.forEachIndexed { index, inst ->
            val nombre = inst.javaClass.simpleName
            Log.d(TAG, "[$index] Procesando: $nombre → ${inst.toString()}")

            val altura = when (inst) {
                is Declaracion -> {
                    dibujarRectangulo(canvas, inst.toString(), x, y, centroX)
                }
                is Mostrar -> {
                    dibujarRectangulo(canvas, "MOSTRAR ${inst.toString()}", x, y, centroX)
                }
                is Leer -> {
                    dibujarParalelogramo(canvas, "LEER ${inst.toString()}", x, y, centroX)
                }

                else -> {
                    Log.w(TAG, "Tipo NO manejado: $nombre")
                    dibujarRectangulo(canvas, "??? $nombre ???", x, y, centroX)
                }
            }

            // Flecha descendente
            dibujarFlechaVertical(canvas, centroX, y + altura, centroX, y + altura + 50f)

            y += altura + 90f
        }

        return y
    }

    // ────────────────────────────────────────
    //  FIGURAS
    // ────────────────────────────────────────

    private fun dibujarOvalo(canvas: Canvas, texto: String, cx: Float, cy: Float): Float {
        val ancho = 420f
        val alto = 140f
        val rect = RectF(cx - ancho/2, cy, cx + ancho/2, cy + alto)
        canvas.drawOval(rect, paintBorde)
        canvas.drawText(texto, cx, cy + alto * 0.62f, paintTexto)
        Log.d(TAG, "Ovalo dibujado: $texto @ y=$cy")
        return alto
    }

    private fun dibujarRectangulo(canvas: Canvas, texto: String, x: Float, y: Float, centroX: Float): Float {
        val ancho = 400f
        val alto = 110f
        val left = centroX - ancho / 2
        canvas.drawRect(left, y, left + ancho, y + alto, paintBorde)
        canvas.drawText(texto, centroX, y + alto * 0.62f, paintTexto)
        Log.d(TAG, "Rectángulo: $texto @ y=$y")
        return alto
    }

    private fun dibujarParalelogramo(canvas: Canvas, texto: String, x: Float, y: Float, centroX: Float): Float {
        val ancho = 420f
        val alto = 130f
        val slant = 70f
        val left = centroX - ancho / 2

        val path = Path().apply {
            moveTo(left + slant, y)
            lineTo(left + ancho, y)
            lineTo(left + ancho - slant, y + alto)
            lineTo(left, y + alto)
            close()
        }
        canvas.drawPath(path, paintBorde)
        canvas.drawText(texto, centroX, y + alto * 0.62f, paintTexto)
        Log.d(TAG, "Paralelogramo: $texto @ y=$y")
        return alto
    }

    private fun dibujarRombo(
        canvas: Canvas,
        texto: String,
        cx: Float,
        cy: Float,
        alto: Float = 160f
    ): Float {
        val ancho = 380f
        val mitadAncho = ancho / 2f
        val mitadAlto = alto / 2f

        val path = Path().apply {
            moveTo(cx, cy)                           // arriba
            lineTo(cx + mitadAncho, cy + mitadAlto)
            lineTo(cx, cy + alto)                    // abajo
            lineTo(cx - mitadAncho, cy + mitadAlto)
            close()
        }
        canvas.drawPath(path, paintBorde)
        canvas.drawText(texto, cx, cy + mitadAlto + 15f, paintTexto)
        Log.d(TAG, "Rombo dibujado: $texto @ y=$cy")
        return alto
    }

    private fun dibujarFlechaVertical(canvas: Canvas, x1: Float, y1: Float, x2: Float, y2: Float) {
        canvas.drawLine(x1, y1, x2, y2, paintFlecha)
        val tam = 24f
        canvas.drawLine(x2, y2, x2 - tam, y2 - tam, paintFlecha)
        canvas.drawLine(x2, y2, x2 + tam, y2 - tam, paintFlecha)
    }

    companion object {
        private const val TAG = "DiagramaView_DEBUG"
    }
}