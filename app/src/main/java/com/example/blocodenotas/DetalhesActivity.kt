package com.example.blocodenotas

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DetalhesActivity : AppCompatActivity() {

    private lateinit var txtTitulo: TextView
    private lateinit var txtConteudo: TextView
    private lateinit var btnExcluir: Button
    private val viewModel: NotaViewModel by viewModels() // Usando ViewModel para manipulação de notas

    private var notaId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)

        txtTitulo = findViewById(R.id.txtTitulo)
        txtConteudo = findViewById(R.id.txtConteudo)
        btnExcluir = findViewById(R.id.btnExcluir)

        notaId = intent.getIntExtra("nota_id", -1)

        if (notaId != -1) {
            // Usando lifecycleScope para lançar uma coroutine
            lifecycleScope.launch {
                val nota = viewModel.buscarPorId(notaId)
                if (nota != null) {
                    txtTitulo.text = nota.titulo
                    txtConteudo.text = nota.conteudo

                    btnExcluir.setOnClickListener {
                        viewModel.deletar(nota)
                        Toast.makeText(this@DetalhesActivity, "Nota excluída", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } else {
                    Toast.makeText(this@DetalhesActivity, "Erro ao carregar nota", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        } else {
            Toast.makeText(this, "Erro ao carregar nota", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
