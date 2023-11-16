package br.ueg.cons.soft.estoqfacil.enums.converter;

import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import br.ueg.cons.soft.estoqfacil.enums.TipoMovimentacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoMovimentacaoConverter implements AttributeConverter<TipoMovimentacao, String> {

    @Override
    public String convertToDatabaseColumn(TipoMovimentacao tipo) {
        return tipo != null ? tipo.getId() : null;
    }

    @Override
    public TipoMovimentacao convertToEntityAttribute(String id) {
        return TipoMovimentacao.getById(id);
    }
}
