CREATE TABLE customers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    document_number VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL,
    gender VARCHAR(10) NOT NULL,
    date_of_birth DATE NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone_number BIGINT NOT NULL
);

CREATE TABLE cotacoes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    product_id VARCHAR(100) NOT NULL,
    offer_id VARCHAR(100) NOT NULL,
    category VARCHAR(20) NOT NULL,
    total_monthly_premium_amount DECIMAL(10, 2) NOT NULL,
    total_coverage_amount DECIMAL(10, 2) NOT NULL,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers(id)
);

CREATE TABLE coverages (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    cotacao_id BIGINT NOT NULL,
    FOREIGN KEY (cotacao_id) REFERENCES cotacoes(id)
);