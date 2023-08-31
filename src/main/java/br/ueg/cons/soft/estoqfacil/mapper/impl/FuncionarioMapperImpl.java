package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.controller.PessoaController;
import br.ueg.cons.soft.estoqfacil.dto.FuncionarioDTO;
import br.ueg.cons.soft.estoqfacil.mapper.FuncionarioMapper;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.Pessoa;
import org.mapstruct.Mapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public class FuncionarioMapperImpl implements FuncionarioMapper {

    PessoaController pessoaController = new PessoaController();
    PessoaMapperImpl pessoaMapper = new PessoaMapperImpl();

    @Override
    public Funcionario toModelo(FuncionarioDTO funcionarioDTO) {
        Pessoa pessoa = pessoaMapper.toModelo(
                Objects.requireNonNull(pessoaController.ObterPorId(
                        funcionarioDTO.getPessoaID()).getBody()));
        Funcionario funcionario = new Funcionario();
        funcionario.setCodigoPessoa(pessoa);
        funcionario.setCodigo(funcionarioDTO.getCodigo());
        funcionario.setCargo(funcionarioDTO.getCargo());
        return funcionario;
    }

    @Override
    public FuncionarioDTO toDTO(Funcionario modelo) {
        return FuncionarioDTO.builder()
                .codigo(modelo.getCodigo())
                .cargo(modelo.getCargo())
                .pessoaID(modelo.getCodigoPessoa().getId())
                .build();
    }

    @Override
    public List<FuncionarioDTO> toDTO(List<Funcionario> lista) {
        List<FuncionarioDTO> listaDTO = new ArrayList<>();

        lista.forEach(item -> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
