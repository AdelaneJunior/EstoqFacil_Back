package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.dto.UsuarioDTO;
import br.ueg.cons.soft.estoqfacil.mapper.UsuarioMapperImpl;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import br.ueg.cons.soft.estoqfacil.repository.UsuarioRepository;
import br.ueg.cons.soft.estoqfacil.service.UsuarioService;
import br.ueg.prog.webi.api.exception.BusinessException;
import br.ueg.prog.webi.api.service.BaseCrudService;
import br.ueg.prog.webi.api.util.Validacoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.ueg.cons.soft.estoqfacil.exception.SistemaMessageCode.*;

@Service
public class UsuarioServiceImpl extends BaseCrudService<Usuario, Long, UsuarioRepository>
        implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapperImpl usuarioMapper;

    Validacoes validacoes = new Validacoes();

    @Override
    protected void prepararParaIncluir(Usuario usuario) {
        Optional<Usuario> usuarioBD = repository.findUsuarioByFuncionario(usuario.getFuncionario().getCpf());
        if(usuarioBD.isPresent()){
            throw new BusinessException(ERRO_USUARIO_COM_LOGIN);
        }

    }

    @Override
    protected void validarDados(Usuario usuario) {
        if(!validacoes.validarSenha(usuario.getSenha()))
        {
            throw new BusinessException(ERRO_SENHA_INVALIDA);
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String senhaCodificada = bCryptPasswordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCodificada);
    }

    @Override
    protected void validarCamposObrigatorios(Usuario entidade) {

    }


    public Usuario getUsuarioPorEmail(String usuarioEmail) {

        return repository.findUsuarioByFuncionario_Pessoa_Email(usuarioEmail).get();

    }

    public UsuarioDTO getUsuarioDTOPorEmail(String usuarioEmail) {

        Usuario usuario = repository.findUsuarioByFuncionario_Pessoa_Email(usuarioEmail).orElseThrow();

        UsuarioDTO usuarioDTO = usuarioMapper.toDTO(usuario);
        usuarioDTO.setSenha(usuario.getSenha());

        List<String> permissaoList = new ArrayList<>();

        usuario.getFuncionario().getCargo().getPermissoes().stream().forEach(cargoPermissao -> {
            permissaoList.add(cargoPermissao.getPermissao().getRole());

        });

        usuarioDTO.setPermissoes(permissaoList);

        return usuarioDTO;
    }

    @Override
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Usuario excluir(Long id) {

        Usuario excluir = null;
        try {
            excluir = super.excluir(id);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(ERRO_FUNCIONARIO_CARGO);
        }

        return excluir;
    }

}
