package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.repository.ProdutoRepository;
import br.ueg.cons.soft.estoqfacil.service.ProdutoService;
import br.ueg.cons.soft.estoqfacil.util.EmailSender;
import br.ueg.cons.soft.estoqfacil.util.PdfCreator;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl extends BaseCrudService<Produto, Long, ProdutoRepository>
        implements ProdutoService{


    @Autowired
    ProdutoRepository repository;

    @Autowired
    PdfCreator creator;


    @Override
    protected void prepararParaIncluir(Produto entidade) {

    }

    @Override
    protected void validarDados(Produto entidade) {

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
}
