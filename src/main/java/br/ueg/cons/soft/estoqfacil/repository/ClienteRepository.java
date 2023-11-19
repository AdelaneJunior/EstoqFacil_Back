package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>, JpaSpecificationExecutor<Cliente> {

}
