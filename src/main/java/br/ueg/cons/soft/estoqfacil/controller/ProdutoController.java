package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.EnviaEmailDTO;
import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.ProdutoMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.service.impl.ProdutoServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import br.ueg.prog.webi.api.exception.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/${app.api.version}/produto")
public class ProdutoController extends
        CrudController<Produto, ProdutoDTO, Long, ProdutoMapperImpl, ProdutoServiceImpl> {

    @PostMapping("envia")
    @Operation(
            description = "Método utilizado para realizar a inclusão de um entidade",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Entidade Incluida"
            ), @ApiResponse(
                    responseCode = "403",
                    description = "Acesso negado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = MessageResponse.class
                            )
                    )}
            ), @ApiResponse(
                    responseCode = "400",
                    description = "Erro de Negócio",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = MessageResponse.class
                            )
                    )}
            )}
    )
    public boolean enviaEmail(@RequestBody EnviaEmailDTO enviaEmail) {
        return this.service.enviaLista(enviaEmail.email, enviaEmail.listaProdutos);
    }




}
