package br.ueg.cons.soft.estoqfacil.util;

import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasperGeneretor {

    public static void gerarPdf(List<ProdutoDTO> listaProduto) {

        try {

            //CAMINHO ABSOLUTO DE ONDE ESTA O ARQUIVO .JRXML(RELATORIO FEITO NO JASPER)
            String filePath = "C:\\Users\\Delane Jr\\Documents\\Facul\\6ºSemestre\\EstoqFacil_Geral\\EstoqFacil-BackEnd\\src\\main\\resources\\ProdutoRelatorio.jrxml";

            Map<String, Object> parametros = new HashMap<String, Object>();

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaProduto);

            JasperReport report = JasperCompileManager.compileReport(filePath);

            JasperPrint print = JasperFillManager.fillReport(report, parametros, dataSource);

            //CAMINHO ONDE SERÁ SALVO O PDF (por enquanto deixando na pasta fotos)
            JasperExportManager.exportReportToPdfFile(print, "C:\\Users\\Delane Jr\\Documents\\Facul\\6ºSemestre\\EstoqFacil_Geral\\EstoqFacil-BackEnd\\src\\fotos\\Teste.pdf");
            System.out.println("Gerando pdf");

        } catch (Exception e) {

            System.out.println(e);

        }
    }
}
