package br.ueg.cons.soft.estoqfacil.controller;

import br.ueg.cons.soft.estoqfacil.dto.CargoDTO;
import br.ueg.cons.soft.estoqfacil.mapper.CargoMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Cargo;
import br.ueg.cons.soft.estoqfacil.service.impl.CargoServiceImpl;
import br.ueg.prog.webi.api.controller.CrudController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/${app.api.version}/cargo")
public class CargoController extends CrudController<Cargo, CargoDTO, Long, CargoMapperImpl, CargoServiceImpl> {
}
