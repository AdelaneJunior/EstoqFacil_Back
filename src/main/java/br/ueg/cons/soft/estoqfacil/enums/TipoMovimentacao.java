package br.ueg.cons.soft.estoqfacil.enums;

import java.util.Arrays;

public enum TipoMovimentacao {
    ENTRADA("E","Entrada"),
    SAIDA("S","Saida");

    private final String id;
    private final String descricao;

    TipoMovimentacao(final String id, final String descricao){
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
    public static TipoMovimentacao getById(final String id) {
        return Arrays.stream(values()).filter(value -> value.getId().equals(id)).findFirst().orElse(null);
    }

}
