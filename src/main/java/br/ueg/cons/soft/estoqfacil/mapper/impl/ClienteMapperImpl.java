package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.controller.PessoaController;
import br.ueg.cons.soft.estoqfacil.dto.ClienteDTO;
import br.ueg.cons.soft.estoqfacil.mapper.ClienteMapper;
import br.ueg.cons.soft.estoqfacil.model.Cliente;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring")
public class ClienteMapperImpl implements ClienteMapper {

    PessoaController pessoaController = new PessoaController();
    PessoaMapperImpl pessoaMapper = new PessoaMapperImpl();

    @Override
    public Cliente toModelo(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setCodigo(cliente.getCodigo());
        cliente.setCodigoPessoa(
                pessoaMapper.toModelo(
                        Objects.requireNonNull(pessoaController.ObterPorId(
                                clienteDTO.getPessoaID()).getBody())
                )
        );
        return cliente;
    }

    @Override
    public ClienteDTO toDTO(Cliente modelo) {
        return ClienteDTO.builder()
                .codigo(modelo.getCodigo())
                .pessoaID(
                        modelo.getCodigoPessoa().getCodigo())
                .build();
    }

    @Override
    public List<ClienteDTO> toDTO(List<Cliente> lista) {
        List<ClienteDTO> listaDTO = new ArrayList<>();

        lista.forEach(item -> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
