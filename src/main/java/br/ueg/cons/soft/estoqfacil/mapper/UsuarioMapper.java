package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.UsuarioDTO;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {

    @Mapping(source = "funcionario.codigo", target = "funcionarioCodigo")
    @Mapping(source = "funcionario.pessoa.nome", target = "funcionarioNome")
    @Mapping(source = "funcionario.pessoa.email", target = "funcionarioEmail")
    @Mapping(source = "funcionario.cargo", target = "funcionarioCargo")
    UsuarioDTO toDTO(Usuario usuario);


    @Mapping(source = "funcionarioCodigo", target = "funcionario.codigo")
    Usuario toModelo(UsuarioDTO usuarioDTO);
}

