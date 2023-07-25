// "locacao" Referencia à pasta locacao, nos templates
// "locacoes" Referencia o próprio LocacaoController

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

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;
@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

	@Autowired
	private ILocacaoService locacaoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Locacao locacao) {
		return "locacao/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("locacoes", locacaoService.buscarTodos());
		return "locacao/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "locacao/cadastro";
		}

		locacaoService.salvar(locacao);
		attr.addFlashAttribute("sucess", "Locação inserida com sucesso");
		return "redirect:/locacoes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("locacao", locacaoService.buscarPorId(id));
		return "locacao/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Locacao locacao, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "locacao/cadastro";
		}

		locacaoService.salvar(locacao);
		attr.addFlashAttribute("sucess", "Locação editada com sucesso.");
		return "redirect:/locacoes/listar";
	}

	@GetMapping("/excluirPorId/{id}") 
	public String excluirPorId(@PathVariable("id") Long id, RedirectAttributes attr) {
		locacaoService.excluirPorId(id);
		attr.addFlashAttribute("sucess", "Locação excluída com sucesso.");
		return "redirect:/locacao/listar";
	}
}