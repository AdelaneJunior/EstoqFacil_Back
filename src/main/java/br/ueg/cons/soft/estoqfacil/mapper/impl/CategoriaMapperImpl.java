package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.dto.CategoriaDTO;
import br.ueg.cons.soft.estoqfacil.mapper.CategoriaMapper;
import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.cons.soft.estoqfacil.service.impl.UsuarioServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class CategoriaMapperImpl implements CategoriaMapper {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Override
    public Categoria toModelo(CategoriaDTO categoriaDTO) {
        return Categoria.builder()
                .categoriaUsuario(usuarioService.obterPeloId(categoriaDTO.getUsuarioID()))
                .descricaoCategoria(categoriaDTO.getDescricao())
                .nomeCategoria(categoriaDTO.getNome())
                .codigo(categoriaDTO.getCodigo())
                .build();
    }

    @Override
    public CategoriaDTO toDTO(Categoria modelo) {
        return CategoriaDTO.builder()
                .codigo(modelo.getCodigo())
                .descricao(modelo.getDescricaoCategoria())
                .nome(modelo.getNomeCategoria())
                .usuarioID(modelo.getCategoriaUsuario().getId())
                .build();
    }

    @Override
    public List<CategoriaDTO> toDTO(List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = new ArrayList<>();

        lista.forEach(item -> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
