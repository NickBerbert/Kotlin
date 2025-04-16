package com.example.blocodenotas

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerNotas: RecyclerView
    private lateinit var adapter: NotaAdapter
    private val viewModel: NotaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerNotas = findViewById(R.id.recyclerNotas)
        adapter = NotaAdapter(emptyList())

        recyclerNotas.layoutManager = LinearLayoutManager(this)
        recyclerNotas.adapter = adapter

        // Observando as notas
        viewModel.todasNotas.observe(this) { lista ->
            adapter.atualizarLista(lista)
            // Log com o número de notas
            Log.d("NOTAS", "Temos ${lista.size} notas no banco")
        }

        // Ao clicar no botão "Nova Nota", abre a NovaNotaActivity
        findViewById<Button>(R.id.btnNovaNota).setOnClickListener {
            val intent = Intent(this, NovaNotaActivity::class.java)  // Cria o Intent para a NovaNotaActivity
            startActivity(intent)  // Inicia a NovaNotaActivity
        }
    }
}
