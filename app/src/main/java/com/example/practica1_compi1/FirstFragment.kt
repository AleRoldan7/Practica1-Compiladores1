package com.example.practica1_compi1

import Modelo.Instrucciones
import Modelo.TokenError
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

            val entrada = binding.etEntrada.text.toString().trim()

            if (entrada.isEmpty()) {
                binding.tvResultado.text = "Escribe algo primero"
                return@setOnClickListener
            }

            try {
                val lexer = Lexer(StringReader(entrada))
                val parser = Parser(lexer)

                val parseResult = parser.parse()
                val errores = ArrayList<TokenError>()
                errores.addAll(Lexer.listaErrores)
                errores.addAll(Parser.listaErrores)
                Log.d("ERRORES", "Errores encontrados: ${errores.size}")
                errores.forEach {
                    Log.d("ERRORES", "${it.tipo} → ${it.lexema} (${it.linea}, ${it.columna})")
                }
                val resultado = parseResult.value

                Log.d("PARSER_RESULT", "Tipo devuelto: ${resultado?.javaClass?.name ?: "null"}")
                Log.d("PARSER_RESULT", "Valor crudo: $resultado")

                if (resultado is List<*>) {
                    val listaCruda = resultado as List<Instrucciones?>
                    val listaLimpia = listaCruda.filterNotNull()

                    Log.d("PARSER_RESULT", "Es lista! Tamaño = ${listaLimpia.size}")
                    listaLimpia.forEachIndexed { i, instr ->
                        Log.d("PARSER_RESULT", "  [$i] ${instr.javaClass.simpleName} → $instr")
                    }

                    if (listaLimpia.isEmpty()) {
                        binding.tvResultado.text = "Parser OK pero lista vacía (solo nulls o vacío). Revisa el input o gramática."
                    } else {
                        val bundle = Bundle().apply {
                            putSerializable("Lista instrucciones", ArrayList(listaLimpia))
                        }
                        findNavController().navigate(R.id.diagramaFragment, bundle)
                    }
                } else {
                    binding.tvResultado.text = "El parser no devolvió una lista. Tipo: ${resultado?.javaClass?.name}"
                }

            } catch (e: Exception) {
                Log.e("PARSER_ERROR", "Error al parsear", e)
                binding.tvResultado.text = "Error: ${e.message}"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}