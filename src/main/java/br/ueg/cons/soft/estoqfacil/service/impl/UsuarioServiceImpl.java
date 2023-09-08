package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.dto.UsuarioDTO;
import br.ueg.cons.soft.estoqfacil.mapper.UsuarioMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import br.ueg.cons.soft.estoqfacil.repository.UsuarioRepository;
import br.ueg.cons.soft.estoqfacil.service.UsuarioService;
import br.ueg.prog.webi.api.service.BaseCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl extends BaseCrudService<Usuario, Long, UsuarioRepository>
        implements UsuarioService {


    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapperImpl usuarioMapper;
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


    public Usuario incluir(Usuario usuario) {
        String senha = usuario.getSenha();
        this.validarCamposObrigatorios(usuario);
        this.validarDados(usuario);
        this.prepararParaIncluir(usuario);
        usuario = usuarioRepository.save(usuario);
        usuario.setSenha(senha);
        return usuario;
    }

    public Usuario getUsuarioPorEmail(String usuarioEmail){

        return repository.findUsuarioByFuncionario_Pessoa_Email(usuarioEmail).get();

    }

    public UsuarioDTO getUsuarioDTOPorEmail(String usuarioEmail){

        return usuarioMapper.toDTO(usuarioRepository.findUsuarioByFuncionario_Pessoa_Email(usuarioEmail).get());

    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}
