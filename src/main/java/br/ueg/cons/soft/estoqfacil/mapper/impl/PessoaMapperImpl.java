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
        Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(pessoaDTO.getCodigo());
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setEmail(pessoaDTO.getEmail());
        pessoa.setNascimento(pessoaDTO.getNascimento());
        pessoa.setTelefone(pessoaDTO.getTelefone());
        return pessoa;
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
