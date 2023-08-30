package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.IEntidade;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@Table(name = Categoria.NOME_TABELA)
public @Data class Categoria implements IEntidade<Long> {

    public static final String NOME_TABELA = "categoria";

    public static final class Coluna {

        public static final String ID = "catg_codigo";

        public static final String NOME = "catg_nome";

        public static final String DESCRICAO = "catg_descricao";

        public static final String ID_USUARIO = "catg_usuario";
    }

    public static final class Atributo {

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
    private Long codigo;

    @Column(name = Coluna.NOME, nullable = false)
    private String nomeCategoria;

    @Column(name = Coluna.DESCRICAO, nullable = false)
    private String descricaoCategoria;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Coluna.ID_USUARIO, nullable = false,
            referencedColumnName = Usuario.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_categoria_usuario"))
    private Usuario categoriaUsuario;

    @Override
    public String getTabelaNome() {
        return NOME_TABELA;
    }

    @Override
    public Long getId() {
        return getCodigo();
    }

    @Override
    public void setId(Long id) {
        setCodigo(id);
    }
}
