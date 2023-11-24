package br.ueg.cons.soft.estoqfacil.dto;

import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoDTO {

    private Long codigo;

    private Long produtoId;

    private String produtoNome;

    private String produtoCategoriaNome;

    private Long usuarioId;

    private String usuarioNome;

    private Long quantidade;

    @Temporal(TemporalType.DATE)
    private LocalDate data;

    private AcaoMovimentacao acao;

    private String observacao;

    private String tipo;

}
