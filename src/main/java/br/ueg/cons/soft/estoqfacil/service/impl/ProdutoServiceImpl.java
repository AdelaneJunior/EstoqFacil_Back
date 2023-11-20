package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.dto.EnviaEmailDTO;
import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.repository.ProdutoRepository;
import br.ueg.cons.soft.estoqfacil.service.ProdutoService;
import br.ueg.cons.soft.estoqfacil.util.EmailSender;
import br.ueg.cons.soft.estoqfacil.util.JasperGeneretor;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Base64;
import java.util.List;

@Service
public class ProdutoServiceImpl extends BaseCrudService<Produto, Long, ProdutoRepository>
        implements ProdutoService {

    @Autowired
    private ImagemServiceImpl imagemService;

    @Override
    protected void prepararParaIncluir(Produto entidade) {

    }

    @Override
    protected void validarDados(Produto entidade) {

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
}
