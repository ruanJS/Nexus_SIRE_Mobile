package com.example.nexus_sire_mobile

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nexus_sire_mobile.util.MQTTManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa MQTT
        MQTTManager.initialize(this)

        // Conecta ao broker
        MQTTManager.connect(
            onSuccess = {
                Toast.makeText(this, "Conectado ao MQTT!", Toast.LENGTH_SHORT).show()

                // Inscreve-se no tópico de monitoramento de energia
                MQTTManager.subscribe("energia/monitoramento", qos = 1)
            },
            onFailure = { error ->
                Log.e("MQTT", "Erro ao conectar: ${error.message}")
                Toast.makeText(this, "Erro ao conectar ao MQTT!", Toast.LENGTH_SHORT).show()
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        MQTTManager.disconnect() // Desconecta ao destruir a atividade
    }
}
