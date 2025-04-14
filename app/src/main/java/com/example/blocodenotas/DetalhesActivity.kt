package com.example.blocodenotas
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetalhesActivity : AppCompatActivity() {

    private lateinit var txtTitulo: TextView
    private lateinit var txtConteudo: TextView
    private lateinit var btnExcluir: Button
    private var notaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        txtTitulo = findViewById(R.id.txtTitulo)
        txtConteudo = findViewById(R.id.txtConteudo)
        btnExcluir = findViewById(R.id.btnExcluir)

        notaId = intent.getIntExtra("nota_id", -1)

        if (notaId != -1) {
            val nota = NotaRepository.listaNotas.find { it.id == notaId }
            if (nota != null) {
                txtTitulo.text = nota.titulo
                txtConteudo.text = nota.conteudo

                btnExcluir.setOnClickListener {
                    NotaRepository.listaNotas.remove(nota)
                    Toast.makeText(this, "Nota excluída", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "Erro ao carregar nota", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}

