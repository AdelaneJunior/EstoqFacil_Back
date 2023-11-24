package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.MovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.dto.RelatorioMovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.MovimentacaoMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.cons.soft.estoqfacil.service.impl.MovimentacaoServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/${app.api.version}/movimentacao")
public class MovimentacaoController extends
        CrudController<Movimentacao, MovimentacaoDTO, Long, MovimentacaoMapperImpl, MovimentacaoServiceImpl> {

    @GetMapping("/geral")
    public List<RelatorioMovimentacaoDTO> todasMovimentacoesProdutosEntradaSaida(){
        return service.getAllMovimentacoesProdutoEntradaSaida();
    }

    @GetMapping("/geral{codigoProduto}")
    public List<MovimentacaoDTO> todasMovimentacoesProdutosEntradaSaida(@PathVariable(name = "codigoProduto") Long codigoProduto){
        return mapper.toDTO(service.getTodasMovimentacoesDeProdutoPorCodigo(codigoProduto));
    }

}
