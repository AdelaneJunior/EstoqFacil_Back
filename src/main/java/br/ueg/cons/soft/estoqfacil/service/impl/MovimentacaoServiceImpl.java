package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.dto.RelatorioMovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.cons.soft.estoqfacil.repository.MovimentacaoRepository;
import br.ueg.cons.soft.estoqfacil.service.MovimentacaoService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoServiceImpl extends BaseCrudService<Movimentacao, Long, MovimentacaoRepository>
        implements MovimentacaoService {


    @Autowired
    MovimentacaoRepository repository;


    @Override
    protected void prepararParaIncluir(Movimentacao entidade) {
        entidade.setTipo(entidade.getAcao().getTipoMovimentacao());
    }

    @Override
    protected void validarDados(Movimentacao entidade) {

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
