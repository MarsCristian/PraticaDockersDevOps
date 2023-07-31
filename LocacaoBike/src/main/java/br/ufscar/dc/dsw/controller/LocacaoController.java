// "locacao" Referencia à pasta locacao, nos templates
// "locacoes" Referencia o próprio LocacaoController

package br.ufscar.dc.dsw.controller;

import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import javax.validation.Valid;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

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

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/cadastrar")
	public String cadastrar(Locacao locacao, ModelMap model, @AuthenticationPrincipal Usuario usuario) {
		System.out.println("Entrou no cadastrar");
		DataAtual = formatoData.format(new Date());
		System.out.println(DataAtual);
		HoraAtual = formatoHora.format(new Date());
		System.out.println(HoraAtual);
		DataHoraAtual = DataAtual + "T" + HoraAtual + ":00";
		System.out.println(DataHoraAtual);
		model.addAttribute("dataHora", DataHoraAtual);
		model.addAttribute("usuarioTeste", usuario);

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nome = null;
		if (principal instanceof UserDetails) {
			nome = ((UserDetails)principal).getUsername();
		}

		Usuario teste = usuarioService.buscarPorNome(nome);

		model.addAttribute("idCliente", teste.getId());
		return "locacao/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		System.out.println("Entrou no listar");
		String dataHoraAuxiliar = null;
		List<Locacao> locacoes = locacaoService.buscarTodos();
		for (Locacao locacao : locacoes) {
			dataHoraAuxiliar = locacao.getDataHora().replace("T", " ");
			locacao.setDataHora(dataHoraAuxiliar);
		}

		model.addAttribute("locacoes", locacoes);

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nome = null;
		if (principal instanceof UserDetails) {
			nome = ((UserDetails)principal).getUsername();
		}

		System.out.println("achou o usuario: " + nome);
		Usuario teste = usuarioService.buscarPorNome(nome);
		model.addAttribute("cliente", teste);

		return "locacao/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr) {
		System.out.println("Entrou no salvar");

		System.out.println("Erros: " + result.getErrorCount());

		if (result.hasErrors()) {
			System.out.println("Entrou no if do salvar locacao");
			return "locacao/cadastro";
		}
		locacaoService.salvar(locacao);
		attr.addFlashAttribute("sucess", "Locação inserida com sucesso");
		return "redirect:/locacoes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		System.out.println("Entrou no preEditar");
		model.addAttribute("locacao", locacaoService.buscarPorId(id));		
		return "locacao/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr) {
		System.out.println("Entrou no editar");

		Integer errors = 0;
		if (result.getFieldError("dataHora") != null)
			errors += 1;
		System.out.println(errors);
		System.out.println(result.getFieldErrorCount()); 
		if (result.getFieldErrorCount() > errors+1 || result.getFieldError("dataHora") != null) {
			System.out.println("Falhou");

			return "locacao/cadastro";
		}

		locacaoService.salvar(locacao);
		attr.addFlashAttribute("sucess", "Locação editada com sucesso.");
		return "redirect:/locacoes/listar";
	}

	@GetMapping("/excluirPorId/{id}") 
	public String excluirPorId(@PathVariable("id") Long id, RedirectAttributes attr) {
		System.out.println("Entrou no excluirPorId");
		locacaoService.excluirPorId(id);
		attr.addFlashAttribute("sucess", "Locação excluída com sucesso.");
		return "redirect:/locacoes/listar";
	}

	@ModelAttribute("clientes")
	public List<Cliente> listaClientes() {
		return clienteService.buscarTodos();
	}

	@ModelAttribute("clienteAtual")
	public Cliente listaClienteAtual(@AuthenticationPrincipal Cliente usuario) {
		System.out.println("usuario" + usuario);
		return usuario;
	}

	@ModelAttribute("locadoras")
	public List<Locadora> listaLocadoras() {
		return locadoraService.buscarTodos();
	}
}

