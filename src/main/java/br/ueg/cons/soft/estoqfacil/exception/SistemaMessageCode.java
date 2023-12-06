package br.ueg.cons.soft.estoqfacil.exception;

import br.ueg.prog.webi.api.exception.MessageCode;

public enum SistemaMessageCode implements MessageCode {
    ERRO_CODIGO_BARRA("MSG-005", 400),
    ERRO_NUMERO_NEGATIVO("MSG-006", 400),
    ERRO_CPF_INVALIDO("MSG-007", 400),
    ERRO_EMAIL_INVALIDO("MSG-008", 400),
    ERRO_CODIGO_BARRA_ZERADO("MSG-010", 400),
    ERRO_TELEFONE_INVALIDO("MSG-009", 400),

    ERRO_CATEGORIA_UTILIZADA("MSG-011", 400),
    ERRO_PRODUTO_MOVIMENTACAO("MSG-012", 400),
    ERRO_FUNCIONARIO_CARGO("MSG-013", 400),
    ERRO_USUARIO_COM_LOGIN("MSG-014", 400),
    ERRO_CLIENTE_DUPLICADO("MSG-015", 400),
    ERRO_FUNCIONARIO_DUPLICADO("MSG-016", 400),

    ERRO_SENHA_INVALIDA("MSG-017", 400);

    private final String code;
    private final Integer status;
    SistemaMessageCode(final String code, final Integer status) {
        this.code = code;
        this.status = status;
    }
    public String getCode() { return code; }
    public Integer getStatus() { return status; }
    @Override
    public String toString() { return code; }
}
