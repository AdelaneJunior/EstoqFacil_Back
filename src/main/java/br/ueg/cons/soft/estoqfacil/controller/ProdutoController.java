package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.service.impl.ProdutoServiceImpl;
import br.ueg.cons.soft.estoqfacil.mapper.impl.ProdutoMapperImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/${app.api.version}/produto")
public class ProdutoController extends CrudController<Produto, ProdutoDTO, Long, ProdutoMapperImpl, ProdutoServiceImpl> {

}
