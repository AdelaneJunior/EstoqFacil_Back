package br.ueg.cons.soft.estoqfacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication(scanBasePackages = {
		"br.ueg.cons.soft.*", // modificar conforme o pacote padrão do seu projeto
		"br.ueg.prog.webi.*" //Para funcionamento da Arquitetura
})
@EntityScan(basePackageClasses = { Jsr310JpaConverters.class },
		basePackages = {
				"br.ueg.cons.soft.*", //modificar conforme o pacote padrão do seu projeto
				"br.ueg.prog.webi.api.*" //Para funcionamento da Arquitetura
		})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Hello world");
	}

}
