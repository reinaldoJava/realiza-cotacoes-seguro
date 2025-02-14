-- Cria a tabela `customers`
CREATE TABLE customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document_number VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone_number BIGINT NOT NULL
);

-- Cria a tabela `cotacoes`
CREATE TABLE cotacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id VARCHAR(50) NOT NULL,
    offer_id VARCHAR(50) NOT NULL,
    category VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    total_monthly_premium_amount DECIMAL(10, 2) NOT NULL,
    total_coverage_amount DECIMAL(15, 2) NOT NULL,
    customer_id BIGINT,
    FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE
);

-- Cria a tabela `coverages`
CREATE TABLE coverages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    cotacao_id BIGINT,
    FOREIGN KEY (cotacao_id) REFERENCES cotacoes(id) ON DELETE CASCADE
);

-- Cria a tabela `assistances`
CREATE TABLE assistances (
    cotacao_id BIGINT,
    assistance_name VARCHAR(255),
    PRIMARY KEY (cotacao_id, assistance_name),
    FOREIGN KEY (cotacao_id) REFERENCES cotacoes(id) ON DELETE CASCADE
);
