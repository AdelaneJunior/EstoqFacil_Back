package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import br.ueg.cons.soft.estoqfacil.enums.TipoMovimentacao;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.cons.soft.estoqfacil.repository.MovimentacaoRepository;
import br.ueg.cons.soft.estoqfacil.service.MovimentacaoService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoServiceImpl extends BaseCrudService<Movimentacao, Long, MovimentacaoRepository>
        implements MovimentacaoService {


    @Autowired
    MovimentacaoRepository repository;


    @Override
    protected void prepararParaIncluir(Movimentacao entidade) {
            if (entidade.getAcao().equals(AcaoMovimentacao.VENDA.getId()) ||
                    entidade.getAcao().equals(AcaoMovimentacao.DEVOLUCAO_AO_FORNECEDOR.getId()) ||
                    entidade.getAcao().equals((AcaoMovimentacao.PRODUTO_QUEBRADO.getId()))
                    ) {
                entidade.setTipo(TipoMovimentacao.SAIDA);
            } else if (entidade.getAcao().equals(AcaoMovimentacao.DEVOLUCAO_DO_CLIENTE.getId()) ||
                        entidade.getAcao().equals(AcaoMovimentacao.COMPRA.getId())) {
                entidade.setTipo(TipoMovimentacao.ENTRADA);
            }
    }

    @Override
    protected void validarDados(Movimentacao entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Movimentacao entidade) {

    }
}
