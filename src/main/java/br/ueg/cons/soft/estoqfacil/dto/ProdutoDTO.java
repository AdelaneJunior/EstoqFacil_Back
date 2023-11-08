package br.ueg.cons.soft.estoqfacil.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.File;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Long codigo;

    private String nome;

    private String descricao;

    private String marca;

    private Long quantidade;

    private Double preco;

    private Double custo;

    private Long imagemId;

    private String imagem;

    private Long categoriaId;

    private String categoriaNome;

    private Long usuarioId;
}
