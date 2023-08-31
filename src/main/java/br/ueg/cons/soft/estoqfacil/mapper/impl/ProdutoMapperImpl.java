package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.ProdutoMapper;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class ProdutoMapperImpl implements ProdutoMapper {

    @Override
    public Produto toModelo(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setCodigo(produtoDTO.getCodigo());
        produto.setProdutoCategoria(produtoDTO.getCategoriaID());
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produto.getPreco());
        produto.setProdutoUsuario(produtoDTO.getUsuarioID());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setQuantidade(produtoDTO.getQuantidade());
        return produto;
    }

    @Override
    public ProdutoDTO toDTO(Produto modelo) {
        return ProdutoDTO.builder()
                .codigo(modelo.getCodigo())
                .categoriaID(modelo.getProdutoCategoria().getId())
                .descricao(modelo.getDescricao())
                .nome(modelo.getNome())
                .preco(modelo.getPreco())
                .quantidade(modelo.getQuantidade())
                .usuarioID(modelo.getProdutoUsuario().getId())
                .build();
    }

    @Override
    public List<ProdutoDTO> toDTO(List<Produto> lista) {
        List<ProdutoDTO> listaDTO = new ArrayList<>();

        lista.forEach(item-> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
