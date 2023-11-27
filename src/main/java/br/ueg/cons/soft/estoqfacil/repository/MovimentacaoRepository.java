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

    @Query("SELECT COALESCE(SUM(quantidade),0) FROM Movimentacao WHERE tipo = TipoMovimentacao.SAIDA AND produto.codigo = :idProduto")
    Long totalProdutosSaida(long idProduto);

    @Query("SELECT COALESCE(SUM(quantidade),0) FROM Movimentacao WHERE tipo = TipoMovimentacao.ENTRADA AND produto.codigo = :idProduto")
    Long totalProdutosEntrada(long idProduto);

    @Query("SELECT COALESCE(SUM(preco),0)  FROM Movimentacao WHERE produto.codigo = :idProduto AND codigo = (SELECT MAX(codigo) FROM Movimentacao WHERE produto.codigo = :idProduto)")
    BigDecimal findPrecoValueByLastDataAndProduto(long idProduto);
    @Query("SELECT COALESCE(SUM(custo),0) FROM Movimentacao WHERE produto.codigo = :idProduto AND codigo = (SELECT MAX(codigo) FROM Movimentacao WHERE produto.codigo = :idProduto)")
    BigDecimal findCustoValueByLastDataAndProduto(long idProduto);

    @Query("SELECT new br.ueg.cons.soft.estoqfacil.dto.RelatorioMovimentacaoDTO(" +
            "m.produto.codigo, p.nome, sum(case when m.tipo = 'S' then  m.quantidade else 0 end) as totalSaida," +
            "sum(case when m.tipo = 'E' then  m.quantidade else 0 end) as totalEntrada, sum(m.preco)" +
            ")" +
            "from Movimentacao m inner join fetch Produto p on p.codigo = m.produto.codigo " +
            "group by m.produto.codigo, p.nome " +
            "order by m.produto.codigo")
    List<RelatorioMovimentacaoDTO> getAllMovimentacoesProdutoEntradaSaida();

    List<Movimentacao> findAllByProdutoId(Long produto_id);

    @Query(value = "select * from movimentacao m " +
            "inner join produto p on p.prod_codigo = m.movt_produto and p.prod_codigo = :codProduto " +
            "limit :pageSize offset :pagina", nativeQuery = true)
    List<Movimentacao> getTodasMovimentacoesDeProdutoPorCodigoComPaginacao(@Param("codProduto") Long codProduto,@Param("pagina") int offset, @Param("pageSize") int pageSize);
}