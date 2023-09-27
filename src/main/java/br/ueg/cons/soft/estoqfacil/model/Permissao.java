package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Permissao.NOME_TABELA)
public class Permissao extends BaseEntidade<Long> {


    public static final String NOME_TABELA = "permissao";

    public static final class Coluna {

        public static final String ID = "perm_codigo";

        public static final String ROLE = "perm_role";
    }

    @Id
    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "permissao_sequence",
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "permissao", fetch = FetchType.LAZY)
    private Set<CargoPermissao> cargoPermissaos = new HashSet<>();

}
