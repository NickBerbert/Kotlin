package com.example.blocodenotas
import android.widget.Button
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.util.Log



class MainActivity : AppCompatActivity() {
    private lateinit var db: SQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = SQLiteHelper(this)

        // Exemplo: inserir nota
        db.inserirNota(Nota(titulo = "Teste", conteudo = "Conteúdo de teste"))

        // Exemplo: listar notas
        val notas = db.listarNotas()
        for (nota in notas) {
            Log.d("BANCO", "Nota: ${nota.id} - ${nota.titulo}")
        }
    }
}


