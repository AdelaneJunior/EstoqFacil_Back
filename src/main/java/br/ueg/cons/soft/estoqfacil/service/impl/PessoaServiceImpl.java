package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Pessoa;
import br.ueg.cons.soft.estoqfacil.repository.PessoaRepository;
import br.ueg.cons.soft.estoqfacil.service.PessoaService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.exception.FieldResponse;
import br.ueg.prog.webi.api.exception.InvalidParameterException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import br.ueg.prog.webi.api.util.Validacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl extends BaseCrudService<Pessoa, String, PessoaRepository>
        implements PessoaService {


    @Autowired
    PessoaRepository repository;
    Validacoes validacoes = new Validacoes();


    @Override
    protected void prepararParaIncluir(Pessoa entidade) {

    }

    @Override
    protected void validarDados(Pessoa entidade) {
    }

    @Override
    protected void validarCamposObrigatorios(Pessoa entidade) {

    }
}
