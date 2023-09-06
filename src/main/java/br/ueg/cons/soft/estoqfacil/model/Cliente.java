package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.cons.soft.estoqfacil.model.pks.PkCliente;
import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.IEntidade;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = Cliente.NOME_TABELA)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PkCliente.class)
public class Cliente extends BaseEntidade<PkCliente> {

    public static final String NOME_TABELA = "cliente";

    public static final class Coluna {

        public static final String ID = "clnt_codigo";

        public static final String ID_PESSOA = "clnt_pessoa";

    }

    @Id
    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "cliente_sequence",
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
            foreignKey = @ForeignKey(name = "fk_cliente_pessoa"))
    private Pessoa pessoa;

}
