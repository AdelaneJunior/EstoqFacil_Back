package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.PermissaoDTO;
import br.ueg.cons.soft.estoqfacil.model.Permissao;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissaoMapper extends BaseMapper<Permissao, PermissaoDTO> {
}
