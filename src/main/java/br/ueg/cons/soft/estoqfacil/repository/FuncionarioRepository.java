package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.pks.PkFuncionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, PkFuncionario> {

    @Query("select f from Funcionario f " +
            "inner join fetch f.cargo c " +
            "inner join fetch c.permissoes cp " +
            "inner join fetch f.pessoa p ")
    List<Funcionario> findAll();


    @Query("select f from Funcionario f " +
            "inner join fetch f.cargo c " +
            "inner join fetch f.pessoa ")
    List<Funcionario> listaTodosSemFetch();
}
