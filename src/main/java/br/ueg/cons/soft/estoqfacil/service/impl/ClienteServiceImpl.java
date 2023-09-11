package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.model.pks.PkCliente;
import br.ueg.cons.soft.estoqfacil.repository.ClienteRepository;
import br.ueg.cons.soft.estoqfacil.service.ClienteService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl extends BaseCrudService<Cliente, PkCliente,ClienteRepository>
        implements ClienteService {

    @Override
    protected void prepararParaIncluir(Cliente entidade) {

    }

    @Override
    protected void validarDados(Cliente entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Cliente entidade) {

    }
}
