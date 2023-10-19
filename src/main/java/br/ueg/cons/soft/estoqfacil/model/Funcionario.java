package br.ueg.cons.soft.estoqfacil.model;

import br.ueg.prog.webi.api.model.BaseEntidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = Funcionario.NOME_TABELA)
public class Funcionario extends BaseEntidade<String> {

    public static final String NOME_TABELA = "funcionario";

    public static final class Coluna {

        public static final String CPF = "func_cpf";

        public static final String CARGO = "func_cargo";

    }

    @Id
    @Column(name = Coluna.CPF)
    private String cpf;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
        if(Objects.isNull(this.getPessoa())){
            this.setPessoa(Pessoa.builder().build());
        }
        this.getPessoa().setCpf(cpf);
    }
}
