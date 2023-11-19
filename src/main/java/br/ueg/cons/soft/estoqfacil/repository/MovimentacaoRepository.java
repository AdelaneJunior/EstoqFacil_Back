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
    @Query("SELECT COALESCE(SUM(quantidade), 0) FROM Movimentacao WHERE tipo = TipoMovimentacao.SAIDA AND produto.codigo = :idProduto")
    Long totalProdutosSaida(long idProduto);

    @Query("SELECT COALESCE(SUM(quantidade), 0) FROM Movimentacao WHERE tipo = TipoMovimentacao.ENTRADA AND produto.codigo = :idProduto")
    Long totalProdutosEntrada(long idProduto);

    @Query("SELECT COALESCE(m.preco, 0)  FROM Movimentacao m WHERE m.codigo = :idProduto AND m.data = (SELECT MAX(m2.data) FROM Movimentacao m2 WHERE m2.codigo = :idProduto)")
    Double findPrecoValueByLastDataAndProduto(long idProduto);
    @Query("SELECT COALESCE(m.custo, 0)  FROM Movimentacao m WHERE m.codigo = :idProduto AND m.data = (SELECT MAX(m2.data) FROM Movimentacao m2 WHERE m2.codigo = :idProduto)")
    Double findCustoValueByLastDataAndProduto(long idProduto);
    */


    @Query("SELECT COALESCE(SUM(CASE WHEN tipo = TipoMovimentacao.SAIDA THEN quantidade ELSE 0 END), 0) FROM Movimentacao WHERE produto.codigo = :idProduto")
    long totalProdutosSaida(long idProduto);

    @Query("SELECT COALESCE(SUM(CASE WHEN tipo = TipoMovimentacao.ENTRADA THEN quantidade ELSE 0 END), 0) FROM Movimentacao WHERE produto.codigo = :idProduto")
    long totalProdutosEntrada(long idProduto);

    @Query("SELECT COALESCE(MAX(CASE WHEN m.codigo = :idProduto THEN m.preco END), 0) FROM Movimentacao m WHERE m.data = (SELECT MAX(m2.data) FROM Movimentacao m2 WHERE m2.codigo = :idProduto)")
    double findPrecoValueByLastDataAndProduto(long idProduto);

    @Query("SELECT COALESCE(MAX(CASE WHEN m.codigo = :idProduto THEN m.custo END), 0) FROM Movimentacao m WHERE m.data = (SELECT MAX(m2.data) FROM Movimentacao m2 WHERE m2.codigo = :idProduto)")
    double findCustoValueByLastDataAndProduto(long idProduto);
}
