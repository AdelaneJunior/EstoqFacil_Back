package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.cons.soft.estoqfacil.enums.ACAO;
import br.ueg.prog.webi.api.model.IEntidade;
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
public class Movimentacao implements IEntidade<Long> {

    public static final String NOME_TABELA = "movimentacao";

    public static final class Coluna {

        public static final String ID = "movt_codigo";

        public static final String ID_PRODUTO = "movt_produto";

        public static final String ID_USUARIO = "movt_usuario";

        public static final String DATA_MOVIMENTACAO = "movt_data";

        public static final String QUANTIDADE = "prod_quantidade";

        public static final String ACAO = "movt_acao";
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Coluna.ID_PRODUTO, nullable = false,
            referencedColumnName = Produto.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_movimentacao_produto"))
    private Produto movimentacaoProduto;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = Coluna.ID_USUARIO, nullable = false,
            referencedColumnName = Usuario.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_movimentacao_usuario"))
    private Usuario movimentacaoUsuario;

    @Column(name = Coluna.QUANTIDADE, nullable = false)
    private Long quantidadeMovimentada;

    @Temporal(TemporalType.DATE)
    @Column(name = Coluna.DATA_MOVIMENTACAO, nullable = false)
    private LocalDate movimentacaoData;

    @Column(name = Coluna.ACAO, nullable = false)
    private ACAO movimentacaoAcao;

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
