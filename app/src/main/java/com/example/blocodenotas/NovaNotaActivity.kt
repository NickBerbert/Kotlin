package com.example.blocodenotas
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class NovaNotaActivity : AppCompatActivity() {

    private lateinit var edtTitulo: EditText
    private lateinit var edtConteudo: EditText
    private lateinit var btnSalvar: Button
    private val viewModel: NotaViewModel by viewModels() // Usando o ViewModel para manipular o banco de dados

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
                val novaNota = Nota(titulo = titulo, conteudo = conteudo)

                // Usando a coroutine para chamar a inserção no banco de dados
                lifecycleScope.launch {
                    viewModel.inserir(novaNota)
                    Toast.makeText(this@NovaNotaActivity, "Nota salva!", Toast.LENGTH_SHORT).show()
                    finish() // Finaliza a Activity após salvar
                }
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
