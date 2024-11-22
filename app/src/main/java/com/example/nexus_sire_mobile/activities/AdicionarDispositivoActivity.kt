package com.example.nexus_sire_mobile.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.nexus_sire_mobile.R
import com.example.nexus_sire_mobile.util.MQTTManager

class AdicionarDispositivoActivity : AppCompatActivity() {

    private lateinit var editTextData: EditText
    private lateinit var buttonEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar_dispositivo)

        editTextData = findViewById(R.id.editTextData)
        buttonEnviar = findViewById(R.id.buttonEnviar)

        buttonEnviar.setOnClickListener {
            val data = editTextData.text.toString()
            if (data.isNotEmpty()) {
                // Publica os dados no tópico MQTT
                MQTTManager.publish("energia/ajustes", data, qos = 1)
            }
        }
    }
}