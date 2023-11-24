package br.ueg.cons.soft.estoqfacil.repository;


import br.ueg.cons.soft.estoqfacil.dto.RelatorioMovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

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


    @Query("SELECT SUM(quantidade) FROM Movimentacao WHERE tipo = TipoMovimentacao.SAIDA AND produto.codigo = :idProduto")
    Long totalProdutosSaida(long idProduto);

    @Query("SELECT SUM(quantidade) FROM Movimentacao WHERE tipo = TipoMovimentacao.ENTRADA AND produto.codigo = :idProduto")
    Long totalProdutosEntrada(long idProduto);

    @Query("SELECT preco  FROM Movimentacao WHERE produto.codigo = :idProduto AND data = (SELECT MAX(data) FROM Movimentacao WHERE produto.codigo = :idProduto)")
    BigDecimal findPrecoValueByLastDataAndProduto(long idProduto);
    @Query("SELECT custo FROM Movimentacao WHERE produto.codigo = :idProduto AND data = (SELECT MAX(data) FROM Movimentacao WHERE produto.codigo = :idProduto)")
    BigDecimal findCustoValueByLastDataAndProduto(long idProduto);

    @Query("SELECT new br.ueg.cons.soft.estoqfacil.dto.RelatorioMovimentacaoDTO(" +
            "m.produto.codigo, p.nome, sum(case when m.tipo = 'S' then  m.quantidade else 0 end) as totalSaida," +
            "sum(case when m.tipo = 'E' then  m.quantidade else 0 end) as totalEntrada , m.tipo " +
            ")" +
            "from Movimentacao m inner join fetch Produto p on p.codigo = m.produto.codigo " +
            "group by m.produto.codigo, m.tipo, p.nome " +
            "order by m.produto.codigo")
    List<RelatorioMovimentacaoDTO> getAllMovimentacoesProdutoEntradaSaida();

    List<Movimentacao> findAllByProdutoId(Long produto_id);
}