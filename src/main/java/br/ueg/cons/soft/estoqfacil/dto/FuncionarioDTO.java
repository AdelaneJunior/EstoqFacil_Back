package br.ueg.cons.soft.estoqfacil.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO{

    private Long codigo;

    private Long pessoaId;

    private String cargoId;

    private String cargoNome;

}
