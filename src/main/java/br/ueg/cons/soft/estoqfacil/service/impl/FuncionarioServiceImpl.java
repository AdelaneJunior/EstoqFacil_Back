package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.repository.FuncionarioRepository;
import br.ueg.cons.soft.estoqfacil.service.FuncionarioService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.exception.InvalidParameterException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import br.ueg.prog.webi.api.util.Validacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static br.ueg.cons.soft.estoqfacil.exception.SistemaMessageCode.*;

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
            throw new BusinessException(ERRO_EMAIL_INVALIDO);

        if(!validacoes.validarCPF(entidade.getPessoa().getCpf()))
            throw new BusinessException(ERRO_CPF_INVALIDO);

        if(!validacoes.validarTelefone(entidade.getPessoa().getTelefone()))
            throw new BusinessException(ERRO_TELEFONE_INVALIDO);
    }

    @Override
    protected void validarCamposObrigatorios(Funcionario entidade) {

    }

    @Override
    public Funcionario incluir(Funcionario modelo) {
        return super.incluir(modelo);
    }


}
