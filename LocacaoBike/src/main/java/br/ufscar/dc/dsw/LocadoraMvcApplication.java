package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.dao.IEditoraDAO;
import br.ufscar.dc.dsw.dao.ILivroDAO;
import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Livro;

@SpringBootApplication
public class LocadoraMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocadoraMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ILocadoraDAO locadoraDAO, IClienteDAO clienteDAO, ILocacaoDAO locacaoDAO) {
		return (args) -> {
						
            Locadora l1 = new Locadora();
            l1.setNome("Conserta Bike");
            l1.setEmail("conserta_bike@gmail.com");
            l1.setSenha("ConsertaBike123");
            l1.setPapel("Locadora");
            l1.setCNPJ("55.789.390/0008-99");
            l1.setCidade("Ribeirão Preto");
            l1.setTelefone("(16)12345-1234");

		};
	}
}
