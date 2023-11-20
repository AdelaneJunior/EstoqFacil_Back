package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.interfaces.ISearchFieldData;
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
@Table(name = Categoria.NOME_TABELA)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categoria extends BaseEntidade<Long> implements ISearchFieldData<Long> {



    public static final String NOME_TABELA = "categoria";

    @Override
    public String getDescription() {
        return this.nome;
    }

    public static final class Coluna {

        public static final String ID = "catg_codigo";

        public static final String NOME = "catg_nome";

        public static final String DESCRICAO = "catg_descricao";

        public static final String USUARIO = "catg_usuario";
    }

    public static final class Atributo {
        public static final String NOME = "nome";
        public static final String DESCRICAO = "descricao";

        public static final String USUARIO = "usuario";
    }

    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "categoria_sequence",
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

    @Column(name = Coluna.NOME, nullable = false)
    @Searchable()
    private String nome;

    @Column(name = Coluna.DESCRICAO, nullable = false)
    @Searchable(label = "Descrição")
    private String descricao;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = Coluna.USUARIO, nullable = false,
            referencedColumnName = Usuario.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_categoria_usuario"))
    private Usuario usuario;
}
