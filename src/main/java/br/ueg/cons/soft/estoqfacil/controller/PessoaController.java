package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.PessoaDTO;
import br.ueg.cons.soft.estoqfacil.mapper.impl.PessoaMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Pessoa;
import br.ueg.cons.soft.estoqfacil.service.impl.PessoaServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/${app.api.version}/pessoa")
public class PessoaController extends CrudController<Pessoa, PessoaDTO, Long, PessoaMapperImpl, PessoaServiceImpl> {

}
