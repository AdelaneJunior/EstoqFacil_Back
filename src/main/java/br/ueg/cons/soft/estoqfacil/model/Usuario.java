package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.annotation.Searchable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = Usuario.NOME_TABELA)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends BaseEntidade<Long> {

    public static final String NOME_TABELA = "usuario";

    public static final class Coluna {
        public static final String ID = "usuo_codigo";
        public static final String SENHA = "usuo_senha";
        public static final String CPF_FUNCIONARIO = "usuo_funcionario";

    }

    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "usuario_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"

    )

    @Id
    @Column(name = Coluna.ID)
    @Searchable(label = "Código")
    private Long codigo;

    @Column(name = Coluna.SENHA, nullable = false)
    private String senha;

    @ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = Coluna.CPF_FUNCIONARIO, unique = true, nullable = false,
            referencedColumnName = Funcionario.Coluna.CPF,
            foreignKey = @ForeignKey(name = "fk_usuario_funcionario"))
    @Searchable()
    private Funcionario funcionario;
}
