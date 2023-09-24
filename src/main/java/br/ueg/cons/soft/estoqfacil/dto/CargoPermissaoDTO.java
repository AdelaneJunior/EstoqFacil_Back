package br.ueg.cons.soft.estoqfacil.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CargoPermissaoDTO {

    private Long cargoCodigo;

    private Long permissaoCodigo;

    private String permissaoRole;
}
