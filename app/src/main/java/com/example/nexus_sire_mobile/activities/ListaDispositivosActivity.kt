package com.example.nexus_sire_mobile.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.nexus_sire_mobile.R

class ListaDispositivosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_dispositivos)

        // Encontrar o ListView no layout
        val listView: ListView = findViewById(R.id.deviceListView)

        // Exemplo de lista de dispositivos para exibição
        val dispositivos = listOf("Dispositivo 1", "Dispositivo 2", "Dispositivo 3", "Dispositivo 4")

        // Criar um adaptador para o ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dispositivos)

        // Configurar o ListView com o adaptador
        listView.adapter = adapter

        // Código adicional para configurar a lógica de dispositivos (exemplo: clique nos itens)
        listView.setOnItemClickListener { _, _, position, _ ->
            // Aqui você pode adicionar o código para o que acontece ao clicar no item
            val dispositivoSelecionado = dispositivos[position]
            // Exemplo: mostrar uma Toast ou iniciar outra atividade com mais detalhes do dispositivo
            // Toast.makeText(this, "Dispositivo: $dispositivoSelecionado", Toast.LENGTH_SHORT).show()
        }
    }
}
