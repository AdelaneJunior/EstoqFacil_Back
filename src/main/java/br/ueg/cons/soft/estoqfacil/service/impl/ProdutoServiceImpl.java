package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.dto.EnviaEmailDTO;
import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import br.ueg.cons.soft.estoqfacil.enums.TipoMovimentacao;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.repository.ProdutoRepository;
import br.ueg.cons.soft.estoqfacil.repository.MovimentacaoRepository;
import br.ueg.cons.soft.estoqfacil.service.ProdutoService;
import br.ueg.cons.soft.estoqfacil.util.EmailSender;
import br.ueg.cons.soft.estoqfacil.util.JasperGeneretor;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl extends BaseCrudService<Produto, Long, ProdutoRepository>
        implements ProdutoService {

    @Autowired
    ProdutoRepository repository;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;


    @Autowired
    MovimentacaoServiceImpl movimentacaoService;

    @Autowired
    private ImagemServiceImpl imagemService;

    @Override
    protected void prepararParaIncluir(Produto entidade) {

    }

    @Override
    protected void validarDados(Produto entidade){
        Optional<Produto> produtoBD = repository.findProdutoByCodigoBarras(entidade.getCodigoBarras());
        if(produtoBD.isPresent() || entidade.getCodigoBarras() == null){
            throw new IllegalStateException("Codigo de barras ausente ou ja cadastrado no banco de dados");
        }
    }

    @Override
    protected void validarCamposObrigatorios(Produto entidade) {

    }

    public Boolean enviaEmailComPdf(EnviaEmailDTO enviaEmailDTO) {
        Thread threadEnvioEmail = new Thread(() -> {
            geraNovoPdf(enviaEmailDTO);
            enviaEmail(enviaEmailDTO.getEmail());
        });
        threadEnvioEmail.start();
        return true;
    }

    @Override
    public Produto obterPeloId(Long id) {
        Produto produto = super.obterPeloId(id);
        preencherCamposDaMovimentacao(produto);
        return produto;
    }

    private static void enviaEmail(String email) {
        try {
            EmailSender.enviaEmail(email);
        } catch (EmailException e) {
            throw new RuntimeException(e);
        }
    }

    private void geraNovoPdf(EnviaEmailDTO enviaEmailDTO) {

        for (ProdutoDTO produtoDTO : enviaEmailDTO.getListaProdutos()) {
            produtoDTO.setImagem(getImagemBase64(produtoDTO));
        }

        if (enviaEmailDTO.getPromocao()){
           enviaEmailDTO.setListaProdutos(tratarPrecoProdutoComDescontoList
                   (enviaEmailDTO.getListaProdutos(), enviaEmailDTO.getDesconto()));
        }

        JasperGeneretor.gerarPdfProduto(enviaEmailDTO.getListaProdutos(), enviaEmailDTO.getPromocao());
    }

    public String getImagemBase64(ProdutoDTO produtoDTO) {

        return new String(Base64.getEncoder().encode((byte[]) imagemService.obterPeloId(produtoDTO.getImagemId()).getBlob()));

    }



    public List<ProdutoDTO> tratarPrecoProdutoComDescontoList(List<ProdutoDTO> produtoDTOList, Long descontoPromocao ) {

        for (ProdutoDTO produtoDTO : produtoDTOList) {
            BigDecimal valorProduto = produtoDTO.getPreco();
            BigDecimal descontoPromoca = BigDecimal.valueOf(descontoPromocao);
            BigDecimal desconto = valorProduto.multiply(descontoPromoca.divide(BigDecimal.valueOf(100)));
            produtoDTO.setPreco(valorProduto.subtract(desconto).setScale(2, RoundingMode.HALF_EVEN));
        }
        return  produtoDTOList;
    }

    @Override
    public List<Produto> listarTodos(){
        List<Produto> produtos = super.listarTodos();
        produtos.forEach(produto -> {
            preencherCamposDaMovimentacao(produto);
        });
        return produtos;
    }

    private void preencherCamposDaMovimentacao(Produto produto){
        //Quantidade do produto
        Long total = ((movimentacaoRepository.totalProdutosEntrada(produto.getCodigo())))
        -
        (movimentacaoRepository.totalProdutosSaida(produto.getCodigo()));
        BigDecimal preco = movimentacaoRepository.findPrecoValueByLastDataAndProduto(produto.getCodigo());
        BigDecimal custo = movimentacaoRepository.findCustoValueByLastDataAndProduto(produto.getCodigo());
        if(total >= 0 && preco.compareTo(BigDecimal.ZERO) >= 0 && custo.compareTo(BigDecimal.ZERO) >=0){
            produto.setQuantidade(total);
            produto.setPreco(preco);
            produto.setCusto(custo);
        }
        else{
            throw new IllegalStateException("Valores negativos encontrados!!");
        }

    }

    public List<Produto> preencherCamposLista(List<Produto> produtos){
        produtos.forEach(produto -> {
            preencherCamposDaMovimentacao(produto);
        });
        return produtos;
    }

    @Override
    public Produto incluir(Produto modelo) {
        Produto novo = super.incluir(modelo);
        if(modelo.getQuantidade() != null && modelo.getPreco() != null && modelo.getCusto() != null){
            Movimentacao movimentacao = Movimentacao.builder()
                    .usuario(modelo.getUsuario())
                    .produto(novo)
                    .quantidade(modelo.getQuantidade())
                    .preco(modelo.getPreco())
                    .custo(modelo.getCusto())
                    .data(LocalDate.now())
                    .observacao("Primeira entrada")
                    .acao(AcaoMovimentacao.COMPRA)
                    .tipo(TipoMovimentacao.ENTRADA)
                    .build();
            movimentacaoService.incluir(movimentacao);
        }
        preencherCamposDaMovimentacao(novo);
        return novo;
    }
}
