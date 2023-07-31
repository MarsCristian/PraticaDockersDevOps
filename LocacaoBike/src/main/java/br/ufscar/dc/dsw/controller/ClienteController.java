// "cliente" Referencia à pasta cliente, nos templates
// "clientes" Referencia o próprio ClienteController

package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ILocacaoService locacaoService;

	@Autowired
	private BCryptPasswordEncoder encoder;


	@GetMapping("/cadastrar")
	public String cadastrar(Cliente cliente) {
		return "cliente/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("clientes", clienteService.buscarTodos()); //Esse clientes é para referenciar o próprio controller?
		return "cliente/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "cliente/cadastro";
		}

		cliente.setSenha(encoder.encode(cliente.getSenha()));
		if(cliente.getPapel().equals("Cliente") || cliente.getPapel().equals("Admin"))
            cliente.setPapel("ROLE_" + cliente.getPapel());
		clienteService.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente inserido com sucesso");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", clienteService.buscarPorId(id));
		return "cliente/cadastro";
	}

	@GetMapping("/buscarPorCPF/{CPF}")
	public String preEditar(@PathVariable("CPF") String CPF, ModelMap model) {
		model.addAttribute("cliente", clienteService.buscarPorCPF(CPF));
		return ""; // Verificar para onde isso retornaria
	}

	@PostMapping("/editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
		System.out.println("Entrou no /editar cliente");
		Integer errors = 0;
		if (result.getFieldError("CPF") != null)
			errors += 1;
		if (result.getFieldError("email") != null)
			errors += 1;
		if (result.getFieldError("telefone") != null)
			errors += 1;

		if (result.getFieldErrorCount() > errors+1 || result.getFieldError("senha") != null || result.getFieldError("nome") != null || result.getFieldError("sexo") != null || result.getFieldError("dataNascimento") != null || result.getFieldError("papel") != null) {
			System.out.println("Falhou");

			return "locadora/cadastro";
		}
		System.out.println("pasosu aqui");
		cliente.setSenha(encoder.encode(cliente.getSenha()));
		if(cliente.getPapel().equals("Cliente") || cliente.getPapel().equals("Admin"))
            cliente.setPapel("ROLE_" + cliente.getPapel());
		clienteService.salvar(cliente);
		attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/excluirPorId/{id}") // Modificar o que já esta feito e referenciava o "excluir", ou então manter esse como excluir sem a especificação
	public String excluirPorId(@PathVariable("id") Long id, RedirectAttributes attr) {
		clienteService.excluirPorId(id);
		attr.addFlashAttribute("sucess", "Cliente excluído com sucesso.");
		return "redirect:/clientes/listar";
	}

	@GetMapping("/excluirPorCPF/{CPF}")
	public String excluirPorCPF(@PathVariable("CPF") String CPF, RedirectAttributes attr) {
		clienteService.excluirPorCPF(CPF);
		attr.addFlashAttribute("sucess", "Cliente excluído com sucesso.");
		return "redirect:/clientes/listar";
	}

	@ModelAttribute("locacoes")
	public List<Locacao> listaLocacoes() {
		return locacaoService.buscarTodos();
	}
}