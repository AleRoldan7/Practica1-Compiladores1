package com.example.practica1_compi1

import Modelo.ResultadoAnalisis
import Modelo.TokenError
import analizador.analizador.Lexer
import analizador.analizador.Parser
import analizador.analizador.sym
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.practica1_compi1.databinding.FragmentFirstBinding
import java.io.StringReader

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
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
            Parser.listaErrores.clear()
            Parser.listaOperador.clear()
            Parser.listaEstructuras.clear()
            Parser.indiceContador = 1

            val entrada = binding.etEntrada.text.toString().trim()

            if (entrada.isEmpty()) {
                binding.tvResultado.text = "Escribe algo primero"
                return@setOnClickListener
            }

            try {
                val lexer = Lexer(StringReader(entrada))
                val parser = Parser(lexer)

                val resultadoSimbolo = parser.parse()
                val resultado = resultadoSimbolo.value as? ResultadoAnalisis

                val todosErrores = ArrayList<TokenError>()
                todosErrores.addAll(Parser.listaErrores)

                Log.d("ANALISIS", "Errores encontrados: ${todosErrores.size}")

                if (todosErrores.isNotEmpty()) {
                    val bundle = Bundle().apply {
                        putSerializable("listaErrores", todosErrores)
                    }
                    findNavController().navigate(R.id.action_firstFragment_to_reporteError, bundle)
                } else {
                    if (resultado != null) {
                        val instrucciones = resultado.instrucciones
                        val configuraciones = resultado.configuracion

                        val bundle = Bundle().apply {
                            putSerializable("instrucciones", ArrayList(instrucciones))
                            putSerializable("configuraciones", ArrayList(configuraciones))
                            putSerializable("operadores", ArrayList(Parser.listaOperador))
                            putSerializable("estructuras", ArrayList(Parser.listaEstructuras))
                        }
                        findNavController().navigate(R.id.action_firstFragment_to_diagramaFragment, bundle)
                    } else {
                        binding.tvResultado.text = "El parser no devolvió resultado válido"
                    }
                }

            } catch (e: Exception) {
                Log.e("ANALISIS", "Error crítico al parsear", e)
                binding.tvResultado.text = "Error: ${e.message ?: "Excepción desconocida"}"
            }
        }

        binding.btnLimpiar.setOnClickListener {
            binding.etEntrada.text?.clear()
            binding.tvResultado.text = ""
        }
    }

    private fun logTokens(entrada: String) {
        val lexerDebug = Lexer(StringReader(entrada))
        Log.d("LEXER_DEBUG", "========== TOKENS ==========")
        while (true) {
            val token = lexerDebug.next_token()
            if (token.sym == sym.EOF) break
            Log.d("LEXER_DEBUG", "Token: ${sym.terminalNames[token.sym]} | Lexema: ${token.value} | Línea: ${token.left + 1}, Col: ${token.right}")
        }
        Log.d("LEXER_DEBUG", "============================")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}