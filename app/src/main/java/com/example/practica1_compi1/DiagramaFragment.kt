package com.example.practica1_compi1

import Modelo.Instrucciones
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.practica1_compi1.databinding.FragmentDiagramaBinding
import estilos.ConfiguracionesUniversales
import reportes.ReporteEstructurasControl
import reportes.ReporteOperador
import java.util.ArrayList

class DiagramaFragment : Fragment() {

    private var _binding: FragmentDiagramaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiagramaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val instrucciones = arguments?.getSerializable("instrucciones") as? ArrayList<Instrucciones> ?: arrayListOf()
        val configuraciones = arguments?.getSerializable("configuraciones") as? ArrayList<ConfiguracionesUniversales> ?: arrayListOf()
        val operadores = arguments?.getSerializable("operadores") as? ArrayList<ReporteOperador> ?: arrayListOf()
        val estructuras = arguments?.getSerializable("estructuras") as? ArrayList<ReporteEstructurasControl> ?: arrayListOf()

        Log.d("DIAGRAMA", "Instrucciones: ${instrucciones.size}")
        Log.d("DIAGRAMA", "Configuraciones: ${configuraciones.size}")

        configuraciones.forEach { config ->
            config.aplicarConfiguracion(instrucciones)
        }

        binding.diagramaView.instrucciones = instrucciones
        binding.diagramaView.invalidate()

        binding.btnReporteOperadores.isEnabled = true
        binding.btnReporteEstructuras.isEnabled = true
        binding.btnReporteErrores.isEnabled = false  // o setVisibility(View.GONE)

        binding.btnReporteOperadores.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("operadores", operadores)
            }
            findNavController().navigate(R.id.action_diagrama_to_reporteOperador, bundle)
        }

        binding.btnReporteEstructuras.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("estructuras", estructuras)
            }
            findNavController().navigate(R.id.action_diagrama_to_reporteEstructuras, bundle)
        }

        binding.btnReporteErrores.setOnClickListener {

            Toast.makeText(requireContext(), "No se encontraron errores", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}