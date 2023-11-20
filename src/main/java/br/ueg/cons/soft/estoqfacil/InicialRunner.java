package br.ueg.cons.soft.estoqfacil;

import br.ueg.cons.soft.estoqfacil.controller.ImagemController;
import br.ueg.cons.soft.estoqfacil.controller.MovimentacaoController;
import br.ueg.cons.soft.estoqfacil.controller.ProdutoController;
import br.ueg.cons.soft.estoqfacil.dto.EnviaEmailDTO;
import br.ueg.cons.soft.estoqfacil.dto.MovimentacaoDTO;
import br.ueg.cons.soft.estoqfacil.dto.ProdutoDTO;
import br.ueg.cons.soft.estoqfacil.enums.AcaoMovimentacao;
import br.ueg.cons.soft.estoqfacil.model.*;
import br.ueg.cons.soft.estoqfacil.repository.PessoaRepository;
import br.ueg.cons.soft.estoqfacil.service.impl.*;
import com.itextpdf.text.BadElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private ImagemServiceImpl imagemService;
    @Autowired
    private MovimentacaoServiceImpl movimentacaoService;
    @Autowired
    private MovimentacaoController movimentacaoController;
    @Autowired
    private ImagemController imagemController;

    // mudar de acordo com o caminho do seu projeto
    private final String ORIGEM = "C:\\Portable20231\\workspace\\EstoqFacil_BackEnd-master\\src\\fotos";

    public void initDados() throws IOException, BadElementException {

        List<ProdutoDTO> produtoDTOList = new ArrayList<>();


        Pessoa pessoaAdmin = new Pessoa(
                "0000000",
                "Ademiro",
                "629999999",
                "admin",
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

        Funcionario funcionarioAdmin = new Funcionario();

        funcionarioAdmin.setPessoa(pessoaAdmin);
        funcionarioAdmin.setCargo(cargo);

        funcionarioAdmin = funcionarioService.incluir(funcionarioAdmin);

        System.out.println("Funcionarios mostrando as pemissoes" + funcionarioService.listarTodos());

        Cliente cliente = new Cliente();

        cliente.setPessoa(pessoaAdmin);

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
        categoria.setNome("Celulares");
        categoria.setDescricao("Categoria de celulares");

        categoria = categoriaService.incluir(categoria);

        System.out.println(categoria);

        byte[] bytes = Files.readAllBytes(Paths.get(ORIGEM + "\\iphone_13.png"));

        Imagem imagem = Imagem.builder()
                .blob(bytes)
                .build();
        imagem =  this.imagemService.incluir(imagem);

        Produto produto = Produto.builder()
                .nome("Iphone 13")
                .marca("Apple")
                .preco(BigDecimal.valueOf(8500.00))
                .quantidade(16L)
                .custo(BigDecimal.valueOf(25.50))
                .categoria(categoria)
                .usuario(usuario)
                .imagemId(imagem.getId())
                .codigoBarras(321l)
                .descricao("Um celular caro")
                .build();

        produto = produtoService.incluir(produto);

        ProdutoDTO produtoDTO = produtoController.obterPorId(produto.getCodigo()).getBody();

        produtoDTOList.add(produtoDTO);

        bytes = Files.readAllBytes(Paths.get(ORIGEM + "\\iphone_15_pro_max.png"));

        imagem = Imagem.builder()
                .blob(bytes)
                .build();
        imagem =  this.imagemService.incluir(imagem);

        produto = Produto.builder()
                .nome("Iphone 15 Pro Max")
                .marca("Apple")
                .preco(BigDecimal.valueOf(15500.00))
                .quantidade(10L)
                .custo(BigDecimal.valueOf(30.50))
                .categoria(categoria)
                .usuario(usuario)
                .imagemId(imagem.getId())
                .codigoBarras(123l)
                .descricao("Outro celular caro")
                .build();

        produto = produtoService.incluir(produto);

        produtoDTO = produtoController.obterPorId(produto.getCodigo()).getBody();

        produtoDTOList.add(produtoDTO);


//        EnviaEmailDTO envio = EnviaEmailDTO.builder()
//                .email("")
//                .listaProdutos(produtoDTOList)
//                .build();
//        produtoController.enviaEmail(envio);

        System.out.println("Fim da inicialização");
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            this.initDados();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}