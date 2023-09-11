package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.cons.soft.estoqfacil.model.pks.PkCargoPermissao;
import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = CargoPermissao.NOME_TABELA)
@IdClass(PkCargoPermissao.class)
public class CargoPermissao extends BaseEntidade<PkCargoPermissao> {


    public static final String NOME_TABELA = "cargo_permissao";
    public static final class Coluna {

        public static final String ID = "peca_codigo";

        public static final String CARGO = "peca_cargo";

        public static final String PERMISSOES = "peca_permissao";
    }


    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Coluna.PERMISSOES, nullable = false,
            referencedColumnName = Permissao.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_cargo_permissao_permissao"))
    private Permissao permissao;

    @Id
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Coluna.CARGO, nullable = false,
            referencedColumnName = Cargo.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_cargo_permissao_cargo"))
    private Cargo cargo;
}
