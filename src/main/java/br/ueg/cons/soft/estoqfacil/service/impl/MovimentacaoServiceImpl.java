package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.dto.RelatorioMovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.cons.soft.estoqfacil.repository.MovimentacaoRepository;
import br.ueg.cons.soft.estoqfacil.service.MovimentacaoService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static br.ueg.cons.soft.estoqfacil.exception.SistemaMessageCode.*;

@Service
public class MovimentacaoServiceImpl extends BaseCrudService<Movimentacao, Long, MovimentacaoRepository>
        implements MovimentacaoService {


    @Autowired
    MovimentacaoRepository repository;


    @Override
    protected void prepararParaIncluir(Movimentacao entidade) {
        entidade.setTipo(entidade.getAcao().getTipoMovimentacao());
        entidade.setData(LocalDate.now());
    }

    @Override
    protected void validarDados(Movimentacao entidade) {
        if(entidade.getAcao() == AcaoMovimentacao.VENDA ||
                entidade.getAcao() == AcaoMovimentacao.DEVOLUCAO_AO_FORNECEDOR ||
                entidade.getAcao() == AcaoMovimentacao.PRODUTO_QUEBRADO) {

            Long total = (repository.totalProdutosEntrada(entidade.getProduto().getCodigo()))
                    -
                    (repository.totalProdutosSaida(entidade.getProduto().getCodigo()));
            Long validarQuantidade = total - entidade.getQuantidade();
            if(validarQuantidade < 0 ){
                throw new BusinessException(ERRO_SAIDA_NEGATIVA);
            }
        }

        if(entidade.getQuantidade() < 0 ||
                entidade.getCusto().compareTo(BigDecimal.ONE) < 1 ||
                entidade.getPreco().compareTo(BigDecimal.ONE) < 1){
            throw new BusinessException(ERRO_NUMERO_NEGATIVO);
        }
    }

    @Override
    protected void validarCamposObrigatorios(Movimentacao entidade) {

    }

    public List<RelatorioMovimentacaoDTO> getAllMovimentacoesProdutoEntradaSaida(){
        return repository.getAllMovimentacoesProdutoEntradaSaida();
    }

    public List<Movimentacao> getTodasMovimentacoesDeProdutoPorCodigo(Long produtoCodigo) {
        return repository.findAllByProdutoId(produtoCodigo);
    }

    public List<Movimentacao> getTodasMovimentacoesDeProdutoPorCodigoComPaginacao(Long codProduto, int offset, int pageSize) {

        return repository.getTodasMovimentacoesDeProdutoPorCodigoComPaginacao(codProduto, offset, pageSize);
    }
}
