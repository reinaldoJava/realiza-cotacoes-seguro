**Fluxo Completo do Processo de Cotação**

### 1. **Recebe uma requisição (API REST)**

- O cliente envia uma requisição para a API REST de cotação.
- O controller (`CotacaoController`) recebe os dados e os encaminha para o caso de uso (`CotacaoService`).

### 2. **Busca no endpoint de produto**


- O `ProdutoServiceAdapter` faz a chamada HTTP para buscar informações do produto via `ProdutoServiceExternal`.
- Retorna os dados do produto.

### 3. **Busca no endpoint de ofertas**

- O `OfertaServiceAdapter` faz a chamada HTTP para buscar informações da oferta via `OfertaServiceExternal`.
- Retorna os dados da oferta.

### 4. **Faz todas as validações**

- O `CotacaoService` valida:
    - Se as ofertas existem e estão ativas.
    - Se as assistências estão dentro das assistências da oferta.
    - Se o valor do prêmio mensal está dentro do mínimo e máximo.
    - Se o valor total das coberturas corresponde à soma das coberturas informadas.

### 5. **Salva no banco de dados**

- O `CotacaoService` chama a porta de saída `CotacaoRepositoryPort`.
- O `CotacaoRepositoryAdapter` salva os dados da cotação no banco via `CotacaoJpaRepository`.

### 6. **Produz uma mensagem no Kafka**

- O `CotacaoService` chama a porta de saída `CotacaoKafkaProducerPort`.
- O `CotacaoKafkaProducer` publica a mensagem no Kafka.

### 7. **Consome do Kafka contendo o ****`policyId`**

- O `CotacaoKafkaConsumer` escuta as mensagens no tópico do Kafka.
- Recebe a resposta contendo o `policyId`.

### 8. **Atualiza o banco com a requisição contendo o ****`policyId`**

- O `CotacaoKafkaConsumer` chama a porta de saída `CotacaoRepositoryPort`.
- O `CotacaoRepositoryAdapter` atualiza a cotação com o `policyId`.
- Confirma que a cotação foi concluída com sucesso.

### **Resumo do Fluxo**

1. Cliente faz a requisição.
2. Busca informações do produto.
3. Busca informações da oferta.
4. Salva no banco.
5. Envia ao Kafka.
6. Consome a resposta do Kafka.
7. Atualiza o banco com `policyId`.

Esse fluxo segue o padrão da **Arquitetura Hexagonal**, garantindo separação de responsabilidades e flexibilidade na comunicação entre os componentes.

Criado o arquivo docker-compose.yml que contem toda infra estrutura.
Criado o arquivo mock_api.py for criado simulando os 2 endpoints externos.
Criado o arquivo init.sql com a criação das tabelas.
Rode o ```python docker-compose.yml up --build``` para subir os servicos externos, kafka e banco de dados.
```python
curl -g -X POST "http://localhost:9999/cotacao/solicitar" \
-H "Content-Type: application/json" \
-d '{
"product_id": "1b2da7cc-b367-4196-8a78-9cfeec21f587",
"offer_id": "adc56d77-348c-4bf0-908f-22d402ee715c",
"category": "HOME",
"total_monthly_premium_amount": 75.25,
"total_coverage_amount": 825000.00,
"coverages": {
"Incêndio": 250000.00,
"Desastres naturais": 500000.00,
"Responsabilidade civil": 75000.00
},
"assistances": [
"Encanador",
"Eletricista",
"Chaveiro 24h"
],
"customer": {
"document_number": "362055789002",
"name": "John Wick",
"customer_type": "NATURAL",
"gender": "MALE",
"date_of_birth": "1973-05-02",
"email": "johnwick@gmail.com",
"phone_number": 11950503030
}
}'
```