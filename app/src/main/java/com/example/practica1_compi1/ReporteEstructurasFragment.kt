package com.example.practica1_compi1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica1_compi1.databinding.FragmentReporteEstructurasBinding
import reportes.EstructurasAdapter
import reportes.ReporteEstructurasControl

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReporteEstructurasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReporteEstructurasFragment : Fragment() {
    private var _binding: FragmentReporteEstructurasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReporteEstructurasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recibir la lista de estructuras del Bundle
        val estructuras = arguments?.getSerializable("estructuras") as? ArrayList<ReporteEstructurasControl>
            ?: arrayListOf()

        setupRecyclerView(estructuras)

        // Mostrar mensaje si no hay estructuras
        if (estructuras.isEmpty()) {
            binding.tvMensajeVacio.visibility = View.VISIBLE
            binding.recyclerEstructuras.visibility = View.GONE
        } else {
            binding.tvMensajeVacio.visibility = View.GONE
            binding.recyclerEstructuras.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView(estructuras: List<ReporteEstructurasControl>) {
        binding.recyclerEstructuras.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerEstructuras.adapter = EstructurasAdapter(estructuras)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}