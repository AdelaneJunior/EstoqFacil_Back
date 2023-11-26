package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.MovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {ProdutoMapperImpl.class, UsuarioMapperImpl.class})
public interface MovimentacaoMapper extends BaseMapper<Movimentacao, MovimentacaoDTO> {

    @Mapping(source = "produto.codigo", target = "produtoId")
    @Mapping(source = "produto.nome", target = "produtoNome")
    @Mapping(source = "produto.categoria.nome", target = "produtoCategoriaNome")
    @Mapping(source = "usuario.codigo", target = "usuarioId")
    @Mapping(source = "usuario.funcionario.pessoa.nome", target = "usuarioNome")
    @Mapping(source = "tipo.descricao", target = "tipo")
    MovimentacaoDTO toDTO(Movimentacao cargoPermissao);

    @Mapping(source = "produtoId", target = "produto.codigo")
    @Mapping(source = "usuarioId", target = "usuario.codigo")
    Movimentacao toModelo(MovimentacaoDTO cargoPermissaoDTO);

}

