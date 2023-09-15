package br.ueg.cons.soft.estoqfacil.teste;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.util.EmailSender;
import br.ueg.cons.soft.estoqfacil.util.PdfCreator;

import java.util.ArrayList;
import java.util.List;

public class Teste {
    public static void main(String[] args) {
        List<ProdutoDTO> produtoDTOList = new ArrayList<>();
        produtoDTOList.add(
                ProdutoDTO.builder()
                        .codigo(1l)
                        .nome("Iphone 15")
                        .marca("Apple")
                        .preco(10500.00)
                        .quantidade(14l)
                        .build()
        );
        produtoDTOList.add(
                ProdutoDTO.builder()
                        .codigo(2l)
                        .nome("Iphone 14")
                        .marca("Apple")
                        .preco(9500.00)
                        .quantidade(15l)
                        .build()
        );
        produtoDTOList.add(
                ProdutoDTO.builder()
                        .codigo(3l)
                        .nome("Iphone 13")
                        .marca("Apple")
                        .preco(8500.00)
                        .quantidade(16l)
                        .build()
        );
        PdfCreator.criaPdf(produtoDTOList);
        EmailSender.enviaEmail("adelane.junior13@gmail.com");
    }
}
