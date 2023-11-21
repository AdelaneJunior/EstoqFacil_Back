package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    @Query("SELECT COALESCE(SUM(quantidade),0) FROM Movimentacao WHERE tipo = TipoMovimentacao.SAIDA AND produto.codigo = :idProduto")
    Long totalProdutosSaida(long idProduto);

    @Query("SELECT COALESCE(SUM(quantidade),0) FROM Movimentacao WHERE tipo = TipoMovimentacao.ENTRADA AND produto.codigo = :idProduto")
    Long totalProdutosEntrada(long idProduto);

    @Query("SELECT COALESCE(SUM(preco),0)  FROM Movimentacao WHERE produto.codigo = :idProduto AND data = (SELECT MAX(data) FROM Movimentacao WHERE produto.codigo = :idProduto)")
    BigDecimal findPrecoValueByLastDataAndProduto(long idProduto);
    @Query("SELECT COALESCE(SUM(custo),0) FROM Movimentacao WHERE produto.codigo = :idProduto AND data = (SELECT MAX(data) FROM Movimentacao WHERE produto.codigo = :idProduto)")
    BigDecimal findCustoValueByLastDataAndProduto(long idProduto);

}