package com.example.practica1_compi1

import Modelo.Instrucciones
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.ArrayList

class DiagramaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_diagrama, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recibimos UN SOLO objeto Instrucciones (no lista)
        val lista = arguments?.getSerializable("Lista instrucciones") as? ArrayList<Instrucciones>

        Log.d("DIAGRAMA_IN", "Lista recibida = $lista")

        Log.d("DIAGRAMA_IN", "Recibido en fragment → instruccion = ${lista?.javaClass?.simpleName ?: "null"}")
        if (lista != null) {
            Log.d("DIAGRAMA_IN", "Tipo: ${lista.toString()} | toString: $lista")
        } else {
            Log.d("DIAGRAMA_IN", "No se recibió ninguna instrucción")
        }

        val diagrama = view.findViewById<DiagramaView>(R.id.diagramaView)

        if (lista != null) {
            diagrama.instrucciones = lista
        } else {
            diagrama.instrucciones = arrayListOf()
        }

        diagrama.invalidate()
    }
}

