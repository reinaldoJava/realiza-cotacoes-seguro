package com.realizacontacoes.realizacontacoes.seguro.usecase.validacao;

import com.realizacontacoes.realizacontacoes.seguro.adapters.inbound.exception.ValidationException;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.InsuranceRequest;
import com.realizacontacoes.realizacontacoes.seguro.domain.model.ProductResponse;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidaCotacaoUseCase {

    public void ValidaCotacao(InsuranceRequest request, ProductResponse product) {
        validateOffers(request, product);
        validateAssistances(request, product);
        validatePremiumAmount(request, product);
        validateCoverageAmount(request);
    }

    // 1️⃣ Valida se todas as ofertas existem e estão ativas
    private void validateOffers(InsuranceRequest request, ProductResponse product) {
        Set<String> productOffers = product.offers().stream().collect(Collectors.toSet());

        if (!productOffers.contains(request.offerId())) {
            throw new ValidationException("A oferta informada não está associada ao produto.");
        }
    }

    // 2️⃣ Valida se todas as assistências informadas estão na oferta
    private void validateAssistances(InsuranceRequest request, ProductResponse product) {
        Offer offer = findOfferById(request.offerId(), product);
        Set<String> availableAssistances = offer.assistances();

        if (!availableAssistances.containsAll(request.assistances())) {
            throw new ValidationException("Uma ou mais assistências não estão disponíveis na oferta.");
        }
    }

    // 3️⃣ Valida se o valor total do prêmio mensal está dentro do intervalo da oferta
    private void validatePremiumAmount(InsuranceRequest request, ProductResponse product) {
        Offer offer = findOfferById(request.offerId(), product);

        if (request.totalMonthlyPremiumAmount() < offer.minPremium() || request.totalMonthlyPremiumAmount() > offer.maxPremium()) {
            throw new ValidationException("O valor do prêmio mensal está fora do intervalo permitido.");
        }
    }

    // 4️⃣ Valida se a soma das coberturas corresponde ao total informado
    private void validateCoverageAmount(InsuranceRequest request) {
        double sumOfCoverages = request.coverages().values().stream().mapToDouble(Double::doubleValue).sum();

        if (sumOfCoverages != request.totalCoverageAmount()) {
            throw new ValidationException("O valor total das coberturas não corresponde à soma das coberturas informadas.");
        }
    }

    // Método auxiliar para encontrar a oferta pelo ID
    private Offer findOfferById(String offerId, ProductResponse product) {
        return product.offers().stream()
                .filter(offer -> offer.id().equals(offerId))
                .findFirst()
                .orElseThrow(() -> new ValidationException("Oferta não encontrada no produto."));
    }
}