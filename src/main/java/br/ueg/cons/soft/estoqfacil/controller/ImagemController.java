package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.service.impl.ImagemServiceImpl;
import br.ueg.prog.webi.api.exception.MessageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/${app.api.version}/imagem")
public class ImagemController {

    @Autowired
    private ImagemServiceImpl service;

    @Operation(
            description = "upload de imagem",
            responses = {@ApiResponse(
                    responseCode = "200",
                    description = "Media geral do Jogo",
                    content = {@Content(
                            mediaType = "application/json"
                    )}
            ), @ApiResponse(
                    responseCode = "404",
                    description = "Registro não encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    implementation = MessageResponse.class
                            )
                    )}
            )})
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/upload")
    public Long uploadImagem(@RequestParam MultipartFile imagemASalvar) throws IOException {

        return this.service.incluir(imagemASalvar);

    }
    @GetMapping
    public String obterPeloId(Long requestID){
        return this.service.obterPeloId(requestID).getPathReference();
    }

}
