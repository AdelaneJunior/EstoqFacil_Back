package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Override
    @Query("select  c from Categoria c " +
            "inner join fetch c.usuario " +
            "where c.codigo = :codigo")
    Optional<Categoria> findById(@Param("codigo") Long codigo);

    @Query("select  c from Categoria c " +
            "where c.codigo = :codigo")
    Optional<Categoria> findByIdSemFetch(@Param("codigo") Long codigo);
}
