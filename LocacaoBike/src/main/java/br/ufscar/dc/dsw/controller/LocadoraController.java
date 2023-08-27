// "locadora" Referencia à pasta locadora, no templates
// "locadoras" Referencia o próprio LocadoraController

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

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;

@Controller
@RequestMapping("/locadoras")
public class LocadoraController {

	@Autowired
	private ILocadoraService locadoraService;

	@Autowired
	private ILocacaoService locacaoService;

	@Autowired
	private BCryptPasswordEncoder encoder;
	

	@GetMapping("/cadastrar")
	public String cadastrar(Locadora locadora) {
		System.out.println("Cadastrando nova Locadora");
		//model.addAttribute("Locadora", locadora);
		return "locadora/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("locadoras", locadoraService.buscarTodos());
		return "locadora/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {

		System.out.println("Salvar locadora");

		if (result.hasErrors()) {
			System.out.println("Entrou no if");
			return "locadora/cadastro";
		}
		System.out.println("ROLE_".concat(locadora.getPapel()));

		System.out.println(locadora.getPapel().equals("Locadora"));

		if(locadora.getPapel().equals("Locadora"))
		{
			System.out.println("Arrumando Role");
            locadora.setPapel("ROLE_".concat(locadora.getPapel()));
		}
		locadora.setSenha(encoder.encode(locadora.getSenha()));
		System.out.println(locadora.getPapel());
		
		locadoraService.salvar(locadora);
		attr.addFlashAttribute("sucess", "Locadora inserida com sucesso");
		return "redirect:/locadoras/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		System.out.println("Pre editar locadora");
		model.addAttribute("locadora", locadoraService.buscarPorId(id));
		return "locadora/cadastro";
	}

	@GetMapping("/procurarPorCNPJ/{CNPJ}")
	public String buscarPorCNPJ(@PathVariable("CNPJ") String CNPJ, ModelMap model) {  
		model.addAttribute("locadora", locadoraService.buscarPorCNPJ(CNPJ));
		return "locadora/cadastro"; //Verificar pra onde isso retornaria de fato
	}

	@PostMapping("/editar")
	public String editar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {
		System.out.println("Entrou no /editar");
		Integer errors = 0;
		if (result.getFieldError("CNPJ") != null)
			errors += 1;
		if (result.getFieldError("email") != null)
			errors += 1;
		if (result.getFieldError("telefone") != null)
			errors += 1;

		System.out.println(errors);
		System.out.println(result.getFieldErrorCount()); 
		if (result.getFieldErrorCount() > errors+1 || result.getFieldError("senha") != null || result.getFieldError("nome") != null || result.getFieldError("cidade") != null || result.getFieldError("papel") != null) {
			System.out.println("Falhou");

			return "locadora/cadastro";
		}
		locadora.setSenha(encoder.encode(locadora.getSenha()));
		if(locadora.getPapel().equals("Locadora"))
		{
			System.out.println("Arrumando Role");
            locadora.setPapel("ROLE_".concat(locadora.getPapel()));
		}
		locadoraService.salvar(locadora);
		attr.addFlashAttribute("sucess", "Locadora editada com sucesso.");
		return "redirect:/locadoras/listar";
	}

	@GetMapping("/excluirPorId/{id}") //Mudar no html para excluir por id
	public String excluirPorID(@PathVariable("id") Long id, RedirectAttributes attr) {
		System.out.println("Entrou no excluir");
		locadoraService.excluirPorId(id);
		attr.addFlashAttribute("sucess", "Locadora excluída com sucesso.");
		return "redirect:/locadoras/listar";
	}

	@GetMapping("/excluirPorCNPJ/{CNPJ}") 
	public String excluirPorCNPJ(@PathVariable("CNPJ") String CNPJ, RedirectAttributes attr) {
		locadoraService.excluirPorCNPJ(CNPJ);
		attr.addFlashAttribute("sucess", "Locadora excluída com sucesso.");
		return "redirect:/locadoras/listar";
	}

	@ModelAttribute("locacoes") // Alguém entendeu a utilidade disso? Porque eu gostaria de printar todas as editoras por aqui?
	public List<Locacao> listaLocacoes() {
		return locacaoService.buscarTodos();
	}

	@ModelAttribute("locadoras") 
	public List<Locadora> listaLocadoras() {
		return locadoraService.buscarTodos();
	}
}