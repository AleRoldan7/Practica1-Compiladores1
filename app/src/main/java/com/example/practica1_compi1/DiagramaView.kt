package com.example.practica1_compi1

import Modelo.Instrucciones
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
        color = Color.BLACK
        textSize = 45f
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var y = 200f

        for (inst in instrucciones) {
            dibujarRectangulo(canvas, inst.toString(), y)
            y += 250f
        }
    }

    private fun dibujarRectangulo(canvas: Canvas, texto: String, y: Float) {
        val left = 200f
        val right = width - 200f
        val top = y
        val bottom = y + 150f

        canvas.drawRect(left, top, right, bottom, dibujarFigura)
        canvas.drawText(texto, left + 40f, top + 90f, escribirTexto)
    }

}