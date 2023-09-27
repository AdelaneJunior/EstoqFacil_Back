package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.cons.soft.estoqfacil.model.pks.PkFuncionario;
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
@Table(name = Funcionario.NOME_TABELA)
public class Funcionario extends BaseEntidade<Long> {

    public static final String NOME_TABELA = "funcionario";

    public static final class Coluna {

        public static final String ID = "func_codigo";

        public static final String CARGO = "func_cargo";

    }

    @Id
    @Column(name = Coluna.ID)
    private Long codigo;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = Coluna.ID,
            referencedColumnName = Pessoa.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_funcionario_pessoa"))
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Coluna.CARGO, nullable = false,
            referencedColumnName = Cargo.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_funcionario_cargo"))
    private Cargo cargo;
}
