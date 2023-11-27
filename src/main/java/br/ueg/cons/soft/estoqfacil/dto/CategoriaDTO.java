package br.ueg.cons.soft.estoqfacil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Long codigo;

    private String nome;

    private String descricao;

    private Long usuarioId;

}
