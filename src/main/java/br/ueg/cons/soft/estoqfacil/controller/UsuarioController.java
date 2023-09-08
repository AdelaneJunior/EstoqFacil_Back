package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.UsuarioDTO;
import br.ueg.cons.soft.estoqfacil.mapper.UsuarioMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import br.ueg.cons.soft.estoqfacil.service.impl.UsuarioServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/${app.api.version}/usuario")
public class UsuarioController extends CrudController<Usuario, UsuarioDTO, Long, UsuarioMapperImpl, UsuarioServiceImpl> {

    @Override
    @PostMapping("/singup")
    public ResponseEntity<UsuarioDTO> incluir(UsuarioDTO usuarioDTO) {

        Usuario usuarioParaIncluir = mapper.toModelo(usuarioDTO);
        UsuarioDTO novoUser = mapper.toDTO(service.incluir(usuarioParaIncluir));
        return ResponseEntity.ok(novoUser);

    }

}
