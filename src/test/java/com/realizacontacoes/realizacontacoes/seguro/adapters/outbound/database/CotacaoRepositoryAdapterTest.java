package com.realizacontacoes.realizacontacoes.seguro.adapters.outbound.database;

import com.realizacontacoes.realizacontacoes.seguro.domain.model.CotacaoDTO;
import com.realizacontacoes.realizacontacoes.seguro.utils.CotacaoMock;
import com.realizacontacoes.realizacontacoes.seguro.utils.mapper.CotacaoMapperManual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CotacaoRepositoryAdapterTest {

    @Autowired
    private CotacaoJpaRepository cotacaoJpaRepository;

    private CotacaoMapperManual cotacaoMapperManual = new CotacaoMapperManual();

    private CotacaoRepositoryAdapter cotacaoRepositoryAdapter;
    private CotacaoDTO cotacaoDTO = CotacaoMock.createMockCotacao();

    @BeforeEach
    public void init() {
        cotacaoRepositoryAdapter = new CotacaoRepositoryAdapter(cotacaoJpaRepository);
    }

    @Test
    @DisplayName("Deve salvar uma cotação no banco de dados")
    public void deveSalvarCotacao() {

        CotacaoDTO cotacaoSalva = cotacaoRepositoryAdapter.salvarCotacao(cotacaoDTO);

        assertNotNull(cotacaoSalva);
        assertEquals(cotacaoDTO.id(), cotacaoSalva.id());
    }

    @Test
    @DisplayName("Deve atualizar uma cotação no banco de dados")
    //TODO Fazer o ajustes.
    public void deveAtualizarCotacao() {

        /*CotacaoDTO cotacaoSalva = cotacaoRepositoryAdapter.salvarCotacao(cotacaoDTO);
        CotacaoEntity cotacaoEntity = cotacaoMapper.DTOToEntity(cotacaoSalva);
        cotacaoEntity.setProductId("novo_id");
        CotacaoDTO cotacaoSalvaNova = cotacaoMapper.toDomain(cotacaoEntity);
        cotacaoRepositoryAdapter.atualizaCotacao(cotacaoSalvaNova);

        CotacaoDTO cotacaoAtualizada = cotacaoJpaRepository.findById(cotacaoSalvaNova.id()).map(cotacaoMapper::toDomain).orElse(null);

        assertNotNull(cotacaoAtualizada);
        assertEquals(cotacaoSalvaNova.productId(), cotacaoAtualizada.productId());*/
    }


}