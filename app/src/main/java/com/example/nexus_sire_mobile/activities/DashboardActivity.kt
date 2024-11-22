package com.example.nexus_sire_mobile.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.nexus_sire_mobile.R

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Encontrar o ListView no layout
        val listView: ListView = findViewById(R.id.dashboardListView)

        // Exemplo de lista de dados para exibição
        val dados = listOf("Item 1", "Item 2", "Item 3", "Item 4")

        // Criar um adaptador para o ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dados)

        // Configurar o ListView com o adaptador
        listView.adapter = adapter

        // Código adicional para configurar a lógica de interação com a lista
        listView.setOnItemClickListener { _, _, position, _ ->
            val itemSelecionado = dados[position]
            // Exemplo de ação ao clicar no item, pode exibir um Toast ou navegar para outra atividade
            // Toast.makeText(this, "Item selecionado: $itemSelecionado", Toast.LENGTH_SHORT).show()
        }
    }
}
