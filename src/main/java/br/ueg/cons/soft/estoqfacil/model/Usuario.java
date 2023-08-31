package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.IEntidade;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Getter
@Table(name = Usuario.NOME_TABELA)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements IEntidade<Long> {

    public static final String NOME_TABELA = "usuario";

    public static final class Coluna {
        public static final String ID = "usuo_codigo";
        public static final String LOGIN = "usuo_login";
        public static final String STATUS = "usuo_status";
        public static final String SENHA = "usuo_senha";
        public static final String ID_FUNCIONARIO = "usuo_funcionario";

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

    @Column(name = Coluna.LOGIN, length = 30, nullable = false, unique = true)
    private String login;

    @Column(name = Coluna.SENHA, nullable = false)
    private String senha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Coluna.ID_FUNCIONARIO, unique = true, nullable = false,
            referencedColumnName = Funcionario.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_usuario_funcionario"))
    private Funcionario usuarioFuncionario;
    
    @Override
    public String getTabelaNome() {
        return NOME_TABELA;
    }

    @Override
    public Long getId() {
        return codigo;
    }

    @Override
    public void setId(Long id) {
        this.codigo = id;
    }
}
