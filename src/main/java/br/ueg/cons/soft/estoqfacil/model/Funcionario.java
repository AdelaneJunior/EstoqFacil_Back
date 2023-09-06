package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.cons.soft.estoqfacil.model.pks.PkFuncionario;
import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.IEntidade;
import jakarta.persistence.*;
import lombok.*;


import static jakarta.persistence.GenerationType.SEQUENCE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Funcionario.NOME_TABELA)
@IdClass(PkFuncionario.class)
public class Funcionario extends BaseEntidade<PkFuncionario> {

    public static final String NOME_TABELA = "funcionario";

    public static final class Coluna {

        public static final String ID = "func_codigo";

        public static final String ID_PESSOA = "func_pessoa";

        public static final String CARGO = "func_cargo";

    }

    public static final class Atributo {

    }

    @Id
    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "funcionario_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"

    )
    @Column(name = Coluna.ID)
    private Long codigo;

    @Id
    @ManyToOne
    @JoinColumn(name = Coluna.ID_PESSOA,
            referencedColumnName = Pessoa.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_funcionario_pessoa"))
    private Pessoa pessoa;

    @Column(name = Coluna.CARGO, nullable = false)
    private String cargo;
}
