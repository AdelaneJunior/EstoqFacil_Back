package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.CargoPermissao;
import br.ueg.cons.soft.estoqfacil.model.pks.PkCargoPermissao;
import br.ueg.cons.soft.estoqfacil.repository.CargoPermissaoRepository;
import br.ueg.cons.soft.estoqfacil.service.CargoPermissaoService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.stereotype.Service;

@Service
public class CargoPermissaoServiceImpl extends BaseCrudService<CargoPermissao, PkCargoPermissao, CargoPermissaoRepository>
        implements CargoPermissaoService {


    @Override
    protected void prepararParaIncluir(CargoPermissao entidade) {

    }

    @Override
    protected void validarDados(CargoPermissao entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(CargoPermissao entidade) {

    }
}
