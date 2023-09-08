package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
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
        public static final String STATUS = "usuo_status";
        public static final String SENHA = "usuo_senha";
        public static final String ID_FUNCIONARIO = "usuo_funcionario";

        public static final String ID_PESSOA = "usuo_pessoa";

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
    private Long codigo;

    @Column(name = Coluna.SENHA, nullable = false)
    private String senha;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = Coluna.ID_FUNCIONARIO, unique = true, nullable = false,
                    referencedColumnName = Funcionario.Coluna.ID,
                    foreignKey = @ForeignKey(name = "fk_usuario_funcionario")),
            @JoinColumn(name = Coluna.ID_PESSOA, unique = true, nullable = false,
                    referencedColumnName = Funcionario.Coluna.ID_PESSOA,
                    foreignKey = @ForeignKey(name = "fk_usuario_pessoa"))
    })
    private Funcionario funcionario;



}
