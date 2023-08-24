// "locacao" Referencia à pasta locacao, nos templates
// "locacoes" Referencia o próprio LocacaoController

package br.ufscar.dc.dsw.controller;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.io.IOException;
import java.util.Map;


import javax.validation.Valid;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import br.ufscar.dc.dsw.service.spec.IClienteService;

import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class LocacaoRestController {

	String DataAtual;
	String HoraAtual;
	String DataHoraAtual;
	DateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat formatoHora = new SimpleDateFormat("HH"); 

	@Autowired
	private ILocacaoService locacaoService;

	@Autowired
	private ILocadoraService locadoraService;

	@Autowired
	private IClienteService clienteService;


    private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	@GetMapping(path = "/locacoes")
	public ResponseEntity<List<Locacao>> lista() {
		List<Locacao> lista = locacaoService.buscarTodos();
		System.out.println("Entrou no lista");

		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/locacoes/{id}")
	public ResponseEntity<Locacao> listaLocacao(@PathVariable("id") Long id) {
		Locacao locacao = locacaoService.buscarPorId(id);
		System.out.println("Entrou no listaLocacao");

		if(locacao == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(locacao);
	}

	
	@GetMapping(path = "/locacoes/clientes/{id}")
	public ResponseEntity<List<Locacao>> listaLocacoesCliente(@PathVariable("id") Long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		List<Locacao> locacoes = locacaoService.buscarTodosPorCliente(cliente);
		System.out.println("Entrou no listaLocacoesCliente");

		if(locacoes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(locacoes);
	}

	@GetMapping(path = "/locacoes/locadoras/{id}")
	public ResponseEntity<List<Locacao>> listaLocacoesLocadora(@PathVariable("id") Long id) {
		Locadora locadora = locadoraService.buscarPorId(id);
		List<Locacao> locacoes = locacaoService.buscarTodosPorLocadora(locadora);
		System.out.println("Entrou no listaLocacoesLocadora");

		if(locacoes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(locacoes);
	}

	
}

