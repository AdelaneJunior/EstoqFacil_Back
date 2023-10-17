package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProdutoMapper extends BaseMapper<Produto, ProdutoDTO> {


    @Mapping(source = "imagem_id", target = "imagemId")
    @Mapping(source = "categoria.codigo", target = "categoriaId")
    @Mapping(source = "usuario.codigo", target = "usuarioId")
    ProdutoDTO toDTO(Produto produto);

    @Mapping(source = "imagemId", target = "imagem_id")
    @Mapping(source = "categoriaId", target = "categoria.codigo")
    @Mapping(source = "usuarioId", target = "usuario.codigo")
    Produto toModelo(ProdutoDTO produtoDTO);
}

