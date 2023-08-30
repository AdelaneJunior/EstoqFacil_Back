package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.model.Usuario;
import br.ueg.cons.soft.estoqfacil.repository.UsuarioRepository;
import br.ueg.cons.soft.estoqfacil.service.UsuarioService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends BaseCrudService<Usuario, Long, UsuarioRepository>
        implements UsuarioService {


    @Autowired
    UsuarioRepository repository;

    @Override
    protected void prepararParaIncluir(Usuario usuario) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCodificada = bCryptPasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCodificada);

    }

    @Override
    protected void validarDados(Usuario entidade) {

    }

    @Override
    protected void validarCamposObrigatorios(Usuario entidade) {

    }

    public Usuario obterPeloLogin(String login) {

        return this.repository.obterPeloLogin(login);

    }

    public Usuario incluir(Usuario usuario) {
        String senha = usuario.getSenha();
        this.validarCamposObrigatorios(usuario);
        this.validarDados(usuario);
        this.prepararParaIncluir(usuario);
        usuario = this.repository.save(usuario);
        usuario.setSenha(senha);
        return usuario;
    }

}
