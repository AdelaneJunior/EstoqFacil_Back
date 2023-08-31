package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.controller.PessoaController;
import br.ueg.cons.soft.estoqfacil.dto.ClienteDTO;
import br.ueg.cons.soft.estoqfacil.mapper.ClienteMapper;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import br.ueg.cons.soft.estoqfacil.service.impl.PessoaServiceImpl;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public class ClienteMapperImpl implements ClienteMapper {

    @Autowired
    PessoaServiceImpl pessoaService;

    @Override
    public Cliente toModelo(ClienteDTO clienteDTO) {
        return Cliente.builder()
                .codigo(clienteDTO.getCodigo())
                .codigoPessoa(pessoaService.obterPeloId(clienteDTO.getPessoaID()))
                .build();
    }

    @Override
    public ClienteDTO toDTO(Cliente modelo) {
        return ClienteDTO.builder()
                .codigo(modelo.getCodigo())
                .pessoaID(modelo.getCodigoPessoa().getCodigo())
                .build();
    }

    @Override
    public List<ClienteDTO> toDTO(List<Cliente> lista) {
        List<ClienteDTO> listaDTO = new ArrayList<>();

        lista.forEach(item -> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
