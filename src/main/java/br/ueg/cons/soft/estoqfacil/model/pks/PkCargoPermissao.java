package br.ueg.cons.soft.estoqfacil.model.pks;

import br.ueg.prog.webi.api.model.annotation.PkComposite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@PkComposite
@Data
public class PkCargoPermissao implements Serializable {

    private Long cargo;
    private Long permissao;
}
