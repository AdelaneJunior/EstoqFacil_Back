package br.ueg.cons.soft.estoqfacil.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class EnviaEmailDTO {
    private String email;
    private List<ProdutoDTO> listaProdutos;
    private Boolean promocao;
    private Long desconto;
}
