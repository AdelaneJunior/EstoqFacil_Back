package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select p from Produto p where p.codigo = :codigo")
    Optional<Produto> findById(@Param("codigo")Long codigo);


    @Query("select p from Produto p " +
            "inner join fetch  p.categoria c " +
            "inner join fetch p.usuario u " +
            "where p.codigo = :codigo")
    Optional<Produto> findByIdFetchTudo(@Param("codigo")Long codigo);

    Optional<Produto> findProdutoByCodigoBarras(long codigoBarras);
}
