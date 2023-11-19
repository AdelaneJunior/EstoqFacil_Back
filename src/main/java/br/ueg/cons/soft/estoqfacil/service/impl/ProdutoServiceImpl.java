package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import br.ueg.cons.soft.estoqfacil.enums.TipoMovimentacao;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.repository.MovimentacaoRepository;
import br.ueg.cons.soft.estoqfacil.repository.ProdutoRepository;
import br.ueg.cons.soft.estoqfacil.service.ProdutoService;
import br.ueg.cons.soft.estoqfacil.util.EmailSender;
import br.ueg.cons.soft.estoqfacil.util.PdfCreator;
import br.ueg.prog.webi.api.service.BaseCrudService;
import jakarta.validation.constraints.Null;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl extends BaseCrudService<Produto, Long, ProdutoRepository>
        implements ProdutoService{


    @Autowired
    ProdutoRepository repository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    PdfCreator creator;

    @Autowired
    MovimentacaoServiceImpl movimentacaoService;


    @Override
    protected void prepararParaIncluir(Produto entidade) {

    }

    @Override
    protected void validarDados(Produto entidade) {
        Optional<Produto> produtoBD = repository.findProdutoByCodigoBarras(entidade.getCodigoBarras());
        if(produtoBD.isPresent()){
            throw new IllegalStateException("Codigo de barras ja cadastrado no banco de dados");
        }
    }

    @Override
    protected void validarCamposObrigatorios(Produto entidade) {

    }

    public boolean enviaLista(String email, List<ProdutoDTO> listaProdutos){
        creator.criaPdf(listaProdutos);
        try {
            EmailSender.enviaEmail(email);
            return true;
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Produto> listarTodos(){
        List<Produto> produtos = super.listarTodos();
        produtos.forEach(produto -> {
            //Quantidade do produto
            long total = (movimentacaoRepository.totalProdutosSaida(produto.getCodigo()))
                        -
                        (movimentacaoRepository.totalProdutosEntrada(produto.getCodigo()));
            produto.setQuantidade(total);
            //Preco do produto
            double preco = movimentacaoRepository.findPrecoValueByLastDataAndProduto(produto.getCodigo());
            produto.setPreco(preco);
            //Custo
            double custo = movimentacaoRepository.findCustoValueByLastDataAndProduto(produto.getCodigo());
            produto.setCusto(custo);
        });
        return produtos;
    }

    @Override
    public Produto incluir(Produto modelo) {
        Produto novo = super.incluir(modelo);
        if(novo.getQuantidade() != null && novo.getPreco() != null && novo.getCusto() != null){
            Movimentacao movimentacao = Movimentacao.builder()
                    .usuario(novo.getUsuario())
                    .produto(novo)
                    .quantidade(novo.getQuantidade())
                    .preco(novo.getPreco())
                    .custo(novo.getCusto())
                    .data(LocalDate.now())
                    .observacao("Primeira entrada")
                    .acao(AcaoMovimentacao.COMPRA)
                    .tipo(TipoMovimentacao.ENTRADA)
                    .build();
            movimentacaoService.incluir(movimentacao);
        }

        return novo;
    }
}
