package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Permissao;
import br.ueg.cons.soft.estoqfacil.repository.PermissaoRepository;
import br.ueg.cons.soft.estoqfacil.service.PermissaoService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class PermissaoServiceImpl extends BaseCrudService<Permissao, Long, PermissaoRepository>
        implements PermissaoService {

    @Override
    protected void prepararParaIncluir(Permissao entidade) {

    }

    @Override
    protected void validarDados(Permissao entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Permissao entidade) {

    }
}
