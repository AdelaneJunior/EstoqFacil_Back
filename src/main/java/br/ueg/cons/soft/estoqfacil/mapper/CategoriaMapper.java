package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.CategoriaDTO;
import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDTO> {

    @Mapping(source = "usuarioId", target = "usuario.codigo")
    Categoria toModelo(CategoriaDTO categoriaDTO);

    @Mapping(source = "usuario.codigo", target = "usuarioId")
    CategoriaDTO toDTO(Categoria categoria);
}

