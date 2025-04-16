package com.example.blocodenotas

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotaDao {

    @Insert
    suspend fun inserir(nota: Nota)

    @Update
    suspend fun atualizar(nota: Nota)

    @Delete
    suspend fun deletar(nota: Nota)

    @Query("SELECT * FROM notas ORDER BY id DESC")
    fun listarNotas(): LiveData<List<Nota>>

    @Query("SELECT * FROM notas WHERE id = :id")
    suspend fun buscarPorId(id: Int): Nota
}
