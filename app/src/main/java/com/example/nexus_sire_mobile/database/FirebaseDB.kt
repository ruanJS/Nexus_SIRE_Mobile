package com.example.nexus_sire_mobile.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseDB {
    private var databaseInstance: FirebaseDatabase? = null

    // Inicializa a instância do Firebase Database
    fun getDatabase(): FirebaseDatabase {
        if (databaseInstance == null) {
            databaseInstance = FirebaseDatabase.getInstance()
            databaseInstance!!.setPersistenceEnabled(true) // Ativa persistência offline
        }
        return databaseInstance!!
    }

    // Retorna uma referência para um nó específico no banco de dados
    fun getDatabaseReference(path: String): DatabaseReference {
        return getDatabase().getReference(path)
    }
}
