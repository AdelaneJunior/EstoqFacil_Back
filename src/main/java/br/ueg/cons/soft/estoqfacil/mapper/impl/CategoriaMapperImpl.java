package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.controller.UsuarioController;
import br.ueg.cons.soft.estoqfacil.dto.CategoriaDTO;
import br.ueg.cons.soft.estoqfacil.mapper.CategoriaMapper;
import br.ueg.cons.soft.estoqfacil.model.Categoria;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class CategoriaMapperImpl implements CategoriaMapper {
    UsuarioController usuarioController = new UsuarioController();
    UsuarioMapperImpl usuarioMapper = new UsuarioMapperImpl();

    @Override
    public Categoria toModelo(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setCategoriaUsuario();
        categoria.setDescricaoCategoria(categoriaDTO.getDescricao());
        categoria.setNomeCategoria(categoriaDTO.getNome());
        categoria.setCodigo(categoriaDTO.getCodigo());
        return categoria;
    }

    @Override
    public CategoriaDTO toDTO(Categoria modelo) {
        return CategoriaDTO.builder()
                .codigo(modelo.getCodigo())
                .descricao(modelo.getDescricaoCategoria())
                .nome(modelo.getNomeCategoria())
                .usuarioID(
                        usuarioController.obterPorLogin(
                                modelo.getCategoriaUsuario())
                .build();
    }

    @Override
    public List<CategoriaDTO> toDTO(List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = new ArrayList<>();

        lista.forEach(item -> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
