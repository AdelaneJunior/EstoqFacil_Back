package br.ueg.cons.soft.estoqfacil.service.impl;

import br.ueg.cons.soft.estoqfacil.controller.UsuarioController;
import br.ueg.cons.soft.estoqfacil.dto.UsuarioDTO;
import br.ueg.prog.webi.api.dto.CredencialDTO;
import br.ueg.prog.webi.api.dto.UsuarioSenhaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

//TODO resolver a parte do usuario aqui dentro
@Service
public class UserProviderService implements br.ueg.prog.webi.api.service.UserProviderService {

    @Autowired
    UsuarioController usuarioController;

    @Override
    public CredencialDTO getCredentialByLogin(String username) {

        if (Objects.nonNull(username)) {

            UsuarioDTO user = this.usuarioController.obterPorLogin(username);

            if (Objects.nonNull(user)) {

                return getCredencialDTO(user);

            }

        }
        return null;
    }

    private CredencialDTO getCredencialDTO(UsuarioDTO user) {

        return CredencialDTO.builder()
                .login(user.getLogin())
                .id(user.getCodigo())
                .nome(user.getNome())
                .email(user.getEmail())
                .roles(Collections.singletonList(user.getRole()))
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