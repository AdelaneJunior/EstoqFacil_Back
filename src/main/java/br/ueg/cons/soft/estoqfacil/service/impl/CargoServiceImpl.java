package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Cargo;
import br.ueg.cons.soft.estoqfacil.repository.CargoPermissaoRepository;
import br.ueg.cons.soft.estoqfacil.repository.CargoRepository;
import br.ueg.cons.soft.estoqfacil.service.CargoService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class CargoServiceImpl extends BaseCrudService<Cargo, Long, CargoRepository>
        implements CargoService {

    @Autowired
    private CargoPermissaoRepository cargoPermissaoRepository;

    @Override
    protected void prepararParaIncluir(Cargo cargo) {

    }

    @Override
    protected void validarDados(Cargo cargo) {
        tratarCargoPermissao(cargo);
    }

    private void tratarCargoPermissao(Cargo cargo) {

        if (Objects.nonNull(cargo.getPermissoes())) {
            cargo.getPermissoes().forEach(cargoPermissao -> {

                if (Objects.isNull(cargoPermissao.getCargo())) {
                    cargoPermissao.setCargo(new Cargo());
                    cargoPermissao.getCargo().setCodigo(cargo.getCodigo());
                    cargoPermissao.getCargo().setNome(cargo.getNome());
                }

            });
        }
    }

    @Override
    protected void validarCamposObrigatorios(Cargo entidade) {

    }

    @Override
    public List<Cargo> listarTodos() {
        return repository.findAll();
    }
}
