package com.example.blocodenotas
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas")
data class Nota(
    val id: Int,
    val titulo: String,
    val conteudo: String
)

