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

    @Override
    protected void prepararParaIncluir(Imagem entidade) {

    }

    @Override
    protected void validarDados(Imagem entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Imagem entidade) {

    }
}
