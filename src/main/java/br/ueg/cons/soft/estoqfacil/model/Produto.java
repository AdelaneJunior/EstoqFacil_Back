package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = Produto.NOME_TABELA)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends BaseEntidade<Long> {

    public static final String NOME_TABELA = "produto";

    public static final class Coluna {

        public static final String ID = "prod_codigo";

        public static final String ID_CATEGORIA = "prod_categoria";

        public static final String ID_USUARIO = "prod_usuario";

        public static final String ID_IMAGEM = "prod_imagem";

        public static final String NOME = "prod_nome";

        public static final String DESCRICAO = "prod_descricao";

        public static final String MARCA = "prod_marca";

        public static final String QUANTIDADE = "prod_quantidade";

        public static final String PRECO = "prod_preco_venda";

        public static final String CUSTO = "prod_custo_aquisicao";
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
    @Column(name = Produto.Coluna.ID)
    private Long codigo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Produto.Coluna.ID_CATEGORIA, nullable = false,
            referencedColumnName = Categoria.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_produto_categoria"))
    private Categoria produtoCategoria;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Produto.Coluna.ID_USUARIO, nullable = false,
            referencedColumnName = Usuario.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_produto_usuario"))
    private Usuario produtoUsuario;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = Produto.Coluna.ID_IMAGEM, nullable = false,
            referencedColumnName = Imagem.COLUNA.ID,
            foreignKey = @ForeignKey(name = "fk_produto_imagem"))
    private Imagem produtoImagem;

    @Column(name = Produto.Coluna.NOME, nullable = false)
    private String nome;

    @Column(name = Produto.Coluna.DESCRICAO, nullable = false)
    private String descricao;

    @Column(name = Produto.Coluna.QUANTIDADE, nullable = false)
    private Long quantidade;

    @Column(name = Produto.Coluna.PRECO, nullable = false)
    private Double preco;

    @Column(name = Produto.Coluna.MARCA, nullable = false)
    private String marca;

    @Column(name = Produto.Coluna.CUSTO, nullable = false)
    private Double custo;

}
