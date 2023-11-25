package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.CategoriaDTO;
import br.ueg.cons.soft.estoqfacil.dto.MovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.dto.RelatorioMovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.MovimentacaoMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import br.ueg.cons.soft.estoqfacil.service.impl.MovimentacaoServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import br.ueg.prog.webi.api.exception.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    @Operation(
            description = "Realiza busca paginada de acordo com o tamanho da pagina e a pagina",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Listagem do resultado",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema
                    )}
            )}
    )
    public ResponseEntity<List<RelatorioMovimentacaoDTO>> todasMovimentacoesProdutosEntradaSaida() {
        return ResponseEntity.ok(service.getAllMovimentacoesProdutoEntradaSaida());
    }

    @GetMapping("/geral{codigoProduto}")
    @Operation(
            description = "Listagem de movimentações de um produto",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Listagem do resultado",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema
                    )}
            ), @ApiResponse(
                    responseCode = "400",
                    description = "falha ao realizar a busca",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = MessageResponse.class
                            )
                    )}
            ), @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = MessageResponse.class
                            )
                    )}
            )}
    )
    public List<MovimentacaoDTO> todasMovimentacoesDeProdutoPorCodigo(@PathVariable(name = "codigoProduto") Long codigoProduto) {
        return mapper.toDTO(service.getTodasMovimentacoesDeProdutoPorCodigo(codigoProduto));
    }

    @GetMapping("geral/pagination/{codProduto}/{offset}/{pageSize}")
    @Operation(
            description = "Realiza busca paginada de acordo com o tamanho da pagina e a pagina",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Listagem do resultado",
                    content = {@Content(
                            mediaType = "application/json",
                            array = @ArraySchema
                    )}
            ), @ApiResponse(
                    responseCode = "400",
                    description = "falha ao realizar a busca",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = MessageResponse.class
                            )
                    )}
            ), @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = MessageResponse.class
                            )
                    )}
            )}
    )
    public ResponseEntity<List<MovimentacaoDTO>> listMovimentacoesPorProdutoWithPagination(@PathVariable Long codProduto,@PathVariable int offset, @PathVariable int pageSize){
        return ResponseEntity.ok(this.mapper.toDTO(this.service.getTodasMovimentacoesDeProdutoPorCodigoComPaginacao(codProduto,offset, pageSize)));
    }
    @GetMapping("/mapeamento")
    public RelatorioMovimentacaoDTO mapearRelatorio(){
        return new RelatorioMovimentacaoDTO();
    }
}
