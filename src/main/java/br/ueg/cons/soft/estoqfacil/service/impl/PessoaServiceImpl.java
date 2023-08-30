package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Pessoa;
import br.ueg.cons.soft.estoqfacil.repository.PessoaRepository;
import br.ueg.cons.soft.estoqfacil.service.PessoaService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl extends BaseCrudService<Pessoa, Long, PessoaRepository>
        implements PessoaService {


    @Autowired
    PessoaRepository repository;


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
