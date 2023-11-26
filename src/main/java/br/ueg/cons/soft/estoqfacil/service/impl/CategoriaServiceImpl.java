package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.repository.CategoriaRepository;
import br.ueg.cons.soft.estoqfacil.repository.UsuarioRepository;
import br.ueg.cons.soft.estoqfacil.service.CategoriaService;
import br.ueg.prog.webi.api.exception.ApiMessageCode;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoriaServiceImpl extends BaseCrudService<Categoria, Long, CategoriaRepository>
        implements CategoriaService {

    private final UsuarioRepository usuarioRepository;

    public CategoriaServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void prepararParaIncluir(Categoria entidade) {}
    @Override
    protected void validarDados(Categoria entidade) {}


    private void tratarUsuarioCategoria(Categoria categoria){

        if (Objects.nonNull(categoria.getUsuario())) {
            categoria.setUsuario(usuarioRepository.findById(categoria.getUsuario().getCodigo()).orElseThrow());
        }

    }
    @Override
    protected void validarCamposObrigatorios(Categoria entidade) {}

    @Override
    public Categoria incluir(Categoria modelo) {
        tratarUsuarioCategoria(modelo);
        return super.incluir(modelo);
    }
}
