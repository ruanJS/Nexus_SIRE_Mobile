package com.example.nexus_sire_mobile.util

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    // Autenticação
    fun signIn(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Erro desconhecido")
                }
            }
    }

    fun signUp(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Erro desconhecido")
                }
            }
    }

    fun signOut() {
        auth.signOut()
    }

    // Dados no Realtime Database
    fun saveDevice(deviceId: String, data: Map<String, Any>, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val ref = database.getReference("devices/$deviceId")
        ref.setValue(data)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "Erro ao salvar dados")
                }
            }
    }

    fun getDevices(onSuccess: (List<Map<String, Any>>) -> Unit, onError: (String) -> Unit) {
        val ref = database.getReference("devices")
        ref.get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result?.children?.map { it.value as Map<String, Any> } ?: emptyList()
                    onSuccess(result)
                } else {
                    onError(task.exception?.message ?: "Erro ao buscar dados")
                }
            }
    }
}