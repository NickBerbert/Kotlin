package com.example.blocodenotas
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, "NotasDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
            CREATE TABLE notas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                titulo TEXT,
                conteudo TEXT
            )
        """.trimIndent()
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS notas")
        onCreate(db)
    }

    fun inserirNota(nota: Nota): Long {
        val db = writableDatabase
        val valores = ContentValues().apply {
            put("titulo", nota.titulo)
            put("conteudo", nota.conteudo)
        }
        return db.insert("notas", null, valores)
    }

    fun listarNotas(): List<Nota> {
        val lista = mutableListOf<Nota>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM notas", null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val titulo = cursor.getString(cursor.getColumnIndexOrThrow("titulo"))
            val conteudo = cursor.getString(cursor.getColumnIndexOrThrow("conteudo"))
            lista.add(Nota(id, titulo, conteudo))
        }
        cursor.close()
        return lista
    }
}
