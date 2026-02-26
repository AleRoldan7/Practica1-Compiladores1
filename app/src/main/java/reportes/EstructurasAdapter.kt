package reportes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practica1_compi1.databinding.ItemEstructuraBinding

class EstructurasAdapter(private val estructuras: List<ReporteEstructurasControl>) :
    RecyclerView.Adapter<EstructurasAdapter.EstructuraViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstructuraViewHolder {
        val binding = ItemEstructuraBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EstructuraViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EstructuraViewHolder, position: Int) {
        holder.bind(estructuras[position])
    }

    override fun getItemCount() = estructuras.size

    class EstructuraViewHolder(private val binding: ItemEstructuraBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(estructura: ReporteEstructurasControl) {
            binding.tvObjeto.text = estructura.objeto
            binding.tvLinea.text = estructura.linea.toString()
            binding.tvCondicion.text = estructura.condicion

            when (estructura.objeto) {
                "SI" -> {
                    binding.tvObjeto.setTextColor(android.graphics.Color.parseColor("#4CAF50"))
                }
                "MIENTRAS" -> {
                    binding.tvObjeto.setTextColor(android.graphics.Color.parseColor("#FFC107"))
                }
                else -> {
                    binding.tvObjeto.setTextColor(android.graphics.Color.WHITE)
                }
            }
        }
    }
}