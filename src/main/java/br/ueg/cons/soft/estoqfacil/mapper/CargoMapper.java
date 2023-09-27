package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.CargoDTO;
import br.ueg.cons.soft.estoqfacil.model.Cargo;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CargoMapper extends BaseMapper<Cargo, CargoDTO> {
}
