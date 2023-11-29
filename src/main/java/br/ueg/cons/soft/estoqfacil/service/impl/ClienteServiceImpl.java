package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.repository.ClienteRepository;
import br.ueg.cons.soft.estoqfacil.service.ClienteService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.exception.InvalidParameterException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import br.ueg.prog.webi.api.util.Validacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static br.ueg.cons.soft.estoqfacil.exception.SistemaMessageCode.*;

@Service
public class ClienteServiceImpl extends BaseCrudService<Cliente, String, ClienteRepository>
        implements ClienteService {

    @Autowired
    private PessoaServiceImpl pessoaService;
    Validacoes validacoes = new Validacoes();
    @Override
    protected void prepararParaIncluir(Cliente entidade) {

    }

    @Override
    protected void validarDados(Cliente entidade) {
        if(!validacoes.isEmailValido(entidade.getPessoa().getEmail()))
            throw new BusinessException(ERRO_EMAIL_INVALIDO);

        if(!validacoes.validarCPF(entidade.getPessoa().getCpf()))
            throw new BusinessException(ERRO_CPF_INVALIDO);

        if(!validacoes.validarTelefone(entidade.getPessoa().getTelefone()))
            throw new BusinessException(ERRO_TELEFONE_INVALIDO);

    }

    @Override
    protected void validarCamposObrigatorios(Cliente entidade) {

    }

    @Override
    public Cliente alterar(Cliente entidade, String id) {
        return super.alterar(entidade, id);
    }
}
