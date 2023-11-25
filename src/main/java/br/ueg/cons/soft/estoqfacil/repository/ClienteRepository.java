package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>, JpaSpecificationExecutor<Cliente> {
    @Query(value = "select * from cliente limit :pageSize offset :pagina", nativeQuery = true)
    List<Cliente> findClienteWithPagination(@Param("pagina") int offset, @Param("pageSize") int pageSize);

    @Query(value = "select count(*) from cliente ", nativeQuery = true)
    Integer countAll();
}
