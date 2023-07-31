package br.ufscar.dc.dsw;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

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
            l1.setNome("Conserta Bike");
            l1.setEmail("conserta_bike@gmail.com");
            l1.setSenha(encoder.encode("ConsertaBike123"));
            l1.setPapel("ROLE_Locadora");
            l1.setCNPJ("55.789.390/0008-99");
            l1.setCidade("Ribeirão Preto");
            l1.setTelefone("(16)12345-1234");
            locadoraDAO.save(l1);
            System.out.println("Inseriu l1");

            Locadora l2 = new Locadora();
            l2.setNome("Conserta Bike");
            l2.setEmail("conserta_bikeRP@gmail.com");
            l2.setSenha(encoder.encode("ConsertaBike123"));
            l2.setPapel("ROLE_Locadora");
            l2.setCNPJ("71.150.470/0001-40");
            l2.setCidade("São Carlos");
            l2.setTelefone("(16)12345-1235");
            locadoraDAO.save(l2);
            System.out.println("Inseriu l1");

            //Inserindo Clientes
            Cliente c1 = new Cliente();
            c1.setNome("Pietro");
            c1.setEmail("pietro@pietro.com");
            c1.setSenha(encoder.encode("123"));
            c1.setPapel("ROLE_Cliente");
            c1.setCPF("446.023.648-61");
            c1.setSexo("Masculino");
            c1.setTelefone("(16)12346-1235");
            c1.setDataNascimento("08/09/1999");
            clienteDAO.save(c1);
            System.out.println("Inseriu c1");

            Usuario admin = new Usuario();
            admin.setEmail("admin");
            admin.setPapel("ROLE_Admin");
            admin.setSenha(encoder.encode("admin"));
            admin.setNome("admin");
            admin.setTelefone("(16)12457-1458");
            usuarioDAO.save(admin);

            //Declaração de variáveis para a validação das datas
            //SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy"); 
            //SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm"); 
            Date DataAtual = new Date();
            String data = new SimpleDateFormat("yyyy-MM-dd").format(DataAtual);
            String hora = new SimpleDateFormat("HH:00").format(DataAtual);
            //Date test = formatoData.parse("27/07/2023 17:00");

            System.out.println(data);
            System.out.println(hora);

            //Inserindo Locações
            Locacao lo1 = new Locacao();
            lo1.setCliente(c1);
            lo1.setLocadora(l1);
            lo1.setDataHora(data + "T" + hora);
            //lo1.setHoraLocacao(hora);
            locacaoDAO.save(lo1);

            //if (test.after(DataAtual)) {
            //      lo1.setDataHoraLocacao("27/07/2023 17:00");
            //      locacaoDAO.save(lo1);
            //}
            //else {
            //      lo1.setDataHoraLocacao("25/10/2023 10:00");
            //      locacaoDAO.save(lo1);
            //}

            List<Locadora> locadora = locadoraDAO.findAll();
            System.out.println("Printando todas as locadoras adicionadas");
            for(Locadora l : locadora) {
                  System.out.println(l);
            }

		};
	}
}
