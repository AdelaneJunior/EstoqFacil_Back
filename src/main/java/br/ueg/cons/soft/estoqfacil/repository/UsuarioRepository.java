package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.model.Produto;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, JpaSpecificationExecutor<Usuario> {


    @Query("select u  from Usuario  u " +
            "inner join fetch u.funcionario f " +
            "inner join fetch f.cargo c " +
            "inner join fetch c.permissoes " +
            "where u.funcionario.pessoa.email = :usuarioEmail")
    Optional<Usuario> findUsuarioByFuncionario_Pessoa_Email(String usuarioEmail);

    @Query("select u from Usuario u " +
            "inner join fetch u.funcionario f " +
            "inner join fetch f.cargo c " +
            "inner join fetch c.permissoes ")
    List<Usuario> findAll();

    @Query(value = "select * from usuario limit :pageSize offset :pagina", nativeQuery = true)
    List<Usuario> findUsuariosWithPagination(@Param("pagina") int offset, @Param("pageSize") int pageSize);

    @Query(value = "select count(*) from usuario ", nativeQuery = true)
    Integer countAll();
}
