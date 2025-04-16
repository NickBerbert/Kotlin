package com.example.blocodenotas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class NotaViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = NotasDatabase.getDatabase(application).notaDao()
    private val repository = NotaRepository(dao)

    val todasNotas = repository.todasNotas

    fun inserir(nota: Nota) = viewModelScope.launch {
        repository.inserir(nota)
    }

    fun atualizar(nota: Nota) = viewModelScope.launch {
        repository.atualizar(nota)
    }

    fun deletar(nota: Nota) = viewModelScope.launch {
        repository.deletar(nota)
    }

    suspend fun buscarPorId(id: Int) = repository.buscarPorId(id)
}
