package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.IEntidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = Imagem.NOME_TABELA)
public class Imagem extends BaseEntidade<Long> {

    public static final String NOME_TABELA = "imagem";

    public static final class COLUNA {

        public static final String ID = "imag_codigo";
        public static final String PATH_ABSOLUTE = "imag_path_absolute";
        public static final String PATH_REFERENCE = "imag_path_reference";
        public static final String NOME = "imag_nome";
        public static final String TIPO = "imag_tipo";


    }

    @SequenceGenerator(
            name = "a_gerador_sequence",
            sequenceName = "imagem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "a_gerador_sequence"
    )

    @Id
    @Column(name = COLUNA.ID, nullable = false)
    private Long codigo;

    @Column(name = COLUNA.NOME, nullable = false)
    private String nome;

    @Column(name = COLUNA.TIPO, nullable = false)
    private String tipo;

    @Column(name = COLUNA.PATH_ABSOLUTE, nullable = false)
    private String pathAbsolute;

    @Column(name = COLUNA.PATH_REFERENCE, nullable = false)
    private String pathReference;

}

