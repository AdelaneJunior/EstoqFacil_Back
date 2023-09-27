package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.cons.soft.estoqfacil.model.pks.PkCliente;
import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = Cliente.NOME_TABELA)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends BaseEntidade<Long> {

    public static final String NOME_TABELA = "cliente";

    public static final class Coluna {

        public static final String ID = "clnt_codigo";

        public static final String ID_PESSOA = "clnt_pessoa";

    }

    @Id
    @Column(name = Coluna.ID)
    private Long codigo;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = Coluna.ID_PESSOA,
            referencedColumnName = Pessoa.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_cliente_pessoa"))
    private Pessoa pessoa;

}
