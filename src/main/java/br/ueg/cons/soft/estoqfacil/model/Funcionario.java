package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.interfaces.ISearchFieldData;
import br.ueg.prog.webi.api.model.BaseEntidade;
import br.ueg.prog.webi.api.model.annotation.Searchable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Funcionario.NOME_TABELA)
@Getter
@Setter
public class Funcionario extends BaseEntidade<String> implements ISearchFieldData<String> {

    public static final String NOME_TABELA = "funcionario";

    @Override
    public String getDescription() {
        return this.getPessoa().getNome();
    }

    public static final class Coluna {

        public static final String CPF = "func_cpf";

        public static final String CARGO = "func_cargo";

    }

    @Id
    @Column(name = Coluna.CPF)
    @Searchable(label = "CPF")
    private String cpf;

    public String getId(){
        return this.cpf;
    }

    @MapsId
    @OneToOne(optional = false)
    @JoinColumn(name = Coluna.CPF,
            referencedColumnName = Pessoa.Coluna.CPF,
            foreignKey = @ForeignKey(name = "fk_funcionario_pessoa"))
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = Coluna.CARGO, nullable = false,
            referencedColumnName = Cargo.Coluna.ID,
            foreignKey = @ForeignKey(name = "fk_funcionario_cargo"))
    private Cargo cargo;


    public void setCpf(String cpf) {
        this.cpf = cpf;
        if(Objects.isNull(this.getPessoa())){
            this.setPessoa(Pessoa.builder().build());
        }
        this.getPessoa().setCpf(cpf);
    }
}
