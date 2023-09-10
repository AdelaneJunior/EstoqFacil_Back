package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.CargoPermissaoDTO;
import br.ueg.cons.soft.estoqfacil.model.CargoPermissao;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CargoPermissaoMapper extends BaseMapper<CargoPermissao, CargoPermissaoDTO>{

    @Mapping(source = "cargo.codigo", target = "cargoCodigo")
    @Mapping(source = "permissao.codigo", target = "permissaoCodigo")
    @Mapping(source = "permissao.role", target = "permissaoRole")
    CargoPermissaoDTO toDTO(CargoPermissao cargoPermissao);

    @Mapping(source = "cargoCodigo", target = "cargo.codigo")
    @Mapping(source = "permissaoCodigo", target = "permissao.codigo")
    @Mapping(source = "permissaoRole", target = "permissao.role")
    CargoPermissao toModelo(CargoPermissaoDTO cargoPermissaoDTO);
}
