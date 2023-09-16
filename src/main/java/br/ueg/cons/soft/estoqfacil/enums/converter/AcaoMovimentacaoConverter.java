package br.ueg.cons.soft.estoqfacil.enums.converter;

import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Classe de conversão JPA referente ao enum {@link AcaoMovimentacao}.
 */
@Converter(autoApply = true)
public class AcaoMovimentacaoConverter implements AttributeConverter<AcaoMovimentacao, String> {
    @Override
    public String convertToDatabaseColumn(AcaoMovimentacao acao) {
        return acao != null ? acao.getId() : null;
    }

    @Override
    public AcaoMovimentacao convertToEntityAttribute(String id) {
        return AcaoMovimentacao.getById(id);
    }
}
