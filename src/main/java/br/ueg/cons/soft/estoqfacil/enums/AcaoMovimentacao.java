package br.ueg.cons.soft.estoqfacil.enums;

import java.util.Arrays;

public enum AcaoMovimentacao {

    VENDA("V", "Venda"),
    COMPRA("C", "Compra"),
    DEVOLUCAO_DO_CLIENTE("DC", "Devolução do Cliente"),

    DEVOLUCAO_AO_FORNECEDOR("DF", "Devolução ao fornecedor"),
    PRODUTO_QUEBRADO("PQ", "Produto Quebrado");

    private final String id;

    private final String descricao;

    /**
     * Construtor da classe.
     *
     * @param id        -
     * @param descricao -
     */
    AcaoMovimentacao(final String id, final String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
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
