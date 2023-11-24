package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.repository.FuncionarioRepository;
import br.ueg.cons.soft.estoqfacil.service.FuncionarioService;
import br.ueg.prog.webi.api.service.BaseCrudService;
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
        return super.incluir(modelo);
    }

    public List<Funcionario> findFuncionariosWithSortAsc(String field){
        return this.repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public List<Funcionario> findFuncionarioWithPagination(int offset, int pageSize){
        return  this.repository.findFuncionarioWithPagination(offset, pageSize);
    }

    public Integer countRows(){
        return this.repository.countAll();
    }
}
