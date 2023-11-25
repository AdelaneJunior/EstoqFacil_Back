package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, String>, JpaSpecificationExecutor<Funcionario> {

    @Query(value = "select * from funcionario limit :pageSize offset :pagina", nativeQuery = true)
    List<Funcionario> findFuncionarioWithPagination(@Param("pagina") int offset, @Param("pageSize") int pageSize);

    @Query(value = "select count(*) from funcionario ", nativeQuery = true)
    Integer countAll();
}
