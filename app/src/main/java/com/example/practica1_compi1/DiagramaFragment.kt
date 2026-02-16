package com.example.practica1_compi1

import Modelo.Instrucciones
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class DiagramaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val lista = arguments?.getSerializable("Lista instrucciones") as ArrayList<Instrucciones>

        val view = DiagramaView(requireContext())
        view.instrucciones = lista

        return view
    }

}