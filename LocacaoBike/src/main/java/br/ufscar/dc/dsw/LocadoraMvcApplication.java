package br.ufscar.dc.dsw;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;


import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;

@SpringBootApplication
public class LocadoraMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocadoraMvcApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ILocadoraDAO locadoraDAO,BCryptPasswordEncoder encoder, IClienteDAO clienteDAO, ILocacaoDAO locacaoDAO, IUsuarioDAO usuarioDAO) {
		return (args) -> {
						
            System.out.println("Testando o banco");
            
            // Inserindo Locadoras
            Locadora l1 = new Locadora();
            l1.setNome("Conserta Bike RP");
            l1.setEmail("conserta_bike@gmail.com");
            l1.setSenha(encoder.encode("123"));
            l1.setPapel("ROLE_Locadora");
            l1.setCNPJ("55.789.390/0008-99");
            l1.setCidade("Ribeirão Preto");
            l1.setTelefone("(16)12345-1234");
            locadoraDAO.save(l1);
            System.out.println("Inseriu l1");

            Locadora l2 = new Locadora();
            l2.setNome("Conserta Bike Sanca");
            l2.setEmail("conserta_bikeRP@gmail.com");
            l2.setSenha(encoder.encode("123"));
            l2.setPapel("ROLE_Locadora");
            l2.setCNPJ("71.150.470/0001-40");
            l2.setCidade("São Carlos");
            l2.setTelefone("(16)12345-1235");
            locadoraDAO.save(l2);
            System.out.println("Inseriu l1");

            Locadora l3 = new Locadora();
            l3.setNome("Oi Bike");
            l3.setEmail("oi_bike@gmail.com");
            l3.setSenha(encoder.encode("123"));
            l3.setPapel("ROLE_Locadora");
            l3.setCNPJ("55.789.390/0008-00");
            l3.setCidade("São Carlos");
            l3.setTelefone("(16)12345-4321");
            locadoraDAO.save(l3);
            System.out.println("Inseriu l3");

            //Inserindo Clientes
            Cliente c1 = new Cliente();
            c1.setNome("Pietro");
            c1.setEmail("pietro@pietro.com");
            c1.setSenha(encoder.encode("123"));
            c1.setPapel("ROLE_Cliente");
            c1.setCPF("446.023.648-61");
            c1.setSexo("Masculino");
            c1.setTelefone("(16)12346-1235");
            c1.setDataNascimento("1999-09-08");
            clienteDAO.save(c1);
            System.out.println("Inseriu c1");

            Cliente c2 = new Cliente();
            c2.setNome("Rafael");
            c2.setEmail("rafael@rafael.com");
            c2.setSenha(encoder.encode("123"));
            c2.setPapel("ROLE_Cliente");
            c2.setCPF("446.023.648-00");
            c2.setSexo("Masculino");
            c2.setTelefone("(16)12346-4567");
            c2.setDataNascimento("2002-03-27");
            clienteDAO.save(c2);
            System.out.println("Inseriu c2");

            //Inserindo Administradores
            Usuario admin = new Usuario();
            admin.setEmail("admin@gmail.com");
            admin.setPapel("ROLE_Admin");
            admin.setSenha(encoder.encode("admin"));
            admin.setNome("admin");
            admin.setTelefone("(16)12457-1458");
            usuarioDAO.save(admin);


            //Inserindo Locações
            Locacao lo1 = new Locacao();
            lo1.setCliente(c1);
            lo1.setLocadora(l1);
            lo1.setDataHora("2023-08-02T14:00");
            locacaoDAO.save(lo1);

            Locacao lo2 = new Locacao();
            lo2.setCliente(c1);
            lo2.setLocadora(l1);
            lo2.setDataHora("2023-08-04T16:00");
            locacaoDAO.save(lo2);

            List<Locadora> locadora = locadoraDAO.findAll();
            System.out.println("Printando todas as locadoras adicionadas");
            for(Locadora l : locadora) {
                  System.out.println(l);
            }

		};
	}
}
