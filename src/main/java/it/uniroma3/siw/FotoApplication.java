package it.uniroma3.siw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import it.uniroma3.siw.model.Funzionario;
import it.uniroma3.siw.services.FunzionarioService;
import it.uniroma3.siw.services.storage.StorageProperties;
import it.uniroma3.siw.services.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(FotoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService,FunzionarioService funzionarioService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
			Funzionario funzionario = new Funzionario();
			funzionario.setUsername("luca");
			funzionario.setUsername("ciao1234");
			if(!funzionarioService.esiste("luca"))
				funzionarioService.save(funzionario);
		};
	}

}
