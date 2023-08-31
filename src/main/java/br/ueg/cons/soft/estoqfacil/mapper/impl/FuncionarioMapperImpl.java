package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.controller.PessoaController;
import br.ueg.cons.soft.estoqfacil.dto.FuncionarioDTO;
import br.ueg.cons.soft.estoqfacil.mapper.FuncionarioMapper;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.Pessoa;
import br.ueg.cons.soft.estoqfacil.service.impl.PessoaServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public class FuncionarioMapperImpl implements FuncionarioMapper {

    @Autowired
    PessoaServiceImpl pessoaService;

    @Override
    public Funcionario toModelo(FuncionarioDTO funcionarioDTO) {
        return Funcionario.builder()
                .cargo(funcionarioDTO.getCargo())
                .codigo(funcionarioDTO.getCodigo())
                .codigoPessoa(pessoaService.obterPeloId(funcionarioDTO.getPessoaID()))
                .build();
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
