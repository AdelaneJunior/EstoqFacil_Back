package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import br.ueg.cons.soft.estoqfacil.enums.TipoMovimentacao;
import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@Table(name = Movimentacao.NOME_TABELA)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movimentacao extends BaseEntidade<Long> {

    public static final String NOME_TABELA = "movimentacao";

    public static final class Coluna {

        public static final String ID = "movt_codigo";

        public static final String ID_PRODUTO = "movt_produto";

        public static final String ID_USUARIO = "movt_usuario";

        public static final String DATA_MOVIMENTACAO = "movt_data";

        public static final String QUANTIDADE = "movt_quantidade";

        public static final String ACAO = "movt_acao";

        public static final String TIPO = "movt_tipo";

        public static final String OBSERVACAO = "movt_obersavacao";

        public static final String PRECO = "movt_preco_venda";

        public static final String CUSTO = "movt_custo_aquisicao";
    }

    public static final class Atributo {
        public static final String CODIGO = "codigo";
    }

    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "movimentacao_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"

    )

    @Id
    @Column(name = Coluna.ID)
    private Long codigo;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = Coluna.ID_PRODUTO, nullable = false,
            referencedColumnName = Produto.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_movimentacao_produto"))
    private Produto produto;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = Coluna.ID_USUARIO, nullable = false,
            referencedColumnName = Usuario.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_movimentacao_usuario"))
    private Usuario usuario;

    @Column(name = Coluna.QUANTIDADE, nullable = false)
    private Long quantidade;

    @Temporal(TemporalType.DATE)
    @Column(name = Coluna.DATA_MOVIMENTACAO, nullable = false)
    private LocalDate data;

    @Column(name = Coluna.ACAO, nullable = false)
    private AcaoMovimentacao acao;

    @Column(name = Coluna.TIPO, nullable = false)
    private TipoMovimentacao tipo;

    @Column(name = Coluna.OBSERVACAO, nullable = false)
    private String observacao;

    @Column(name = Coluna.PRECO, nullable = false)
    private Double preco;

    @Column(name = Coluna.CUSTO, nullable = false)
    private Double custo;

}
