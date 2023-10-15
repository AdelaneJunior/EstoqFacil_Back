package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.FuncionarioDTO;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {PessoaMapperImpl.class, CargoMapperImpl.class})
public interface FuncionarioMapper extends BaseMapper<Funcionario, FuncionarioDTO> {

    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "cargoId", target = "cargo.codigo")
    @Mapping(source = "nome", target = "pessoa.nome")
    @Mapping(source = "telefone", target = "pessoa.telefone")
    @Mapping(source = "nascimento", target = "pessoa.nascimento")
    @Mapping(source = "email", target = "pessoa.email")
    @Mapping(source = "cpf", target = "pessoa.cpf")
    Funcionario toModelo(FuncionarioDTO funcionarioDTO);

    @Mapping(source = "cargo.codigo", target = "cargoId")
    @Mapping(source = "cargo.nome", target = "cargoNome")
    @Mapping(source = "cpf", target = "cpf")
    @Mapping(source = "pessoa.nome", target = "nome")
    @Mapping(source = "pessoa.telefone", target = "telefone")
    @Mapping(source = "pessoa.nascimento", target = "nascimento")
    @Mapping(source = "pessoa.email", target = "email")
    FuncionarioDTO toDTO(Funcionario funcionario);
}