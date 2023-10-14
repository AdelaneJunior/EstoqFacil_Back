package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.ClienteDTO;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDTO> {

    @Mapping(source = "nome", target = "pessoa.nome")
    @Mapping(source = "telefone", target = "pessoa.telefone")
    @Mapping(source = "nascimento", target = "pessoa.nascimento")
    @Mapping(source = "email", target = "pessoa.email")
    @Mapping(source = "cpf", target = "pessoa.cpf")
    @Mapping(source = "pessoaCodigo", target = "pessoa.codigo")
    Cliente toModelo(ClienteDTO clienteDTO);

    @Mapping(source = "pessoa.nome", target = "nome")
    @Mapping(source = "pessoa.telefone", target = "telefone")
    @Mapping(source = "pessoa.nascimento", target = "nascimento")
    @Mapping(source = "pessoa.email", target = "email")
    @Mapping(source = "pessoa.cpf", target = "cpf")
    @Mapping(source = "pessoa.codigo", target = "pessoaCodigo")
    ClienteDTO toDTO(Cliente cliente);
}

