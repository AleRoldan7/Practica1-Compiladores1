package com.example.practica1_compi1

import Modelo.Instrucciones
import analizador.analizador.Lexer
import analizador.analizador.Parser
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.practica1_compi1.databinding.FragmentFirstBinding
import java.io.StringReader

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAnalizar.setOnClickListener {

            val entrada = binding.etEntrada.text.toString()

            try {
                val lexer = Lexer(StringReader(entrada))
                val parser = Parser(lexer)

                val resultado = parser.debug_parse().value as List<Instrucciones>

                val bundle = Bundle()
                bundle.putSerializable("Lista instrucciones", ArrayList(resultado))

                findNavController().navigate(
                    R.id.diagramaFragment,
                    bundle
                )

                Log.d("PARSER_OK", "Si jalo ")
            } catch (e: Exception) {
                binding.tvResultado.text = e.message
                e.printStackTrace()
                Log.d("PARSER_ERROR", "Error ")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}