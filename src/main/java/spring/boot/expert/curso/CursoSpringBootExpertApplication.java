package spring.boot.expert.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Spring Boot API", version = "1.0", description = "API do curso de Spring Boot",
contact = @Contact(name = "Douglas Fragoso", email = "douglas.iff@gmail.com", url = "https://github.com/douglasfragoso")))
public class CursoSpringBootExpertApplication {

	public static void main(String[] args) {
		SpringApplication.run(CursoSpringBootExpertApplication.class, args);
	}

}
