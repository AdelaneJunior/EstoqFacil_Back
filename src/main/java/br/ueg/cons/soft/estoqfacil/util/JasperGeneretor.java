package br.ueg.cons.soft.estoqfacil.util;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JasperGeneretor {

    //CAMINHO ABSOLUTO DE ONDE ESTA O ARQUIVO .JRXML(RELATORIO FEITO NO JASPER)
    public static final String JASPER_PRODUTO = ".\\src\\main\\resources\\ProdutoRelatorio.jrxml";
    public static final String JASPER_PRODUTO_PROMOCAO = ".\\src\\main\\resources\\ProdutoPromocaoRelatorio.jrxml";

    public static void gerarPdfProduto(List<ProdutoDTO> listaProduto, Boolean promocao) {
        try {

            String caminhoJasper = promocao ? JASPER_PRODUTO_PROMOCAO : JASPER_PRODUTO;

            Map<String, Object> parametros = new HashMap<String, Object>();

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaProduto);

            JasperReport report = JasperCompileManager.compileReport(caminhoJasper);

            JasperPrint print = JasperFillManager.fillReport(report, parametros, dataSource);

            //CAMINHO ONDE SERÁ SALVO O PDF (por enquanto deixando na pasta fotos)
            JasperExportManager.exportReportToPdfFile(print, ".\\src\\fotos\\Lista_Produtos.pdf");
            System.out.println("Gerando pdf");

        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
