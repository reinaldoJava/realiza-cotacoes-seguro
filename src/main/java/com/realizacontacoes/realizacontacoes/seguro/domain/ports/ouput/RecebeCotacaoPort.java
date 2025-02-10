package com.realizacontacoes.realizacontacoes.seguro.domain.ports.ouput;

public interface RecebeCotacaoPort {
    void processarCotacao(String mensagem);
}
