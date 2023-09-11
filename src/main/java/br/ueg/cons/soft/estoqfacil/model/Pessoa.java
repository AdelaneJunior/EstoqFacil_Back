package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.IEntidade;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Pessoa.NOME_TABELA)
public class Pessoa extends BaseEntidade<Long> {

    public static final String NOME_TABELA = "pessoa";

    public static final class Coluna{

        public static final String ID = "pess_codigo";
        public static final String NOME = "pess_nome";
        public static final String TELEFONE = "pess_telefone";
        public static final String EMAIL = "pess_email";
        public static final String DATA_NASCIMENTO = "pess_nascimento";
        public static final String CPF = "pess_cpf";

    }

    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "pessoa_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"

    )

    @Id
    @Column(name = Coluna.ID)
    private Long codigo;

    @Column(name = Coluna.CPF, nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = Coluna.NOME, nullable = false, length = 200)
    private String nome;

    @Column(name = Coluna.TELEFONE, length = 20)
    private String telefone;

    @Column(name = Coluna.EMAIL, length = 150)
    private String email;

    @Column(name = Coluna.DATA_NASCIMENTO)
    private LocalDate nascimento;

}
