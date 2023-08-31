package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.IEntidade;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@Table(name = Produto.NOME_TABELA)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public @Data class Produto implements IEntidade<Long> {

    public static final String NOME_TABELA = "produto";

    public static final class Coluna {

        public static final String ID = "prod_codigo";

        public static final String ID_CATEGORIA = "prod_categoria";

        public static final String ID_USUARIO = "prod_usuario";

        public static final String NOME = "prod_nome";

        public static final String DESCRICAO = "prod_descricao";

        public static final String QUANTIDADE = "prod_quantidade";

        public static final String PRECO = "prod_preco";
    }

    public static final class Atributo {
        public static final String CODIGO = "codigo";
    }

    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "produto_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"

    )

    @Id
    @Column(name = Coluna.ID)
    private Long codigo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Coluna.ID_CATEGORIA, nullable = false,
            referencedColumnName = Categoria.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_produto_categoria"))
    private Categoria produtoCategoria;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Coluna.ID_USUARIO, nullable = false,
            referencedColumnName = Usuario.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_produto_usuario"))
    private Usuario produtoUsuario;

    @Column(name = Coluna.NOME, nullable = false)
    private String nome;

    @Column(name = Coluna.DESCRICAO, nullable = false)
    private String descricao;

    @Column(name = Coluna.QUANTIDADE, nullable = false)
    private Long quantidade;

    @Column(name = Coluna.PRECO, nullable = false)
    private Double preco;


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
