package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.ClienteDTO;
import br.ueg.cons.soft.estoqfacil.mapper.ClienteMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.service.impl.ClienteServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/${app.api.version}/cliente")
public class ClienteController extends
        CrudController<Cliente, ClienteDTO, Long, ClienteMapperImpl, ClienteServiceImpl> {

}
