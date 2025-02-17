# 📌 Processo Completo de Cotação

Este documento descreve o fluxo completo do processo de cotação, desde o recebimento da requisição até a persistência dos dados e publicação no Kafka.

## 🚀 Fluxo da Cotação

### 1️⃣ Recebimento da Requisição (API REST)
- O cliente envia uma requisição para a API REST de cotação.
- O `CotacaoController` recebe os dados e os encaminha para o `ProcessaCotacaoUseCase`.

### 2️⃣ Busca de Informações do Produto
- O `ConsultaProdutoAdapter` faz uma chamada HTTP ao `ConsultaProdutoServicePort` para obter informações do produto.
- Os dados do produto são retornados.

### 3️⃣ Busca de Informações da Oferta
- O `ConsultaOfertaAdapter` faz uma chamada HTTP ao `ConsultaOfertaServicePort` para obter informações da oferta.
- Os dados da oferta são retornados.

### 4️⃣ Validações
O `OfertaValidator` realiza as seguintes validações:
- Verifica se as ofertas existem e estão ativas.
- Confirma se as coberturas informadas pertencem à oferta.
- Garante que o valor do prêmio mensal está dentro dos limites permitidos.
- Valida se o valor total das coberturas corresponde à soma das coberturas informadas.
- Valida as assistencias.
- Valida se o prêmio mensal
- Valida se o total das cobeturas correspondes ao valor informado na cobertura.

### 5️⃣ Persistência no Banco de Dados
- O `ProcessaCotacaoUseCase` chama o `CotacaoDataBaseUseCase`.
- O `CotacaoRepositoryAdapter` salva os dados da cotação no banco via `CotacaoJpaRepository`.

### 6️⃣ Produção de Mensagem no Kafka
- O `ProcessaCotacaoUseCase` chama o `CotacaoProducerUseCase`.
- O `CotacaoKafkaProducer` publica a mensagem no Kafka no tópico `cotacao-topic`.

### 7️⃣ Consumo da Mensagem do Kafka (com `policyId`)
- O `CotacaoKafkaConsumer`  consome a mensagem no Kafka no tópico `cotacao-topic`.

### 8️⃣ Atualização do Banco de Dados com `policyId`
- O `CotacaoKafkaConsumer` chama o `CotacaoConsumerUseCase`.
- O `CotacaoRepositoryAdapter` atualiza a cotação com o `policyId`, finalizando o processo com sucesso.

## 🔄 Resumo do Fluxo
1. O cliente faz a requisição.
2. Busca informações do produto.
3. Busca informações da oferta.
4. Realiza as validações.
5. Salva os dados no banco.
6. Envia a mensagem ao Kafka.
7. Consome a resposta do Kafka.
8. Atualiza o banco com o `policyId`.

Esse fluxo segue a **Arquitetura Hexagonal**, garantindo separação de responsabilidades e flexibilidade na comunicação entre os componentes.

---

## 🏗️ Infraestrutura

O ambiente pode ser configurado utilizando Docker para gerenciar os serviços externos necessários.

### 📌 Componentes Disponíveis
- **Docker Compose**: Arquivo `docker-compose.yml` com a infraestrutura completa.
- **Mock API**: Arquivo `mock_api.py` simulando os endpoints externos.
- **Banco de Dados**: Arquivo `init.sql` contendo a criação das tabelas (opcional pois, o application.properties usa o `spring.jpa.hibernate.ddl-auto=update` ).

### 🔧 Como Subir a Infraestrutura
Execute o seguinte comando para iniciar os serviços externos, incluindo Kafka e o banco de dados:
```bash
docker-compose up -d
```

---

## 📡 Testando a API

### 1️⃣ Criar uma Cotação
Envie uma requisição **POST** para criar uma cotação:
```bash
curl -g -X POST "http://localhost:9999/cotacao/solicitar" \
     -H "Content-Type: application/json" \
     -d '{
  "product_id": "1b2da7cc-b367-4196-8a78-9cfeec21f587",
  "offer_id": "adc56d77-348c-4bf0-908f-22d402ee715c",
  "category": "HOME",
  "total_monthly_premium_amount": 60.25,
  "total_coverage_amount": 1180000.00,
  "coverages": {
    "Incêndio": 500000.00,
    "Desastres naturais": 600000.00,
    "Responsabilidade civil": 80000.00
  },
  "assistances": [
    "Encanador",
    "Eletricista",
    "Chaveiro 24h"
  ],
  "customer": {
    "document_number": "362055789002",
    "name": "John Wick",
    "type": "NATURAL",
    "gender": "MALE",
    "date_of_birth": "1973-05-02",
    "email": "johnwick@gmail.com",
    "phone_number": 11950503030
  }
}'
```

### 2️⃣ Consultar uma Cotação
Para consultar uma cotação, utilize a requisição **GET**:
```bash
curl -g -X GET "http://localhost:9999/cotacao/1" \
     -H "Content-Type: application/json"
```