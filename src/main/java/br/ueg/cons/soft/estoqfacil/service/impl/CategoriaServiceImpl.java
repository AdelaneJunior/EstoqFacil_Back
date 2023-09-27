package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.cons.soft.estoqfacil.repository.CategoriaRepository;
import br.ueg.cons.soft.estoqfacil.service.CategoriaService;
import br.ueg.prog.webi.api.exception.ApiMessageCode;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl extends BaseCrudService<Categoria, Long, CategoriaRepository>
        implements CategoriaService {

    @Override
    protected void prepararParaIncluir(Categoria entidade) {}
    @Override
    protected void validarDados(Categoria entidade) {}

    @Override
    protected void validarCamposObrigatorios(Categoria entidade) {}

}
