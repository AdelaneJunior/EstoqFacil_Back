package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.ImagemDTO;
import br.ueg.cons.soft.estoqfacil.model.Imagem;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImagemMapper extends BaseMapper<Imagem, ImagemDTO> {
}
