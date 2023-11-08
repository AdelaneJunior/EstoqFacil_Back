package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.controller.ImagemController;
import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.ProdutoMapper;
import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import br.ueg.prog.webi.api.model.IEntidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProdutoMapperImpl implements ProdutoMapper {
    @Autowired
    ImagemController imagemController;

    @Override
    public List<ProdutoDTO> toDTO(List<Produto> lista) {
        if ( lista == null ) {
            return null;
        }

        List<ProdutoDTO> list = new ArrayList<ProdutoDTO>( lista.size() );
        for ( Produto produto : lista ) {
            list.add( toDTO( produto ) );
        }

        return list;
    }

    @Override
    public void updateModel(Produto source, Produto target) {
        if ( source == null ) {
            return;
        }

        if ( source.getId() != null ) {
            target.setId( source.getId() );
        }
        if ( source.getCompositePkEntidadeObject() != null ) {
            target.setCompositePkEntidadeObject( source.getCompositePkEntidadeObject() );
        }
        if ( target.getForeignEntitiesMaps() != null ) {
            Map<String, IEntidade<?>> map = source.getForeignEntitiesMaps();
            if ( map != null ) {
                target.getForeignEntitiesMaps().clear();
                target.getForeignEntitiesMaps().putAll( map );
            }
        }
        else {
            Map<String, IEntidade<?>> map = source.getForeignEntitiesMaps();
            if ( map != null ) {
                target.setForeignEntitiesMaps( new LinkedHashMap<String, IEntidade<?>>( map ) );
            }
        }
        if ( source.getCodigo() != null ) {
            target.setCodigo( source.getCodigo() );
        }
        if ( source.getCategoria() != null ) {
            target.setCategoria( source.getCategoria() );
        }
        if ( source.getUsuario() != null ) {
            target.setUsuario( source.getUsuario() );
        }
        target.setImagem_id( source.getImagem_id() );
        if ( source.getNome() != null ) {
            target.setNome( source.getNome() );
        }
        if ( source.getDescricao() != null ) {
            target.setDescricao( source.getDescricao() );
        }
        if ( source.getQuantidade() != null ) {
            target.setQuantidade( source.getQuantidade() );
        }
        if ( source.getPreco() != null ) {
            target.setPreco( source.getPreco() );
        }
        if ( source.getMarca() != null ) {
            target.setMarca( source.getMarca() );
        }
        if ( source.getCusto() != null ) {
            target.setCusto( source.getCusto() );
        }
    }

    @Override
    public ProdutoDTO toDTO(Produto produto) {
        if ( produto == null ) {
            return null;
        }

        ProdutoDTO.ProdutoDTOBuilder produtoDTO = ProdutoDTO.builder();

        produtoDTO.imagem(
                new String(Base64.getEncoder().encode((byte[]) imagemController.getImage(produto.getImagem_id()).getBody())
        ));
        produtoDTO.imagemId( produto.getImagem_id() );
        produtoDTO.categoriaId( produtoCategoriaCodigo( produto ) );
        produtoDTO.categoriaNome( produtoCategoriaNome( produto ) );
        produtoDTO.usuarioId( produtoUsuarioCodigo( produto ) );
        produtoDTO.codigo( produto.getCodigo() );
        produtoDTO.nome( produto.getNome() );
        produtoDTO.descricao( produto.getDescricao() );
        produtoDTO.marca( produto.getMarca() );
        produtoDTO.quantidade( produto.getQuantidade() );
        produtoDTO.preco( produto.getPreco() );
        produtoDTO.custo( produto.getCusto() );

        return produtoDTO.build();
    }

    @Override
    public Produto toModelo(ProdutoDTO produtoDTO) {
        if ( produtoDTO == null ) {
            return null;
        }

        Produto.ProdutoBuilder produto = Produto.builder();

        produto.categoria( produtoDTOToCategoria( produtoDTO ) );
        produto.usuario( produtoDTOToUsuario( produtoDTO ) );
        if ( produtoDTO.getImagemId() != null ) {
            produto.imagem_id( produtoDTO.getImagemId() );
        }
        produto.codigo( produtoDTO.getCodigo() );
        produto.nome( produtoDTO.getNome() );
        produto.descricao( produtoDTO.getDescricao() );
        produto.quantidade( produtoDTO.getQuantidade() );
        produto.preco( produtoDTO.getPreco() );
        produto.marca( produtoDTO.getMarca() );
        produto.custo( produtoDTO.getCusto() );

        return produto.build();
    }

    private Long produtoCategoriaCodigo(Produto produto) {
        if ( produto == null ) {
            return null;
        }
        Categoria categoria = produto.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        Long codigo = categoria.getCodigo();
        if ( codigo == null ) {
            return null;
        }
        return codigo;
    }

    private String produtoCategoriaNome(Produto produto) {
        if ( produto == null ) {
            return null;
        }
        Categoria categoria = produto.getCategoria();
        if ( categoria == null ) {
            return null;
        }
        String nome = categoria.getNome();
        if ( nome == null ) {
            return null;
        }
        return nome;
    }

    private Long produtoUsuarioCodigo(Produto produto) {
        if ( produto == null ) {
            return null;
        }
        Usuario usuario = produto.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        Long codigo = usuario.getCodigo();
        if ( codigo == null ) {
            return null;
        }
        return codigo;
    }

    protected Categoria produtoDTOToCategoria(ProdutoDTO produtoDTO) {
        if ( produtoDTO == null ) {
            return null;
        }

        Categoria.CategoriaBuilder categoria = Categoria.builder();

        categoria.codigo( produtoDTO.getCategoriaId() );
        categoria.nome( produtoDTO.getCategoriaNome() );

        return categoria.build();
    }

    protected Usuario produtoDTOToUsuario(ProdutoDTO produtoDTO) {
        if ( produtoDTO == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.codigo( produtoDTO.getUsuarioId() );

        return usuario.build();
    }
}
