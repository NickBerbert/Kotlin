package com.example.blocodenotas
import android.content.Context
import java.io.File
import java.io.FileOutputStream

class DatabaseHelper(private val context: Context) {

    private val dbName = "database.sqlite"
    private val dbPath = context.getDatabasePath(dbName).path

    fun copiarBancoSeNecessario() {
        val dbFile = File(dbPath)
        if (!dbFile.exists()) {
            dbFile.parentFile?.mkdirs()
            context.assets.open(dbName).use { input ->
                FileOutputStream(dbFile).use { output ->
                    input.copyTo(output)
                }
            }
        }
    }

    fun getCaminhoBanco(): String {
        return dbPath
    }
}
