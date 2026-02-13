package com.example.practica1_compi1

import analizador.lexer.Lexer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        val etEntrada = view.findViewById<EditText>(R.id.etEntrada)
        val btnAnalizar = view.findViewById<Button>(R.id.btnAnalizar)
        val tvResultado = view.findViewById<TextView>(R.id.tvResultado)

        btnAnalizar.setOnClickListener {

            tvResultado.text = "Se presionooooo"

            try {
                val texto = etEntrada.text.toString()

                val lexer = Lexer(StringReader(texto))

                while (lexer.yylex() != Lexer.YYEOF) {

                }

                tvResultado.text = lexer.getResultado()


            } catch (e: Exception) {
                tvResultado.text = "Error: ${e.message}"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}