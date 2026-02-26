package reportes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica1_compi1.databinding.ItemOperadorBinding

class OperadorAdpter (private val operadores: List<ReporteOperador>) :
    RecyclerView.Adapter<OperadorAdpter.OperadorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperadorViewHolder {
        val binding = ItemOperadorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OperadorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OperadorViewHolder, position: Int) {
        holder.bind(operadores[position])
    }

    override fun getItemCount() = operadores.size

    class OperadorViewHolder(private val binding: ItemOperadorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(operador: ReporteOperador) {
            binding.tvOperador.text = operador.operador
            binding.tvLinea.text = operador.linea.toString()
            binding.tvColumna.text = operador.columna.toString()
            binding.tvOcurrencia.text = operador.ocurrencia

        }
    }
}