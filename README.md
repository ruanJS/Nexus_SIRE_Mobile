
# Nexus SIRE Mobile - MQTT Manager Application

## Descrição do Projeto
O **Nexus SIRE Mobile** é uma aplicação mobile desenvolvida para gerenciar a comunicação em tempo real entre dispositivos utilizando o protocolo MQTT (Message Queuing Telemetry Transport). Essa aplicação permite a publicação e assinatura de tópicos, além de monitorar mensagens em tempo real, conectando-se a um broker público.

### Motivação
O projeto foi criado para facilitar a comunicação eficiente e leve entre dispositivos IoT, permitindo o envio de mensagens rápidas e a criação de sistemas de monitoramento em tempo real.

---

## Funcionalidades
1. **Conexão com o Broker MQTT**  
   - Permite conectar-se a brokers públicos ou privados para comunicação com dispositivos IoT.

2. **Publicação de Mensagens**  
   - Envia mensagens para tópicos específicos no broker.

3. **Inscrição em Tópicos**  
   - Recebe mensagens em tempo real de tópicos assinados.

4. **Callback Personalizado**  
   - Registra logs para eventos como conexão perdida, mensagens recebidas e entrega de mensagens.

5. **Gerenciamento de Sessões**  
   - Configuração de sessões limpas para gerenciar conexões de forma eficiente.

6. **Desconexão Segura**  
   - Fecha a conexão de maneira apropriada para liberar recursos.

---

## Tecnologias Utilizadas
- **Linguagem**: Kotlin
- **Frameworks**: Android SDK
- **Biblioteca MQTT**: [Eclipse Paho MQTT](https://www.eclipse.org/paho/)
- **Broker MQTT**: HiveMQ Public Broker (`tcp://broker.hivemq.com:1883`)

---

## Como Usar

### Requisitos
- **Android Studio** instalado na versão mais recente.
- **Gradle** configurado no projeto.
- Acesso à Internet para conectar-se ao broker MQTT.

### Passo a Passo
1. **Clone o repositório do projeto**:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd NexusSIREMobile
   ```

2. **Configure o projeto no Android Studio**:
   - Abra o projeto no Android Studio.
   - Aguarde o download das dependências via Gradle.

3. **Compile e execute o aplicativo**:
   - Conecte um dispositivo físico ou utilize o emulador do Android Studio.
   - Clique em **Run** para compilar e instalar o aplicativo.

4. **Utilizando o aplicativo**:
   - Insira o endereço do broker MQTT e o ID do cliente.
   - Clique em **Conectar**.
   - Publique mensagens em tópicos ou inscreva-se para receber mensagens em tempo real.

---

## Estrutura do Código

- **`MQTTManager`**  
  Um objeto responsável por gerenciar todas as operações MQTT, como conexão, publicação, assinatura e callbacks.

### Principais métodos:
- `initialize(context: Context)`: Inicializa o cliente MQTT.
- `connect(onSuccess: () -> Unit, onFailure: (Throwable) -> Unit)`: Conecta-se ao broker.
- `publish(topic: String, message: String, qos: Int)`: Publica mensagens no broker.
- `subscribe(topic: String, qos: Int)`: Inscreve-se em tópicos para receber mensagens.
- `disconnect()`: Desconecta-se do broker MQTT.

---

## Equipe de Desenvolvimento

| Nome                          | RM       |
|-------------------------------|----------|
| Geovana Ribeiro Domingos Silva | RM99646  |
| Leonardo Camargo Lucena        | RM552537 |
| Nathan Nunes Calsonari         | RM552539 |
| Ana Beatriz Bento Silva        | RM552536 |
| Ruan Guedes de Campos          | RM551096 |

---

## Referências
- [Protocolo MQTT](https://mqtt.org/)
- [HiveMQ Public Broker](https://www.hivemq.com/public-mqtt-broker/)
- [Documentação Eclipse Paho](https://www.eclipse.org/paho/)

---

## Licença
Este projeto é de uso acadêmico e está sob os direitos da equipe **Nexus SIRE Mobile**. Todos os direitos reservados.
