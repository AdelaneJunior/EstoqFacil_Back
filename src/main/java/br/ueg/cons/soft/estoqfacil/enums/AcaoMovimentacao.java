package br.ueg.cons.soft.estoqfacil.enums;

import java.util.Arrays;

public enum AcaoMovimentacao {

    VENDA("V", "Venda",TipoMovimentacao.SAIDA),
    COMPRA("C", "Compra", TipoMovimentacao.ENTRADA),
    DEVOLUCAO_DO_CLIENTE("DC", "Devolução do Cliente", TipoMovimentacao.ENTRADA),
    DEVOLUCAO_AO_FORNECEDOR("DF", "Devolução ao fornecedor", TipoMovimentacao.SAIDA),
    PRODUTO_QUEBRADO("PQ", "Produto Quebrado", TipoMovimentacao.SAIDA);

    private final String id;

    private final String descricao;

    private final TipoMovimentacao tipo;

    /**
     * Construtor da classe.
     *
     * @param id        -
     * @param descricao -
     * @param tipo
     */
    AcaoMovimentacao(final String id, final String descricao, final TipoMovimentacao tipo) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    public TipoMovimentacao getTipoMovimentacao(){
        return this.tipo;
    }
    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a instância de {@link AcaoMovimentacao} conforme o 'id' informado.
     *
     * @param id -
     * @return -
     */
    public static AcaoMovimentacao getById(final String id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id)).findFirst().orElse(null);
    }
}