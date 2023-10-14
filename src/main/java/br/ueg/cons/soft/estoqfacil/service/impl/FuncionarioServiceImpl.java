package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.repository.FuncionarioRepository;
import br.ueg.cons.soft.estoqfacil.service.FuncionarioService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioServiceImpl extends BaseCrudService<Funcionario, Long, FuncionarioRepository>
        implements FuncionarioService {

    @Override
    protected void prepararParaIncluir(Funcionario funcionario) {
    }
    @Override
    protected void validarDados(Funcionario entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Funcionario entidade) {

    }
}
