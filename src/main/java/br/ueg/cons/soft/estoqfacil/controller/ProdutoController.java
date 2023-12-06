package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.EnviaEmailDTO;
import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.exception.SistemaMessageCode;
import br.ueg.cons.soft.estoqfacil.mapper.ProdutoMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.service.impl.ProdutoServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import br.ueg.prog.webi.api.dto.SearchFieldValue;
import br.ueg.prog.webi.api.exception.ApiMessageCode;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.exception.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/${app.api.version}/produto")
public class ProdutoController extends
        CrudController<Produto, ProdutoDTO, Long, ProdutoMapperImpl, ProdutoServiceImpl> {

    @PostMapping("envia")
    @Operation(description = "Método utilizado para enviar lista de produtos no email", responses = {
            @ApiResponse(responseCode = "200", description = "Listagem geral",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "403", description = "Acesso negado",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de Negócio",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = MessageResponse.class)))
    }
    )
    public ResponseEntity<EnviaEmailDTO> enviaEmailComPdf(@RequestBody EnviaEmailDTO enviaEmail) {
        return ResponseEntity.ok(this.service.enviaEmailComPdf(enviaEmail));
    }

    @Override
    @PostMapping(
            path = {"/search-fields"}
    )
    @Operation(
            description = "Realiza a busca pelos valores dos campos informados",
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
    public ResponseEntity<List<ProdutoDTO>> searchFieldsAction(@RequestBody List<SearchFieldValue> searchFieldValues) {
        List<Produto> listSearchFields = this.service.searchFieldValues(searchFieldValues);
        if (listSearchFields.isEmpty()) {
            throw new BusinessException(ApiMessageCode.SEARCH_FIELDS_RESULT_NONE);
        } else {
            return ResponseEntity.ok(this.mapper.toDTO(this.service.preencherCamposLista(listSearchFields)));
        }
    }

   @Override
   @GetMapping(path = "/page")
   @Operation(description = "Listagem Geral paginada", responses = {
           @ApiResponse(responseCode = "200", description = "Listagem geral",
                   content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                           array = @ArraySchema())),
           @ApiResponse(responseCode = "404", description = "Registro não encontrado",
                   content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                           schema = @Schema(implementation = MessageResponse.class))),
           @ApiResponse(responseCode = "403", description = "Acesso negado",
                   content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                           schema = @Schema(implementation = MessageResponse.class))),
           @ApiResponse(responseCode = "400", description = "Erro de Negócio",
                   content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                           schema = @Schema(implementation = SistemaMessageCode.class)))
   })
   public ResponseEntity<Page<ProdutoDTO>> listAllPage(@PageableDefault(page = 0, size = 5)  Pageable page){
       Page<Produto> pageEntidade = service.listarTodosPage(page);
       this.service.preencherCamposLista(pageEntidade.getContent());
       return ResponseEntity.ok(mapPageEntityToDto(pageEntidade));
   }
}
