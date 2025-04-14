package com.example.blocodenotas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotaAdapter(
    private var notas: List<Nota>,
    private val onClick: (Nota) -> Unit
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    inner class NotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo: TextView = itemView.findViewById(R.id.itemTitulo)
        val conteudo: TextView = itemView.findViewById(R.id.itemConteudo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = notas[position]
        holder.titulo.text = nota.titulo
        holder.conteudo.text = nota.conteudo
        holder.itemView.setOnClickListener { onClick(nota) }
    }

    override fun getItemCount() = notas.size

    fun atualizarLista(novaLista: List<Nota>) {
        notas = novaLista
        notifyDataSetChanged()
    }
}

