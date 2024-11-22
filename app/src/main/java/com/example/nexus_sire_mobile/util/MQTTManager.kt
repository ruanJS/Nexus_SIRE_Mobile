package com.example.nexus_sire_mobile.util

import android.content.Context
import android.util.Log
import org.eclipse.paho.client.mqttv3.*
import org.eclipse.paho.android.service.MqttAndroidClient

object MQTTManager {

    private const val TAG = "MQTTManager"
    private const val BROKER_URL = "tcp://broker.hivemq.com:1883" // Exemplo de broker público
    private const val CLIENT_ID = "NexusSireClient" // Substituir por um ID exclusivo
    private lateinit var mqttClient: MqttAndroidClient

    fun initialize(context: Context) {
        mqttClient = MqttAndroidClient(context.applicationContext, BROKER_URL, CLIENT_ID)
        mqttClient.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable?) {
                Log.e(TAG, "Conexão perdida: ${cause?.message}")
            }

            override fun messageArrived(topic: String?, message: MqttMessage?) {
                Log.d(TAG, "Mensagem recebida - Tópico: $topic, Mensagem: ${message.toString()}")
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {
                Log.d(TAG, "Entrega completa para token: $token")
            }
        })
    }

    fun connect(onSuccess: () -> Unit, onFailure: (Throwable) -> Unit) {
        val options = MqttConnectOptions().apply {
            isCleanSession = true
        }

        mqttClient.connect(options, null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.d(TAG, "Conectado com sucesso ao broker MQTT.")
                onSuccess()
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.e(TAG, "Falha ao conectar ao broker MQTT: ${exception?.message}")
                onFailure(exception ?: Exception("Erro desconhecido"))
            }
        })
    }

    fun publish(topic: String, message: String, qos: Int = 1) {
        try {
            val mqttMessage = MqttMessage().apply {
                payload = message.toByteArray()
                this.qos = qos
            }
            mqttClient.publish(topic, mqttMessage)
            Log.d(TAG, "Mensagem publicada no tópico $topic: $message")
        } catch (e: MqttException) {
            Log.e(TAG, "Erro ao publicar mensagem: ${e.message}")
        }
    }

    fun subscribe(topic: String, qos: Int = 1) {
        try {
            mqttClient.subscribe(topic, qos)
            Log.d(TAG, "Inscrito com sucesso no tópico $topic")
        } catch (e: MqttException) {
            Log.e(TAG, "Erro ao se inscrever no tópico: ${e.message}")
        }
    }

    fun disconnect() {
        try {
            mqttClient.disconnect()
            Log.d(TAG, "Desconectado do broker MQTT.")
        } catch (e: MqttException) {
            Log.e(TAG, "Erro ao desconectar do broker MQTT: ${e.message}")
        }
    }
}
