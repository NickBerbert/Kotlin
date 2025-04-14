package com.example.blocodenotas
import android.widget.Button
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerNotas: RecyclerView
    private lateinit var adapter: NotaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerNotas = findViewById(R.id.recyclerNotas)
        adapter = NotaAdapter(NotaRepository.listaNotas) { nota ->
            val intent = Intent(this, DetalhesActivity::class.java)
            intent.putExtra("nota_id", nota.id)
            startActivity(intent)
        }

        recyclerNotas.layoutManager = LinearLayoutManager(this)
        recyclerNotas.adapter = adapter

        findViewById<Button>(R.id.btnNovaNota).setOnClickListener {
            startActivity(Intent(this, NovaNotaActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.atualizarLista(NotaRepository.listaNotas)
    }
}

