package br.ueg.cons.soft.estoqfacil.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO{

    private Long codigo;

    private String login;

    private String senha;

    private Long funcionarioCodigo;

    private String funcionarioNome;

    private String funcionarioEmail;

    private String funcionarioCargo;
}
