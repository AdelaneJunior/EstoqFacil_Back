package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.repository.FuncionarioRepository;
import br.ueg.cons.soft.estoqfacil.service.FuncionarioService;
import br.ueg.prog.webi.api.exception.InvalidParameterException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import br.ueg.prog.webi.api.util.Validacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FuncionarioServiceImpl extends BaseCrudService<Funcionario, String, FuncionarioRepository>
        implements FuncionarioService {

    @Autowired
    private PessoaServiceImpl pessoaService;
    Validacoes validacoes = new Validacoes();

    @Override
    protected void prepararParaIncluir(Funcionario funcionario) {

    }

    @Override
    protected void validarDados(Funcionario entidade) {
        if(!validacoes.isEmailValido(entidade.getPessoa().getEmail()))
            throw new InvalidParameterException(entidade.getPessoa().getEmail(), "E-mail inválido");

        if(!validacoes.validarCPF(entidade.getPessoa().getCpf()))
            throw new InvalidParameterException(entidade.getPessoa().getCpf(), "CPF inválido");

        if(!validacoes.validarTelefone(entidade.getPessoa().getTelefone()))
            throw new InvalidParameterException(entidade.getPessoa().getTelefone(), "Telefone inválido");
    }

    @Override
    protected void validarCamposObrigatorios(Funcionario entidade) {

    }

    @Override
    public Funcionario incluir(Funcionario modelo) {
        return super.incluir(modelo);
    }

}
