package br.ueg.cons.soft.estoqfacil.api.converters;

import br.ueg.cons.soft.estoqfacil.service.CategoriaService;
import br.ueg.prog.webi.api.converters.LongConverter;
import br.ueg.prog.webi.api.interfaces.IConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CategoriaConverter implements IConverter {
    @Autowired
    CategoriaService categoriaService;
    private static final Logger LOG =
            LoggerFactory.getLogger(LongConverter.class);
    @Override
    public Object converter(String value) {
        if(Objects.nonNull(value)){
            try {
                Long pk = Long.valueOf(value);
                return categoriaService.obterPeloId(pk);
            }catch (Exception e){
                LOG.error("Erro ao Converter valor(%s) para StatusAtivoInativo",value);
            }
        }
        return null;
    }
}
