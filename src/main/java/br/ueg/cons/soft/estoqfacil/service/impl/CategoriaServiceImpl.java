package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Categoria;
import br.ueg.cons.soft.estoqfacil.repository.CategoriaRepository;
import br.ueg.cons.soft.estoqfacil.repository.UsuarioRepository;
import br.ueg.cons.soft.estoqfacil.service.CategoriaService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static br.ueg.cons.soft.estoqfacil.exception.SistemaMessageCode.ERRO_CATEGORIA_UTILIZADA;

@Service
public class CategoriaServiceImpl extends BaseCrudService<Categoria, Long, CategoriaRepository>
        implements CategoriaService {

    private final UsuarioRepository usuarioRepository;

    public CategoriaServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void prepararParaIncluir(Categoria entidade) {
    }

    @Override
    protected void validarDados(Categoria entidade) {
    }


    private void tratarUsuarioCategoria(Categoria categoria) {

        if (Objects.nonNull(categoria.getUsuario())) {
            categoria.setUsuario(usuarioRepository.findById(categoria.getUsuario().getCodigo()).orElseThrow());
        }

    }

    @Override
    protected void validarCamposObrigatorios(Categoria entidade) {
    }

    @Override
    public Categoria incluir(Categoria modelo) {
        tratarUsuarioCategoria(modelo);
        return super.incluir(modelo);
    }

    @Override
    public Categoria excluir(Long id) {

        Categoria excluir = null;
        try {
            excluir = super.excluir(id);
        } catch (DataIntegrityViolationException e) {
                throw new BusinessException(ERRO_CATEGORIA_UTILIZADA);
        }

        return excluir;
    }
}
