package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.dto.UsuarioDTO;
import br.ueg.cons.soft.estoqfacil.mapper.UsuarioMapper;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import org.mapstruct.Mapper;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class UsuarioMapperImpl implements UsuarioMapper {
    @Override
    public Usuario toModelo(UsuarioDTO modelo) {
        Usuario usuario = new Usuario();
        usuario.setCodigo(modelo.getCodigo());
        usuario.setLogin(modelo.getLogin());
        usuario.setSenha(modelo.getSenha());
        return usuario;
    }

    @Override
    public UsuarioDTO toDTO(Usuario modelo) {


        return UsuarioDTO.builder()
                .codigo(modelo.getCodigo())
                .login(modelo.getLogin())
                .senha(modelo.getSenha())
                .build();
    }

    @Override
    public List<UsuarioDTO> toDTO(List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = new ArrayList<>();

        lista.forEach(item -> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
