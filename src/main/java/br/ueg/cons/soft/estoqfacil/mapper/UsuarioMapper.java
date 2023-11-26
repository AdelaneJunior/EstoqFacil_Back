package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.UsuarioDTO;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {FuncionarioMapperImpl.class})
public interface UsuarioMapper extends BaseMapper<Usuario, UsuarioDTO> {

    @Mapping(source = "funcionario.cpf", target = "funcionarioCpf")
    @Mapping(source = "funcionario.pessoa.nome", target = "funcionarioNome")
    @Mapping(source = "funcionario.pessoa.email", target = "funcionarioEmail")
    @Mapping(source = "funcionario.cargo.codigo", target = "funcionarioCargo")
    @Mapping(target = "permissoes", ignore = true)
    @Mapping(target = "senha", ignore = true)
    UsuarioDTO toDTO(Usuario usuario);


    @Mapping(source = "funcionarioCpf", target = "funcionario.cpf")
    @Mapping(source = "permissoes", target = "funcionario.cargo.permissoes", ignore = true)
    Usuario toModelo(UsuarioDTO usuarioDTO);
}

