package com.example.blocodenotas
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NovaNotaActivity : AppCompatActivity() {

    private lateinit var edtTitulo: EditText
    private lateinit var edtConteudo: EditText
    private lateinit var btnSalvar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_nota)

        edtTitulo = findViewById(R.id.edtTitulo)
        edtConteudo = findViewById(R.id.edtConteudo)
        btnSalvar = findViewById(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val titulo = edtTitulo.text.toString().trim()
            val conteudo = edtConteudo.text.toString().trim()

            if (titulo.isNotEmpty() && conteudo.isNotEmpty()) {
                val novaNota = Nota(
                    id = NotaRepository.listaNotas.size + 1,
                    titulo = titulo,
                    conteudo = conteudo
                )
                NotaRepository.listaNotas.add(novaNota)
                Toast.makeText(this, "Nota salva!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

