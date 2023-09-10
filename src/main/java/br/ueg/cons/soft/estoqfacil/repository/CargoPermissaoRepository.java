package br.ueg.cons.soft.estoqfacil.repository;

import br.ueg.cons.soft.estoqfacil.model.CargoPermissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoPermissaoRepository extends JpaRepository<CargoPermissao, Long> {



}
