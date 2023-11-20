package br.ueg.cons.soft.estoqfacil.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.model.Imagem;
import br.ueg.cons.soft.estoqfacil.repository.ImagemRepository;
import br.ueg.cons.soft.estoqfacil.service.ImagemService;
import br.ueg.cons.soft.estoqfacil.service.impl.ImagemServiceImpl;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PdfCreator {

    @Autowired
    ImagemService imagemService;

    private static final String PATH = "listaProdutos.pdf";

    public Document criaPdf(List<ProdutoDTO> produtos) {
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

    private void insereImagem(Imagem img, Document pdf) throws DocumentException, IOException {
        Image figura;
        figura = Image.getInstance(img.getBlob());
        figura.scaleAbsolute(100,100);
        pdf.add(new LineSeparator(1,100, new BaseColor(0,0,0), 0,-5));
        pdf.add(figura);
    }

    private void listaProduto(Document pdf, ProdutoDTO produto) throws DocumentException, IOException {
        Locale l = new Locale("pt", "BR");
        NumberFormat nf =  NumberFormat.getInstance(l);

        Imagem img = imagemService.obterPeloId(produto.getImagemId());
        insereImagem(img, pdf);

        pdf.add(new Paragraph("Id do produto: " + produto.getCodigo()));
        pdf.add(new Paragraph("Nome: " + produto.getNome()));
        pdf.add(new Paragraph("Marca: " + produto.getMarca()));
        pdf.add(new Paragraph("Preço: R$ " + nf.format(produto.getPreco())));
        pdf.add(new Paragraph("Quantidade: " + produto.getQuantidade()));
        pdf.add(new Paragraph(50));
    }

    private void listaProdutos(Document pdf, List<ProdutoDTO> produtos) throws DocumentException, IOException {
        for (ProdutoDTO produto : produtos) {
            listaProduto(pdf, produto);
        }
    }
}
