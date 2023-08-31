package br.ueg.cons.soft.estoqfacil.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO extends PessoaDTO{

    private Long codigo;

    private Long pessoaID;

    private String cargo;

}
