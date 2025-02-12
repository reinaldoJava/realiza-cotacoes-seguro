package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.persistence.entity;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "coverages")
public class CoveragesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "cotacao_id")
    private CotacaoEntity cotacao;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public CotacaoEntity getCotacao() {
        return cotacao;
    }

    public void setCotacao(CotacaoEntity cotacao) {
        this.cotacao = cotacao;
    }

}