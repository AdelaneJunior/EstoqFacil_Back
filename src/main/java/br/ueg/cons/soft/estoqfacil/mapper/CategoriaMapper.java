package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.CategoriaDTO;
import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDTO> {
}

