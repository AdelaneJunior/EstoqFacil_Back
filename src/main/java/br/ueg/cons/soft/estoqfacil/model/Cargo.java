package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Cargo.NOME_TABELA)
public class Cargo extends BaseEntidade<Long> {

    public static final String NOME_TABELA = "cargo";

    public static final class Coluna {

        public static final String ID = "carg_codigo";

        public static final String NOME = "carg_nome";

        public static final String PERMISSOES = "carg_permissoes";

    }

    @Id
    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "cargo_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"

    )
    @Column(name = Coluna.ID)
    private Long codigo;

    @Column(name = Coluna.NOME, length = 100, nullable = false)
    private String nome;

    @OneToMany(
            mappedBy = "cargo",
            cascade = CascadeType.ALL)
    private List<Permissoes> permissoes;
}
