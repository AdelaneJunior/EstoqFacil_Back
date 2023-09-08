package br.ueg.cons.soft.estoqfacil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long codigo;

    private String nome;

    private String descricao;

    private String marca;

    private Long quantidade;

    private Double preco;

    private Double custo;

    private Long imagemId;

    private String imagemPathReference;

    private Long categoriaId;

    private Long usuarioId;
}
