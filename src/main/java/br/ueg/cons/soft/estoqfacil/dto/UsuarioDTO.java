package br.ueg.cons.soft.estoqfacil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO{

    private Long codigo;

    private String senha;

    private Long funcionarioCodigo;

    private String funcionarioNome;

    private String funcionarioEmail;

    private String funcionarioCargo;

}
