package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Cargo;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.pks.PkFuncionario;
import br.ueg.cons.soft.estoqfacil.repository.FuncionarioRepository;
import br.ueg.cons.soft.estoqfacil.repository.PessoaRepository;
import br.ueg.cons.soft.estoqfacil.service.FuncionarioService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FuncionarioServiceImpl extends BaseCrudService<Funcionario, PkFuncionario, FuncionarioRepository>
        implements FuncionarioService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    protected void prepararParaIncluir(Funcionario funcionario) {

        tratarPessoa(funcionario);

    }

    private void tratarPessoa(Funcionario funcionario) {

        if(Objects.nonNull(funcionario.getPessoa()) && Objects.nonNull(funcionario.getPessoa().getCodigo())){
            if(pessoaRepository.findById(funcionario.getPessoa().getCodigo()).isPresent()){
               funcionario.setPessoa(pessoaRepository.findById(funcionario.getPessoa().getCodigo()).get());
            }
        }

    }

    @Override
    protected void validarDados(Funcionario entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Funcionario entidade) {

    }

    public List<Funcionario> listarTodosSemPermissoes() {

        List<Funcionario> todosFuncionarios = repository.listaTodosSemFetch();
        todosFuncionarios.forEach(funcionario -> {
            funcionario.getCargo().setPermissoes(null);
        });
        return todosFuncionarios;
    }

    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }
}
