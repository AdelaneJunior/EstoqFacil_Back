package br.ueg.cons.soft.estoqfacil.repository;

import br.ueg.cons.soft.estoqfacil.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    @Query("select cargo from Cargo cargo " +
            "inner join fetch  cargo.permissoes permissoes " +
            "where cargo.codigo = :codigo" )
    Optional<Cargo> findByIdFetch(@Param("codigo") Long codigo);

    @Query("select c from Cargo c " +
            "inner join fetch c.permissoes p")
    List<Cargo> findAll();


}
