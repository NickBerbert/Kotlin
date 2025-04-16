package com.example.blocodenotas

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotaAdapter(
    private var listaNotas: List<Nota>
) : RecyclerView.Adapter<NotaAdapter.NotaViewHolder>() {

    inner class NotaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.itemTitulo)
        val conteudo: TextView = view.findViewById(R.id.itemConteudo)

        init {
            // Adiciona um clique na nota
            itemView.setOnClickListener {
                val nota = listaNotas[adapterPosition]  // Pega a nota clicada
                val intent = Intent(itemView.context, DetalhesActivity::class.java).apply {
                    putExtra("nota_id", nota.id)  // Passa o ID da nota
                }
                itemView.context.startActivity(intent)  // Inicia a DetalhesActivity
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nota, parent, false)
        return NotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotaViewHolder, position: Int) {
        val nota = listaNotas[position]
        holder.titulo.text = nota.titulo
        holder.conteudo.text = nota.conteudo
    }

    override fun getItemCount(): Int = listaNotas.size

    fun atualizarLista(novasNotas: List<Nota>) {
        listaNotas = novasNotas
        notifyDataSetChanged()
    }
}
