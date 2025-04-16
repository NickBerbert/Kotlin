package com.example.blocodenotas

class NotaRepository(private val dao: NotaDao) {
    val todasNotas = dao.listarNotas()

    suspend fun inserir(nota: Nota) = dao.inserir(nota)
    suspend fun atualizar(nota: Nota) = dao.atualizar(nota)
    suspend fun deletar(nota: Nota) = dao.deletar(nota)
    suspend fun buscarPorId(id: Int) = dao.buscarPorId(id)
}


