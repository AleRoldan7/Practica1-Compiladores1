package com.example.practica1_compi1

import Modelo.TokenError
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import reportes.ErrorAdapter

class ReporteError : Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_reporte_error, container, false)

        recycler = view.findViewById(R.id.recyclerErrores)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val lista = arguments?.getSerializable("listaErrores") as? ArrayList<TokenError>
            ?: arrayListOf()

        recycler.adapter = ErrorAdapter(lista)

        return view
    }
}