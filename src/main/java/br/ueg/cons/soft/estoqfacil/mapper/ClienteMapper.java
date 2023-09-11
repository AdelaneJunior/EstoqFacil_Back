package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.ClienteDTO;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDTO> {

    @Mapping(source = "pessoaId", target = "pessoa.codigo")
    Cliente toModelo(ClienteDTO clienteDTO);

    @Mapping(source = "pessoa.codigo", target = "pessoaId")
    ClienteDTO toDTO(Cliente cliente);
}

