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
    @Mapping(source = "cargoNome", target = "cargo.nome", ignore = true)
    Funcionario toModelo(FuncionarioDTO funcionarioDTO);
}