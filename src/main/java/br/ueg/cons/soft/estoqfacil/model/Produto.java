package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.annotation.Searchable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = Produto.Coluna.ID_CATEGORIA, nullable = false,
            referencedColumnName = Categoria.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_produto_categoria"))
    @Searchable()
    private Categoria categoria;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = Produto.Coluna.ID_USUARIO, nullable = false,
            referencedColumnName = Usuario.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_produto_usuario"))
    private Usuario usuario;

    @Column(name = Coluna.ID_IMAGEM, nullable = false)
    private Long imagemId;

    @Column(name = Produto.Coluna.NOME, nullable = false)
    @Searchable()
    private String nome;

    @Column(name = Produto.Coluna.DESCRICAO, nullable = false)
    @Searchable(label = "Descrição")
    private String descricao;

    @Column(name = Produto.Coluna.QUANTIDADE, nullable = false)
    private Long quantidade;

    @Column(name = Produto.Coluna.PRECO, nullable = false)
    @Searchable(label = "Preço")
    private BigDecimal preco;

    @Column(name = Produto.Coluna.MARCA, nullable = false)
    @Searchable()
    private String marca;

    @Column(name = Produto.Coluna.CUSTO, nullable = false)
    private BigDecimal custo;
}
