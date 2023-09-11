package br.ueg.cons.soft.estoqfacil;

import br.ueg.cons.soft.estoqfacil.model.*;
import br.ueg.cons.soft.estoqfacil.repository.PessoaRepository;
import br.ueg.cons.soft.estoqfacil.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class InicialRunner implements ApplicationRunner {


    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Autowired
    private PermissaoServiceImpl permissaoService;
    @Autowired
    private CargoServiceImpl cargoService;
    @Autowired
    private FuncionarioServiceImpl funcionarioService;

    @Autowired
    private ClienteServiceImpl clienteService;

    public void initDados() {


        Pessoa pessoaAdmin = new Pessoa(
                1L,
                "0000000",
                "Ademiro",
                "629999999",
                "admin@gmail.com",
                LocalDate.now()

        );

        Permissao permissao = Permissao.builder()
                .role("ROLE_PRODUTO_ALTERAR")
                .build();

        permissao = permissaoService.incluir(permissao);

        CargoPermissao cargoPermissao = new CargoPermissao();
        cargoPermissao.setPermissao(permissao);

        permissao = new Permissao();
        permissao.setRole("ROLE_PRODUTO_INCLUIR");

        permissao = permissaoService.incluir(permissao);

        CargoPermissao argoPermissao = new CargoPermissao();
        argoPermissao.setPermissao(permissao);

        List<CargoPermissao> lista = new ArrayList<>();
        lista.add(argoPermissao);
        lista.add(cargoPermissao);

        Cargo cargo = Cargo.builder()
                .nome("DONO")
                .build();

        cargo = cargoService.incluir(cargo);

        cargo.setPermissoes(new HashSet<>());
        cargo.getPermissoes().addAll(lista);

        cargo = cargoService.alterar(cargo, 1L);

        System.out.println(cargoService.listarTodos());

        pessoaRepository.save(pessoaAdmin);

        Funcionario funcionarioAdmin = new Funcionario(
                1L,
                pessoaAdmin,
                cargo

        );

        funcionarioAdmin = funcionarioService.incluir(funcionarioAdmin);

        System.out.println("Funcionarios sem mostrar as pemissoes" + funcionarioService.listarTodosSemPermissoes());

        System.out.println("Funcionarios mostrando as pemissoes" +funcionarioService.listarTodos());

        Usuario user = new Usuario(
                1L,
                "admin",
                funcionarioAdmin
        );

        usuarioService.incluir(user);
        System.out.println(usuarioService.listarTodos());

        Cliente cliente = Cliente.builder()
                .pessoa(pessoaRepository.findById(1L).get())
                .build();

        cliente = clienteService.incluir(cliente);

        System.out.println(cliente);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            this.initDados();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
