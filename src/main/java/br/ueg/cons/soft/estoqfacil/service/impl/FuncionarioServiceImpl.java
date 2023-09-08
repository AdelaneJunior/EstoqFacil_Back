package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.pks.PkFuncionario;
import br.ueg.cons.soft.estoqfacil.repository.FuncionarioRepository;
import br.ueg.cons.soft.estoqfacil.service.FuncionarioService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl extends BaseCrudService<Funcionario, PkFuncionario, FuncionarioRepository>
        implements FuncionarioService {

    @Autowired
    FuncionarioRepository repository;


    @Override
    protected void prepararParaIncluir(Funcionario entidade) {

    }

    @Override
    protected void validarDados(Funcionario entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Funcionario entidade) {

    }
}
