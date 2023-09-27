package br.ueg.cons.soft.estoqfacil;

import br.ueg.cons.soft.estoqfacil.controller.MovimentacaoController;
import br.ueg.cons.soft.estoqfacil.controller.ProdutoController;
import br.ueg.cons.soft.estoqfacil.dto.MovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import br.ueg.cons.soft.estoqfacil.model.*;
import br.ueg.cons.soft.estoqfacil.repository.CargoRepository;
import br.ueg.cons.soft.estoqfacil.repository.ImagemRepository;
import br.ueg.cons.soft.estoqfacil.repository.PessoaRepository;
import br.ueg.cons.soft.estoqfacil.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@Transactional(propagation = Propagation.REQUIRED)
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
    @Autowired
    private CategoriaServiceImpl categoriaService;
    @Autowired
    private ProdutoServiceImpl produtoService;
    @Autowired
    private ProdutoController produtoController;
    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private MovimentacaoServiceImpl movimentacaoService;
    @Autowired
    private MovimentacaoController movimentacaoController;
    @Autowired
    private CargoRepository cargoRepository;

    public void initDados() {


        Pessoa pessoaAdmin = new Pessoa(
                0L,
                "0000000",
                "Ademiro",
                "629999999",
                "admin@gmail.com",
                LocalDate.now()

        );

        Permissao permissao = new Permissao();
        permissao.setRole("ROLE_PRODUTO_ALTERAR");

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

        Cargo cargo = new Cargo();
        cargo.setNome("DONO");

        cargo = cargoService.incluir(cargo);

        cargo.getPermissoes().addAll(lista);

        cargo = cargoService.alterar(cargo, 1L);

        pessoaAdmin = pessoaRepository.save(pessoaAdmin);

        pessoaAdmin = new Pessoa();
        pessoaAdmin.setCodigo(1L);

        Funcionario funcionarioAdmin = new Funcionario();

        funcionarioAdmin.setPessoa(pessoaAdmin);
        funcionarioAdmin.setCargo(cargo);

        funcionarioAdmin = funcionarioService.incluir(funcionarioAdmin);

        System.out.println("Funcionarios mostrando as pemissoes" + funcionarioService.listarTodos());

        Cliente cliente = Cliente.builder()
                .pessoa(pessoaRepository.findById(1L).get())
                .build();

        cliente = clienteService.incluir(cliente);

        System.out.println("Cliente mostrando pessoa" + cliente);

        Usuario usuario = new Usuario(
                1L,
                "admin",
                funcionarioAdmin
        );

        usuario = usuarioService.incluir(usuario);
        System.out.println(usuarioService.listarTodos());

        Categoria categoria = new Categoria();
        categoria.setUsuario(usuario);
        categoria.setNome("Computadores");
        categoria.setDescricao("Categoria de computadores");

        categoria = categoriaService.incluir(categoria);

        System.out.println(categoria);

        Imagem imagem = new Imagem();
        imagem.setTipo("image/jpeg");
        imagem.setPathReference("C:\\");
        imagem.setNome("elden.jpg");
        imagem.setPathAbsolute("C:\\");

        try {
            imagem = imagemRepository.save(imagem);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Produto produto = Produto.builder()
                .nome("Iphone 13")
                .marca("Apple")
                .preco(8500.00)
                .quantidade(16L)
                .custo(25.50)
                .categoria(categoria)
                .usuario(usuario)
                .imagem(imagem)
                .descricao("Um delular caro")
                .build();

        produto = produtoService.incluir(produto);

        ProdutoDTO produtoDTO = produtoController.ObterPorId(1L).getBody();

        System.out.println(produtoDTO);

        Movimentacao movimentacao = Movimentacao.builder()
                .usuario(usuario)
                .produto(produto)
                .quantidade(16L)
                .data(LocalDate.now())
                .observacao("Adicionado para testes")
                .acao(AcaoMovimentacao.COMPRA)
                .build();

        movimentacao = movimentacaoService.incluir(movimentacao);

        MovimentacaoDTO movimentacaoDTO = movimentacaoController.ObterPorId(1L).getBody();

        System.out.println(movimentacaoDTO);


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
