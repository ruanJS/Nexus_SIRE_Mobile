package com.example.nexus_sire_mobile.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nexus_sire_mobile.MainActivity
import com.example.nexus_sire_mobile.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referências dos elementos
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val forgotPasswordTextView = findViewById<TextView>(R.id.forgotPasswordTextView)
        val registerTextView = findViewById<TextView>(R.id.registerTextView)

        // Configurar ação do botão de login
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            } else {
                // Aqui você pode validar o login (comparar com banco de dados ou API)
                if (email == "teste@email.com" && password == "123456") {
                    Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show()

                    // Navegar para a tela principal
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Email ou senha incorretos.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Configurar ação do link para recuperação de senha
        forgotPasswordTextView.setOnClickListener {
            Toast.makeText(this, "Função de recuperação de senha não implementada.", Toast.LENGTH_SHORT).show()
        }

        // Configurar ação do link para cadastro
        registerTextView.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
