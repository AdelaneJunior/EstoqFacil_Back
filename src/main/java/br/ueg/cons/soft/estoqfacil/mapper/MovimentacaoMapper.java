package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.MovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MovimentacaoMapper extends BaseMapper<Movimentacao, MovimentacaoDTO> {

    @Mapping(source = "produto.codigo", target = "produtoId")
    @Mapping(source = "usuario.codigo", target = "usuarioId")
    MovimentacaoDTO toDTO(Movimentacao cargoPermissao);

    @Mapping(source = "produtoId", target = "produto.codigo")
    @Mapping(source = "usuarioId", target = "usuario.codigo")
    Movimentacao toModelo(MovimentacaoDTO cargoPermissaoDTO);

}

