package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.FuncionarioDTO;
import br.ueg.cons.soft.estoqfacil.mapper.impl.FuncionarioMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.service.impl.FuncionarioServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/${app.api.version}/funcionario")
public class FuncionarioController extends CrudController<Funcionario, FuncionarioDTO, Long, FuncionarioMapperImpl, FuncionarioServiceImpl> {

}
