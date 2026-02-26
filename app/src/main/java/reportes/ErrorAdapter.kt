package reportes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import Modelo.TokenError
import com.example.practica1_compi1.R

class ErrorAdapter(private val lista: List<TokenError>) :
    RecyclerView.Adapter<ErrorAdapter.ErrorViewHolder>() {

    class ErrorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNumero: TextView = itemView.findViewById(R.id.tvNumero)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val tvLinea: TextView = itemView.findViewById(R.id.tvLinea)
        val tvColumna: TextView = itemView.findViewById(R.id.tvColumna)
        val tvTipo: TextView = itemView.findViewById(R.id.tvTipo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErrorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_error, parent, false)
        return ErrorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ErrorViewHolder, position: Int) {
        val error = lista[position]
        holder.tvNumero.text = (position + 1).toString()
        holder.tvDescripcion.text = error.descripcion
        holder.tvLinea.text = error.linea.toString()
        holder.tvColumna.text = error.columna.toString()
        holder.tvTipo.text = error.tipo
    }

    override fun getItemCount(): Int = lista.size
}