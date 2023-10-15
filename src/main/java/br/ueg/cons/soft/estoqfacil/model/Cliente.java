package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = Cliente.NOME_TABELA)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends BaseEntidade<String> {

    public static final String NOME_TABELA = "cliente";

    public static final class Coluna {

        public static final String CPF = "clnt_cpf";

    }

    @Id
    @Column(name = Coluna.CPF)
    private String cpf;

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = Coluna.CPF,
            referencedColumnName = Pessoa.Coluna.CPF,
            foreignKey = @ForeignKey(name = "fk_cliente_pessoa"))
    private Pessoa pessoa;

}
