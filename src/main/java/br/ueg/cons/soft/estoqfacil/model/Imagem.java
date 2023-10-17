package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
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

        public static final String BLOB = "imag_blob";

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
    @Column(name = COLUNA.ID)
    private Long id;

    @Lob
    @Column(name = COLUNA.BLOB)
    private byte[] blob;
}

