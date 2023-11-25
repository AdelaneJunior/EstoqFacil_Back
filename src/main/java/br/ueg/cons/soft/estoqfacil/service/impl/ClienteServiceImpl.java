package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.repository.ClienteRepository;
import br.ueg.cons.soft.estoqfacil.service.ClienteService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteServiceImpl extends BaseCrudService<Cliente, String, ClienteRepository>
        implements ClienteService {

    @Autowired
    private PessoaServiceImpl pessoaService;
    @Override
    protected void prepararParaIncluir(Cliente entidade) {

    }

    @Override
    protected void validarDados(Cliente entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Cliente entidade) {

    }

    @Override
    public Cliente alterar(Cliente entidade, String id) {
        return super.alterar(entidade, id);
    }

    public List<Cliente> findClienteWithSortAsc(String field){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public List<Cliente> findClienteWithPagination(int offset, int pageSize){
        return  this.repository.findClienteWithPagination(offset, pageSize);
    }

    public Integer countRows(){
        return this.repository.countAll();
    }
}
