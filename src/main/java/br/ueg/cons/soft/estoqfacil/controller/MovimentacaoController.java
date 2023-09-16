package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.MovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.MovimentacaoMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.cons.soft.estoqfacil.service.impl.MovimentacaoServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/${app.api.version}/movimentacao")
public class MovimentacaoController extends
        CrudController<Movimentacao, MovimentacaoDTO, Long, MovimentacaoMapperImpl, MovimentacaoServiceImpl> {

}
