package com.example.blocodenotas
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class NotasDAO(context: Context) {

    private val db: SQLiteDatabase

    init {
        val dbHelper = DatabaseHelper(context)
        dbHelper.copiarBancoSeNecessario()
        db = SQLiteDatabase.openDatabase(
            dbHelper.getCaminhoBanco(),
            null,
            SQLiteDatabase.OPEN_READWRITE
        )
    }

    fun listarNotas(): List<Nota> {
        val notas = mutableListOf<Nota>()
        val cursor = db.rawQuery("SELECT * FROM notas", null)
        while (cursor.moveToNext()) {
            val nota = Nota(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo")),
                conteudo = cursor.getString(cursor.getColumnIndexOrThrow("conteudo"))
            )
            notas.add(nota)
        }
        cursor.close()
        return notas
    }

    fun adicionarNota(titulo: String, conteudo: String) {
        val valores = ContentValues().apply {
            put("titulo", titulo)
            put("conteudo", conteudo)
        }
        db.insert("notas", null, valores)
    }

    fun deletarNota(id: Int) {
        db.delete("notas", "id = ?", arrayOf(id.toString()))
    }
}
