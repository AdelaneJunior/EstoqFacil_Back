package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.dto.UsuarioDTO;
import br.ueg.cons.soft.estoqfacil.mapper.UsuarioMapper;
import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.Pessoa;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import br.ueg.cons.soft.estoqfacil.service.impl.FuncionarioServiceImpl;
import br.ueg.cons.soft.estoqfacil.service.impl.PessoaServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class UsuarioMapperImpl implements UsuarioMapper {

    @Autowired
    private FuncionarioServiceImpl funcionarioService;

    @Autowired
    private PessoaServiceImpl pessoaService;

    @Override
    public Usuario toModelo(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .codigo(usuarioDTO.getCodigo())
                .login(usuarioDTO.getLogin())
                .usuarioFuncionario(funcionarioService.obterPeloId(usuarioDTO.getFuncionarioCodigo()))
                .senha(usuarioDTO.getSenha())
                .build();
    }

    @Override
    public UsuarioDTO toDTO(Usuario modelo) {

        Funcionario funcionario = funcionarioService.obterPeloId(modelo.getUsuarioFuncionario().getCodigo());
        Pessoa pessoa = pessoaService.obterPeloId(funcionario.getPessoa().getCodigo());

        return UsuarioDTO.builder()
                .codigo(modelo.getCodigo())
                .login(modelo.getLogin())
                .senha(modelo.getSenha())
                .funcionarioCodigo(funcionario.getCodigo())
                .funcionarioNome(pessoa.getNome())
                .funcionarioEmail(pessoa.getEmail())
                .funcionarioCargo(funcionario.getCargo())
                .build();
    }

    @Override
    public List<UsuarioDTO> toDTO(List<Usuario> lista) {
        List<UsuarioDTO> listaDTO = new ArrayList<>();

        lista.forEach(item -> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
