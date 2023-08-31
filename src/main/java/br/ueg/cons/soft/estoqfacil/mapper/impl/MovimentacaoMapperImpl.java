package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.dto.MovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.MovimentacaoMapper;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import org.mapstruct.Mapper;

=import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class MovimentacaoMapperImpl implements MovimentacaoMapper {

    @Override
    public Movimentacao toModelo(MovimentacaoDTO movimentacaoDTO) {
        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setMovimentacaoAcao(movimentacaoDTO.getAcao());
        movimentacao.setMovimentacaoData(movimentacaoDTO.getData());
        movimentacao.setMovimentacaoProduto(movimentacaoDTO.getProdutoID());
        movimentacao.setMovimentacaoUsuario(movimentacaoDTO.getUsuarioID());
        movimentacao.setQuantidadeMovimentada(movimentacaoDTO.getQuantidade());
        return movimentacao;
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
