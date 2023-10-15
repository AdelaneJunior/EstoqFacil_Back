package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.repository.FuncionarioRepository;
import br.ueg.cons.soft.estoqfacil.service.FuncionarioService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class FuncionarioServiceImpl extends BaseCrudService<Funcionario, Long, FuncionarioRepository>
        implements FuncionarioService {

    @Autowired
    private PessoaServiceImpl pessoaService;

    @Override
    protected void prepararParaIncluir(Funcionario funcionario) {
    }

    @Override
    protected void validarDados(Funcionario entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Funcionario entidade) {

    }

    @Override
    public Funcionario incluir(Funcionario modelo) {
        if (Objects.isNull(modelo.getPessoa().getCodigo())) {
            modelo.setPessoa(pessoaService.incluir(modelo.getPessoa()));
        }
        return super.incluir(modelo);
    }
}
