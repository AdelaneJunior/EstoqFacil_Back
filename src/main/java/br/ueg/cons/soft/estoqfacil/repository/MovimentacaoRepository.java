package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    /* Ask later
    @Query(
            value = "SELECT (" +
                    "   (SELECT SUM(m.movt_quantidade) FROM movimentacao m WHERE m.movt_tipo = 'S' AND m.movt_usuario = :idProduto) " +
                    " - " +
                    "   (SELECT SUM(m.movt_quantidade) FROM movimentacao m WHERE m.movt_tipo = 'E' AND m.movt_usuario = :idProduto)" +
                    ") as total " +
                    "FROM movimentacao"
    )
    long findCurrentProductQuantity(long idProduto);

     */
    /*
    @Query("SELECT IFNULL(SUM(movt_quantidade),0) FROM Movimentacao WHERE movt_tipo = 'S' AND movt_produto = :idProduto")
    long totalProdutosSaida(long idProduto);

    @Query("SELECT IFNULL(SUM(movt_quantidade),0) FROM Movimentacao WHERE movt_tipo = 'E' AND movt_produto = :idProduto")
    long totalProdutosEntrada(long idProduto);
    */

}
