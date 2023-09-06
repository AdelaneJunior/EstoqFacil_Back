package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Permissoes.NOME_TABELA)
public class Permissoes extends BaseEntidade<Long> {


    public static final String NOME_TABELA = "permissoes";

    public static final class Coluna {

        public static final String ID = "perm_codigo";

        public static final String ROLE = "perm_role";
    }

    @Id
    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "permissoes_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"

    )
    @Column(name = Coluna.ID)
    private Long codigo;

    @Column(name = Coluna.ROLE)
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cargo cargo;
}
