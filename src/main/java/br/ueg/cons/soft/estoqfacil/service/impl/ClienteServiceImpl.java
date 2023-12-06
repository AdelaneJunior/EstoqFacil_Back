package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.repository.ClienteRepository;
import br.ueg.cons.soft.estoqfacil.repository.ProdutoRepository;
import br.ueg.cons.soft.estoqfacil.service.ClienteService;
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
import java.util.Optional;

import static br.ueg.cons.soft.estoqfacil.exception.SistemaMessageCode.*;

@Service
public class ClienteServiceImpl extends BaseCrudService<Cliente, String, ClienteRepository>
        implements ClienteService {

    @Autowired
    ClienteRepository repository;

    @Autowired
    private PessoaServiceImpl pessoaService;
    Validacoes validacoes = new Validacoes();
    @Override
    protected void prepararParaIncluir(Cliente entidade) {
        if(entidade.getCpf() != null) {
            Optional<Cliente> clienteBD = repository.findById(entidade.getCpf());
            if(clienteBD.isPresent() ){
                throw new BusinessException(ERRO_CLIENTE_DUPLICADO);
            }
        }
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
        Cliente alterar = null;
        try {
            alterar = super.alterar(entidade, id);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(ERRO_CLIENTE_DUPLICADO);
        }

        return alterar;

    }

}
