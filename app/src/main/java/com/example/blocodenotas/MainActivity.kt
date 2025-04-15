package com.example.blocodenotas
import android.widget.Button
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log



class MainActivity : AppCompatActivity() {

    private lateinit var recyclerNotas: RecyclerView
    private lateinit var adapter: NotaAdapter
    private lateinit var dao: NotasDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dao = NotasDAO(this)
        val notas = dao.listarNotas()

        for (nota in notas) {
            Log.d("NOTA", "Título: ${nota.titulo}, Conteúdo: ${nota.conteudo}")
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

