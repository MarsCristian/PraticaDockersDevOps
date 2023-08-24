// "cliente" Referencia à pasta cliente, nos templates
// "clientes" Referencia o próprio ClienteController

package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.IClienteService;
import br.ufscar.dc.dsw.service.spec.ILocacaoService;

@RestController
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ILocacaoService locacaoService;

	@Autowired
	private BCryptPasswordEncoder encoder;

    private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	// @SuppressWarnings("unchecked")
	// private void parse(List<Locacao> locacoes, JSONObject json) {

	// 	Map<String, Object> map = (Map<String, Object>) json.get("locacao");
	// 	for (Locacao locacao : locacoes) 
	// 	{
	// 		Object id = map.get("id");
	// 		if (id instanceof Integer) {
	// 			locacao.setId(((Integer) id).longValue());
	// 		} else {
	// 			locacao.setId(((Long) id));
	// 		}

	// 		locacao.setCliente((Cliente) map.get("cliente"));
	// 		locacao.setDataHora((String) map.get("dataHora"));
	// 		locacao.setLocadora((Locadora) map.get("locadora"));
	// 	}

	// }

    private void parse(Cliente cliente, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				cliente.setId(((Integer) id).longValue());
			} else {
				cliente.setId((Long) id);
			}
		}

		cliente.setCPF((String) json.get("cpf"));
		cliente.setNome((String) json.get("nome"));
		cliente.setDataNascimento((String) json.get("dataNascimento"));
		cliente.setEmail((String) json.get("email"));
		cliente.setPapel((String) json.get("papel"));
		if(cliente.getPapel().equals("Cliente") || cliente.getPapel().equals("Admin"))
            cliente.setPapel("ROLE_" + cliente.getPapel());
		cliente.setSexo((String) json.get("sexo"));
        cliente.setTelefone((String) json.get("telefone"));
		cliente.setSenha(encoder.encode((String) json.get("senha")));
		//cliente.setLocacoes(null);
	}

    @PostMapping(path = "/clientes")
	@ResponseBody
	public ResponseEntity<Cliente> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = new Cliente();
				parse(cliente, json);
				clienteService.salvar(cliente);
				return ResponseEntity.ok(cliente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

    @PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = clienteService.buscarPorId(id);
				if (cliente == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(cliente, json);
					clienteService.salvar(cliente);
					return ResponseEntity.ok(cliente);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	//*
    @DeleteMapping(path = "/clientes/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
		System.out.println("ENtrou no delete");

		Cliente cliente = clienteService.buscarPorId(id);

		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			if (clienteService.clienteTemLocacao(id)) {
				System.out.println("ENtrou no forbidden");
				return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
			} else {
				clienteService.excluirPorId(id);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			}
		}
	}
	//*/

	// Não retornar Locações
    @GetMapping(path = "/clientes")
	public ResponseEntity<List<Cliente>> lista() {
		List<Cliente> lista = clienteService.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

    @GetMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> lista(@PathVariable("id") long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}





	// @GetMapping("/listar")
	// public String listar(ModelMap model) {
	// 	model.addAttribute("clientes", clienteService.buscarTodos()); //Esse clientes é para referenciar o próprio controller?
	// 	return "cliente/lista";
	// }

	// @PostMapping("/salvar")
	// public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

	// 	if (result.hasErrors()) {
	// 		return "cliente/cadastro";
	// 	}

	// 	cliente.setSenha(encoder.encode(cliente.getSenha()));
	// 	if(cliente.getPapel().equals("Cliente") || cliente.getPapel().equals("Admin"))
    //         cliente.setPapel("ROLE_" + cliente.getPapel());
	// 	clienteService.salvar(cliente);
	// 	attr.addFlashAttribute("sucess", "Cliente inserido com sucesso");
	// 	return "redirect:/clientes/listar";
	// }

	// @GetMapping("/editar/{id}")
	// public String preEditar(@PathVariable("id") Long id, ModelMap model) {
	// 	model.addAttribute("cliente", clienteService.buscarPorId(id));
	// 	return "cliente/cadastro";
	// }

	// @GetMapping("/buscarPorCPF/{CPF}")
	// public String preEditar(@PathVariable("CPF") String CPF, ModelMap model) {
	// 	model.addAttribute("cliente", clienteService.buscarPorCPF(CPF));
	// 	return ""; // Verificar para onde isso retornaria
	// }

	// @PostMapping("/editar")
	// public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
	// 	System.out.println("Entrou no /editar cliente");
	// 	Integer errors = 0;
	// 	if (result.getFieldError("CPF") != null)
	// 		errors += 1;
	// 	if (result.getFieldError("email") != null)
	// 		errors += 1;
	// 	if (result.getFieldError("telefone") != null)
	// 		errors += 1;

	// 	if (result.getFieldErrorCount() > errors+1 || result.getFieldError("senha") != null || result.getFieldError("nome") != null || result.getFieldError("sexo") != null || result.getFieldError("dataNascimento") != null || result.getFieldError("papel") != null) {
	// 		System.out.println("Falhou");

	// 		return "locadora/cadastro";
	// 	}
	// 	System.out.println("pasosu aqui");
	// 	cliente.setSenha(encoder.encode(cliente.getSenha()));
	// 	if(cliente.getPapel().equals("Cliente") || cliente.getPapel().equals("Admin"))
    //         cliente.setPapel("ROLE_" + cliente.getPapel());
	// 	clienteService.salvar(cliente);
	// 	attr.addFlashAttribute("sucess", "Cliente editado com sucesso.");
	// 	return "redirect:/clientes/listar";
	// }

	// @GetMapping("/excluirPorId/{id}") // Modificar o que já esta feito e referenciava o "excluir", ou então manter esse como excluir sem a especificação
	// public String excluirPorId(@PathVariable("id") Long id, RedirectAttributes attr) {
	// 	clienteService.excluirPorId(id);
	// 	attr.addFlashAttribute("sucess", "Cliente excluído com sucesso.");
	// 	return "redirect:/clientes/listar";
	// }

	// @GetMapping("/excluirPorCPF/{CPF}")
	// public String excluirPorCPF(@PathVariable("CPF") String CPF, RedirectAttributes attr) {
	// 	clienteService.excluirPorCPF(CPF);
	// 	attr.addFlashAttribute("sucess", "Cliente excluído com sucesso.");
	// 	return "redirect:/clientes/listar";
	// }

	// @ModelAttribute("locacoes")
	// public List<Locacao> listaLocacoes() {
	// 	return locacaoService.buscarTodos();
	// }
}