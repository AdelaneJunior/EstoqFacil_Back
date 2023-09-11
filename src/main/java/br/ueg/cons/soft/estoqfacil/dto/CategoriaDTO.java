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

    public static final class Atributo {
        public static final String NOME = "nome";
        public static final String DESCRICAO = "descricao";

        public static final String USUARIO = "usuarioId";
    }


    private Long codigo;

    private String nome;

    private String descricao;

    private Long usuarioId;

}
