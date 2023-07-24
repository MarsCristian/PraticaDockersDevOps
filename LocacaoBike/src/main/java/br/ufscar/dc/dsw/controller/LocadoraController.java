package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Controller
@RequestMapping("/locadoras")
public class LocadoraController {

	@Autowired
	private ILocadoraService locadoraService;

	@GetMapping("/cadastrar")
	public String cadastrar(Locadora locadora) {
		return "locadora/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("locadoras", locadoraService.buscarTodos());
		return "locadora/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "locadora/cadastro";
		}

		locadoraService.salvar(locadora);
		attr.addFlashAttribute("sucess", "Locadora inserida com sucesso");
		return "redirect:/locadoras/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("locadora", locadoraService.buscarPorId(id));
		return "locadora/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Locadora locadora, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "locadora/cadastro";
		}

		locadoraService.salvar(locadora);
		attr.addFlashAttribute("sucess", "Locadora editada com sucesso.");
		return "redirect:/locadoras/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		livroService.excluir(id);
		attr.addFlashAttribute("sucess", "Locadora excluída com sucesso.");
		return "redirect:/locadoras/listar";
	}

	@ModelAttribute("locacoes")
	public List<Locacao> listaLocacoes() {
		return editoraService.buscarTodos();
	}
}