package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.dto.MovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.MovimentacaoMapper;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.cons.soft.estoqfacil.service.impl.ProdutoServiceImpl;
import br.ueg.cons.soft.estoqfacil.service.impl.UsuarioServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

=import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class MovimentacaoMapperImpl implements MovimentacaoMapper {

    @Autowired
    ProdutoServiceImpl produtoService;

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Override
    public Movimentacao toModelo(MovimentacaoDTO movimentacaoDTO) {
        return Movimentacao.builder()
                .movimentacaoAcao(movimentacaoDTO.getAcao())
                .movimentacaoData(movimentacaoDTO.getData())
                .movimentacaoProduto(produtoService.obterPeloId(movimentacaoDTO.getProdutoID()))
                .movimentacaoUsuario(usuarioService.obterPeloId(movimentacaoDTO.getUsuarioID()))
                .quantidadeMovimentada(movimentacaoDTO.getQuantidade())
                .build();
    }

    @Override
    public MovimentacaoDTO toDTO(Movimentacao modelo) {
        return MovimentacaoDTO.builder()
                .codigo(modelo.getCodigo())
                .acao(modelo.getMovimentacaoAcao())
                .data(modelo.getMovimentacaoData())
                .produtoID(modelo.getMovimentacaoProduto().getId())
                .usuarioID(modelo.getMovimentacaoUsuario().getId())
                .quantidade(modelo.getQuantidadeMovimentada())
                .build();
    }

    @Override
    public List<MovimentacaoDTO> toDTO(List<Movimentacao> lista) {
        List<MovimentacaoDTO> listaDTO = new ArrayList<>();

        lista.forEach(item -> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
