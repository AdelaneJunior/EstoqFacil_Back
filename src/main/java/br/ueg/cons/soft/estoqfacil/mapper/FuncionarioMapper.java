package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.FuncionarioDTO;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface FuncionarioMapper extends BaseMapper<Funcionario, FuncionarioDTO> {

    @Mapping(source = "pessoaId", target = "pessoa.codigo")
    @Mapping(source = "cargoId", target = "cargo.codigo")
    Funcionario toModelo(FuncionarioDTO funcionarioDTO);


    @Mapping(source = "pessoa.codigo", target = "pessoaId")
    @Mapping(source = "pessoa.nome", target = "pessoaNome")
    @Mapping(source = "cargo.codigo", target = "cargoId")
    @Mapping(source = "cargo.nome", target = "cargoNome")
    FuncionarioDTO toDTO(Funcionario funcionario);
}