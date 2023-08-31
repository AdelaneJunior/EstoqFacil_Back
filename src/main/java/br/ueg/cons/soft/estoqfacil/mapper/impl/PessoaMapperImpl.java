package br.ueg.cons.soft.estoqfacil.mapper.impl;

import br.ueg.cons.soft.estoqfacil.dto.PessoaDTO;
import br.ueg.cons.soft.estoqfacil.mapper.PessoaMapper;
import br.ueg.cons.soft.estoqfacil.model.Pessoa;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public class PessoaMapperImpl implements PessoaMapper {

    @Override
    public Pessoa toModelo(PessoaDTO pessoaDTO) {
        return Pessoa.builder()
                .codigo(pessoaDTO.getCodigo())
                .cpf(pessoaDTO.getCpf())
                .email(pessoaDTO.getEmail())
                .nascimento(pessoaDTO.getNascimento())
                .telefone(pessoaDTO.getTelefone())
                .build();
    }

    @Override
    public PessoaDTO toDTO(Pessoa modelo) {
        return PessoaDTO.builder()
                .codigo(modelo.getCodigo())
                .cpf(modelo.getCpf())
                .email(modelo.getEmail())
                .nome(modelo.getNome())
                .nascimento(modelo.getNascimento())
                .telefone(modelo.getTelefone())
                .build();
    }

    @Override
    public List<PessoaDTO> toDTO(List<Pessoa> lista) {
        List<PessoaDTO> listaDTO= new ArrayList<>();

        lista.forEach(item -> listaDTO.add(toDTO(item)));

        return listaDTO;
    }
}
