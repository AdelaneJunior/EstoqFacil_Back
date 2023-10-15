package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Imagem;
import br.ueg.cons.soft.estoqfacil.repository.ImagemRepository;
import br.ueg.cons.soft.estoqfacil.service.ImagemService;
import br.ueg.prog.webi.api.exception.ApiMessageCode;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImagemServiceImpl extends BaseCrudService<Imagem, Long, ImagemRepository> implements ImagemService {

    @Autowired
    private ImagemRepository repository;

    private final String CAMINHO_PASTA =
            "C:\\Users\\Delane Jr\\Documents\\Facul\\6ºSemestre\\EstoqFacil_Geral\\EstoqFacil-Front\\assets"; //caminho para a pasta assests do front ou local onde será guardado as imagens
    private final String PATH_REFERENCE = "assets"; // referencia para encotro no front de forma mais facil

    @Override
    protected void prepararParaIncluir(Imagem entidade) {

    }

    @Override
    protected void validarDados(Imagem entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Imagem entidade) {

    }

    public Long incluir(MultipartFile imagemASalvar) throws IOException {

        String caminhoArquivo = CAMINHO_PASTA + "\\" + imagemASalvar.getOriginalFilename();
        String pathReference = PATH_REFERENCE + "/" + imagemASalvar.getOriginalFilename();

        try {
            Imagem imagem = this.repository.save(Imagem.builder()
                    .nome(imagemASalvar.getOriginalFilename())
                    .tipo(imagemASalvar.getContentType())
                    .pathAbsolute(caminhoArquivo)
                    .pathReference(pathReference)
                    .build());

            imagemASalvar.transferTo(new File(caminhoArquivo));

            return imagem.getId();
        } catch (DataIntegrityViolationException | ConstraintViolationException var3) {
            throw new BusinessException(ApiMessageCode.ERRO_BD, new Object[]{var3.getMessage()});
        }
    }
}
