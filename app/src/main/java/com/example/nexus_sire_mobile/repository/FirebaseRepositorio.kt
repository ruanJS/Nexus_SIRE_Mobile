package com.example.nexus_sire_mobile.repository

import com.example.nexus_sire_mobile.database.FirebaseDB
import com.example.nexus_sire_mobile.models.Dispositivo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class FirebaseRepositorio {

    private val dispositivosRef: DatabaseReference = FirebaseDB.getDatabaseReference("dispositivos")

    // Adiciona um novo dispositivo ao banco de dados
    fun adicionarDispositivo(dispositivo: Dispositivo, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val id = dispositivosRef.push().key ?: return
        dispositivo.copy(id = id).apply {
            dispositivosRef.child(id).setValue(this)
                .addOnSuccessListener { onSuccess() }
                .addOnFailureListener { onFailure(it) }
        }
    }

    // Recupera todos os dispositivos
    fun listarDispositivos(onSuccess: (List<Dispositivo>) -> Unit, onFailure: (Exception) -> Unit) {
        dispositivosRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lista = mutableListOf<Dispositivo>()
                snapshot.children.forEach {
                    val dispositivo = it.getValue(Dispositivo::class.java)
                    dispositivo?.let { lista.add(it) }
                }
                onSuccess(lista)
            }

            override fun onCancelled(error: DatabaseError) {
                onFailure(Exception(error.message))
            }
        })
    }
}
