package br.ueg.cons.soft.estoqfacil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(scanBasePackages = {
        "br.ueg.cons.soft.*",
        "br.ueg.prog.webi.*" //Para funcionamento da Arquitetura
})
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class},
        basePackages = {
                "br.ueg.cons.soft.*",
                "br.ueg.prog.webi.api.*" //Para funcionamento da Arquitetura
        })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}