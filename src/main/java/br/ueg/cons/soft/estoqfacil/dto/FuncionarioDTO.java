package br.ueg.cons.soft.estoqfacil.dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO{

    private Long codigo;

    public String cpf;

    public String nome;

    public String telefone;

    public String email;

    @Temporal(TemporalType.DATE)
    public LocalDate nascimento;

    private Long cargoId;

    private String cargoNome;

}
