package br.ueg.cons.soft.estoqfacil.dto;

import br.ueg.cons.soft.estoqfacil.controller.PessoaController;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.Pessoa;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO extends PessoaDTO{

    private Long codigo;

    private Long pessoaID;

    private String cargo;

}
