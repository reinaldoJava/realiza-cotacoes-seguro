package com.realizacontacoes.realizacontacoes.seguro.utils;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CoverageDTO;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.CustomerDTO;
import org.instancio.Instancio;
import org.instancio.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.instancio.Select.field;

public class CotacaoMock {
    public static CotacaoDTO createMockCotacao() {
        Model<CotacaoDTO> model = Instancio.of(CotacaoDTO.class)
                .supply(field("id"), () -> 1L)
                .supply(field("productId"), () -> "product-1")
                .supply(field("offerId"), () -> "offer-1")
                .supply(field("category"), () -> "category-1")
                .supply(field("totalMonthlyPremiumAmount"), () -> BigDecimal.valueOf(100.00))
                .supply(field("totalCoverageAmount"), () -> BigDecimal.valueOf(10000.00))
                .supply(field("coveragesDTO"), () -> List.of(
                        new CoverageDTO(1L, "type-1", BigDecimal.valueOf(5000.00), 1L)
                ))
                .supply(field("assistances"), () -> List.of("assistance-1"))
                .supply(field("customerDTO"), () -> new CustomerDTO(
                        Optional.of(1L), "document-1", "customer-1", "type-1", "gender-1",
                        LocalDate.of(1990, 1, 1), "email@example.com", 1234567890L
                ))
                .toModel();

        return Instancio.create(model);
    }
}

