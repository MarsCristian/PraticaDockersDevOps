package br.ufscar.dc.dsw;

import java.util.List;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.dao.ILocacaoDAO;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;

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
            locadoraDAO.save(l1);

            Locadora l2 = new Locadora();
            l2.setNome("Conserta Bike");
            l2.setEmail("conserta_bikeRP@gmail.com");
            l2.setSenha("ConsertaBike123");
            l2.setPapel("Locadora");
            l2.setCNPJ("71.150.470/0001-40");
            l2.setCidade("São Carlos");
            l2.setTelefone("(16)12345-1234");
            locadoraDAO.save(l2);

            List<Locadora> locadora = locadoraDAO.findAll();
            System.out.println("Printando todas as locadoras adicionadas");
            for(Locadora l : locadora) {
                  System.out.println(l);
            }

		};
	}
}
