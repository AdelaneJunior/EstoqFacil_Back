package br.ueg.cons.soft.estoqfacil.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class EnviaEmailDTO {
    private String email;
    private List<ProdutoDTO> listaProdutos;
}
