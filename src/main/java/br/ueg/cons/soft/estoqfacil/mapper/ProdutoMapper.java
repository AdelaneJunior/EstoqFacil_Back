package br.ueg.cons.soft.estoqfacil.mapper;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.prog.webi.api.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


public interface ProdutoMapper extends BaseMapper<Produto, ProdutoDTO> {

    ProdutoDTO toDTO(Produto produto);

    Produto toModelo(ProdutoDTO produtoDTO);
}

