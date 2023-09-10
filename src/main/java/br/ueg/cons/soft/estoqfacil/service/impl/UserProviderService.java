package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.controller.UsuarioController;
import br.ueg.cons.soft.estoqfacil.dto.UsuarioDTO;
import br.ueg.prog.webi.api.dto.CredencialDTO;
import br.ueg.prog.webi.api.dto.UsuarioSenhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

//TODO resolver a parte do usuario aqui dentro
@Service
public class UserProviderService implements br.ueg.prog.webi.api.service.UserProviderService {

    @Autowired
    UsuarioController usuarioController;


    @Autowired
    UsuarioServiceImpl usuarioService;


    @Override
    public CredencialDTO getCredentialByLogin(String usuarioEmail) {

        return getCredencialDTO(usuarioService.getUsuarioDTOPorEmail(usuarioEmail));
    }


    private CredencialDTO getCredencialDTO(UsuarioDTO user) {

        System.out.println(user.toString());

        return CredencialDTO.builder()
                .login(user.getFuncionarioEmail())
                .id(user.getCodigo())
                .nome(user.getFuncionarioNome())
                .email(user.getFuncionarioEmail())
                .roles(user.getPermissoes())
                .statusAtivo(true)
                .senha(user.getSenha())
                .build();
    }


    @Override
    public CredencialDTO redefinirSenha(UsuarioSenhaDTO usuarioSenhaDTO) {
        return null;
    }

    @Override
    public CredencialDTO getCredentialByEmail(String email) {
        return null;
    }
}