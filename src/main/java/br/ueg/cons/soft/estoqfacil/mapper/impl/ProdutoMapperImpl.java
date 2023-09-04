package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.ProdutoMapper;
import br.ueg.cons.soft.estoqfacil.model.Imagem;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.service.impl.CategoriaServiceImpl;
import br.ueg.cons.soft.estoqfacil.service.impl.ImagemServiceImpl;
import br.ueg.cons.soft.estoqfacil.service.impl.UsuarioServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class ProdutoMapperImpl implements ProdutoMapper {

    @Autowired
    private CategoriaServiceImpl categoriaService;

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ImagemServiceImpl imagemService;

    @Override
    public Produto toModelo(ProdutoDTO produtoDTO) {
        return Produto.builder()
                .codigo(produtoDTO.getCodigo())
                .nome(produtoDTO.getNome())
                .descricao(produtoDTO.getDescricao())
                .quantidade(produtoDTO.getQuantidade())
                .preco(produtoDTO.getPreco())
                .marca(produtoDTO.getMarca())
                .produtoCategoria(categoriaService.obterPeloId(produtoDTO.getCategoriaID()))
                .produtoUsuario(usuarioService.obterPeloId(produtoDTO.getUsuarioID()))
                .produtoImagem(imagemService.obterPeloId(produtoDTO.getImagemID()))
                .build();
    }

    @Override
    public ProdutoDTO toDTO(Produto modelo) {
        Imagem imagem = imagemService.obterPeloId(modelo.getProdutoImagem().getCodigo());

        return ProdutoDTO.builder()
                .codigo(modelo.getCodigo())
                .categoriaID(modelo.getProdutoCategoria().getId())
                .descricao(modelo.getDescricao())
                .nome(modelo.getNome())
                .preco(modelo.getPreco())
                .marca(modelo.getMarca())
                .quantidade(modelo.getQuantidade())
                .usuarioID(modelo.getProdutoUsuario().getId())
                .imagemID(imagem.getCodigo())
                .imagemCaminho(imagem.getPathReference())
                .build();
    }

    @Override
    public List<ProdutoDTO> toDTO(List<Produto> lista) {
        List<ProdutoDTO> listaDTO = new ArrayList<>();

        lista.forEach(item-> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
