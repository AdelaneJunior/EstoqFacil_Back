package br.ueg.cons.soft.estoqfacil.dto;

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
public class ClienteDTO {


    private String cpf;

    public String nome;

    public String telefone;

    public String email;

    @Temporal(TemporalType.DATE)
    public LocalDate nascimento;

}
