package com.example.practica1_compi1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica1_compi1.databinding.FragmentReporteOperadorBinding
import reportes.OperadorAdpter
import reportes.ReporteOperador

class ReporteOperadorFragment : Fragment() {
    private var _binding: FragmentReporteOperadorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReporteOperadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val operadores = arguments?.getSerializable("operadores") as? List<ReporteOperador>
            ?: emptyList()

        setupRecyclerView(operadores)
    }

    private fun setupRecyclerView(operadores: List<ReporteOperador>) {
        binding.recyclerOperadores.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerOperadores.adapter = OperadorAdpter(operadores)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}