package br.ueg.cons.soft.estoqfacil.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
public class PdfCreator {
    private static final String PATH = "listaProdutos.pdf";

    public static Document criaPdf(List<ProdutoDTO> produtos) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(PATH));
            document.open();

            document.add(new Paragraph("Lista de Produtos",
                    new Font(Font.FontFamily.HELVETICA,14, 1)));
            listaProdutos(document, produtos);
        }
        catch(Exception de) {
            System.err.println(de.getMessage());
        }
        document.close();
        return document;
    }

    private static void insereImagem(String path, Document pdf)  {
        Image figura = null;
        try {
            figura = Image.getInstance(path);
            figura.scaleAbsolute(100,100);
            pdf.add(new Paragraph(50));
            pdf.add(figura);
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    private static void listaProduto(Document pdf, ProdutoDTO produto) throws DocumentException {
        insereImagem(produto.getImagemPathReference(), pdf);
        pdf.add(new Paragraph("Id do produto: " + produto.getCodigo()));
        pdf.add(new Paragraph("Nome: " + produto.getNome()));
        pdf.add(new Paragraph("Marca: " + produto.getMarca()));
        pdf.add(new Paragraph("Preço: R$ " + produto.getPreco()));
        pdf.add(new Paragraph("Quantidade: " + produto.getQuantidade()));
    }

    private static void listaProdutos(Document pdf, List<ProdutoDTO> produtos) throws DocumentException {
        for (ProdutoDTO produto : produtos) {
            listaProduto(pdf, produto);
        }
    }
}
