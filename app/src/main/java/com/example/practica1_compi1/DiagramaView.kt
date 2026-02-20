package com.example.practica1_compi1

import Modelo.CicloMientras
import Modelo.CondicionSI
import Modelo.Declaracion
import Modelo.Fin
import Modelo.Inicio
import Modelo.Instrucciones
import Modelo.Leer
import Modelo.Mostrar
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View


class DiagramaView(context: Context) : View(context) {

    var instrucciones: List<Instrucciones> = emptyList()

    private val dibujarFigura = Paint().apply {

        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 6f
        isAntiAlias = true
    }

    private val escribirTexto = Paint().apply {
        color = Color.WHITE
        textSize = 45f
        isAntiAlias = true
    }

    /*
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var y = 200f

        for (inst in instrucciones) {
            dibujarRectangulo(canvas, inst.toString(), y)
            y += 250f
        }
    }
    */

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var y = 200f

        for (inst in instrucciones) {

            when (inst) {

                is Inicio -> dibujarInicio(canvas, y)

                is Fin -> dibujarFin(canvas, y)

                is Mostrar -> dibujarRectangulo(canvas, inst.toString(), y)

                is Leer -> dibujarParalelogramo(canvas, inst.toString(), y)

                is CondicionSI -> dibujarRombo(canvas, inst.toString(), y)

                is CicloMientras -> dibujarRombo(canvas, inst.toString(), y)

                is Declaracion -> dibujarRectangulo(canvas, inst.toString(), y)
            }

            y += 250f
        }
    }
    private fun dibujarInicio(canvas: Canvas, y: Float) {

        val left = 250f
        val right = width - 250f
        val top = y
        val bottom = y + 150f

        canvas.drawOval(left, top, right, bottom, dibujarFigura)
        canvas.drawText("INICIO", left + 100f, top + 90f, escribirTexto)
    }

    private fun dibujarFin(canvas: Canvas, y: Float) {

        val left = 250f
        val right = width - 250f
        val top = y
        val bottom = y + 150f

        canvas.drawOval(left, top, right, bottom, dibujarFigura)
        canvas.drawText("FIN", left + 130f, top + 90f, escribirTexto)
    }

    private fun dibujarRectangulo(canvas: Canvas, texto: String, y: Float) {
        val left = 200f
        val right = width - 200f
        val top = y
        val bottom = y + 150f

        canvas.drawRect(left, top, right, bottom, dibujarFigura)
        canvas.drawText(texto, left + 40f, top + 90f, escribirTexto)
    }

    private fun dibujarParalelogramo(canvas: Canvas, texto: String, y: Float) {

        val left = 200f
        val right = width - 200f
        val top = y
        val bottom = y + 150f
        val desplazamiento = 60f

        val path = android.graphics.Path()
        path.moveTo(left + desplazamiento, top)
        path.lineTo(right, top)
        path.lineTo(right - desplazamiento, bottom)
        path.lineTo(left, bottom)
        path.close()

        canvas.drawPath(path, dibujarFigura)
        canvas.drawText(texto, left + 80f, top + 90f, escribirTexto)
    }

    private fun dibujarRombo(canvas: Canvas, texto: String, y: Float) {

        val centerX = width / 2f
        val top = y
        val bottom = y + 180f
        val left = centerX - 200f
        val right = centerX + 200f
        val centerY = (top + bottom) / 2

        val path = android.graphics.Path()
        path.moveTo(centerX, top)
        path.lineTo(right, centerY)
        path.lineTo(centerX, bottom)
        path.lineTo(left, centerY)
        path.close()

        canvas.drawPath(path, dibujarFigura)
        canvas.drawText(texto, centerX - 150f, centerY + 20f, escribirTexto)
    }

}