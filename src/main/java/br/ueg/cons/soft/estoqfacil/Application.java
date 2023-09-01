package br.ueg.cons.soft.estoqfacil;

import br.ueg.cons.soft.estoqfacil.model.Funcionario;
import br.ueg.cons.soft.estoqfacil.model.Pessoa;
import br.ueg.cons.soft.estoqfacil.model.Usuario;
import br.ueg.cons.soft.estoqfacil.repository.FuncionarioRepository;
import br.ueg.cons.soft.estoqfacil.repository.PessoaRepository;
import br.ueg.cons.soft.estoqfacil.repository.UsuarioRepository;
import br.ueg.prog.webi.api.controller.CrudController;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.method.HandlerMethod;

import java.time.LocalDate;
import java.util.Objects;

@SpringBootApplication(scanBasePackages = {
        "br.ueg.cons.soft.*", // modificar conforme o pacote padrão do seu projeto
        "br.ueg.prog.webi.*" //Para funcionamento da Arquitetura
})
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = {
                "br.ueg.cons.soft.*", //modificar conforme o pacote padrão do seu projeto
                "br.ueg.prog.webi.api.*" //Para funcionamento da Arquitetura
        })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner
	commandLineRunner(UsuarioRepository usuarioRepository, PessoaRepository pessoaRepository, FuncionarioRepository funcionarioRepository) {
		return args -> {

			Pessoa pessoaAdmin = new Pessoa(
					1L,
					"0000000",
					"Ademiro",
					"629999999",
					"admin@gmail.com",
					LocalDate.now()

			);

			pessoaRepository.save(pessoaAdmin);

			Funcionario funcionarioAdmin = new Funcionario(
					1L,
					pessoaAdmin,
					"DONO"

			);

			funcionarioRepository.save(funcionarioAdmin);

			Usuario user = new Usuario(
					1L,
					"admin",
					"admin",
					funcionarioAdmin
			);

			usuarioRepository.save(user);

		};
	}
	@Bean
	public OperationCustomizer operationIdCustomizer() {
		OperationCustomizer c = new OperationCustomizer() {
			@Override
			public Operation customize(Operation operation, HandlerMethod handlerMethod) {
				Class<?> superClazz = handlerMethod.getBeanType().getSuperclass();
				if (Objects.nonNull(superClazz) && superClazz.isAssignableFrom(CrudController.class)) {
					String beanName = handlerMethod.getBeanType().getSimpleName();
					operation.setOperationId(String.format("%s_%s", beanName, handlerMethod.getMethod().getName()));
				}
				return operation;
			}
		};
		return c;
	}
}