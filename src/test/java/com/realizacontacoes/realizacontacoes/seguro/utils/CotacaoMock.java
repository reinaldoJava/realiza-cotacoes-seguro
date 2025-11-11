package com.realizacontacoes.realizacontacoes.seguro.utils;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.Cotacao;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.Customer;
import org.instancio.Instancio;
import org.instancio.Select;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CotacaoMock {
    public static Cotacao createMockCotacao() {
        return Instancio.of(Cotacao.class)
                .set(Select.field("id"), 22345L)
                .set(Select.field("productId"), "product-1")
                .set(Select.field("insurancePolicyId"), "756969")
                .set(Select.field("offerId"), "offer-1")
                .set(Select.field("createdAt"), "2024-05-22T20:37:17.090098")
                .set(Select.field("updatedAt"), "2024-05-22T20:37:17.090098")
                .set(Select.field("category"), "category-1")
                .set(Select.field("totalMonthlyPremiumAmount"), BigDecimal.valueOf(100.00))
                .set(Select.field("totalCoverageAmount"), BigDecimal.valueOf(10000.00))
                .set(Select.field("coverages"), Map.of(
                        "type-1", 5000.0
                ))
                .set(Select.field("assistances"), List.of("assistance-1"))
                .set(Select.field("customer"), Instancio.of(Customer.class)
                        .set(Select.field("id"), Optional.of(1L))
                        .set(Select.field("documentNumber"), "document-1")
                        .set(Select.field("name"), "customer-1")
                        .set(Select.field("type"), "type-1")
                        .set(Select.field("gender"), "gender-1")
                        .set(Select.field("dateOfBirth"), LocalDate.of(1990, 1, 1))
                        .set(Select.field("email"), "email@example.com")
                        .set(Select.field("phoneNumber"), 1234567890L)
                        .create())
                .create();
    }
}

