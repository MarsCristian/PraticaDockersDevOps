// "locadora" Referencia à pasta locadora, no templates
// "locadoras" Referencia o próprio LocadoraController

package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@CrossOrigin
@RestController
public class LocadoraRestController {

	@Autowired
	private ILocadoraService locadoraService;


	@Autowired
	private BCryptPasswordEncoder encoder;
	

	private boolean isJSONValid(String jsonInString) {
		try {
			return new com.fasterxml.jackson.databind.ObjectMapper().readTree(jsonInString) != null;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
		private void parse(Locadora locadora, JSONObject json) {


			Object id = json.get("id");
			if (id != null) {
				if (id instanceof Integer) {
					locadora.setId(((Integer) id).longValue());
				} else {
					locadora.setId((Long) id);
				}
			}
			
			locadora.setNome((String) json.get("nome"));
			locadora.setEmail((String) json.get("email"));
			locadora.setCNPJ((String) json.get("CNPJ"));
			locadora.setCidade((String) json.get("cidade"));
			locadora.setTelefone((String) json.get("telefone"));
			locadora.setSenha((String) json.get("senha"));
			locadora.setPapel((String) json.get("papel"));
			locadora.setLocacoes(null);
		}

	@PostMapping(path = "/locadoras")
	@ResponseBody
	public ResponseEntity<Locadora> save(@RequestBody JSONObject json) {
		
		try {
			if (isJSONValid(json.toString())) {
				Locadora locadora = new Locadora();
				parse(locadora, json);
				locadoraService.salvar(locadora);
				return ResponseEntity.ok(locadora);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}



	}

	// Não retornar Locações
	@GetMapping(path = "/locadoras")
	public ResponseEntity<List<Locadora>> lista() {
		List<Locadora> lista = locadoraService.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/locadoras/{id}")
	public ResponseEntity<Locadora> lista(@PathVariable("id") long id) {
		Locadora locadora = locadoraService.buscarPorId(id);
		if (locadora == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(locadora);
	}

	@GetMapping(path = "/locadoras/cidades/{cidade}")
	public ResponseEntity<List<Locadora>> lista(@PathVariable("cidade") String cidade) {
		List<Locadora> lista = locadoraService.buscarPorCidade(cidade);
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}
		
	@PutMapping(path = "/locadoras/{id}")
	public ResponseEntity<Locadora> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Locadora locadora = locadoraService.buscarPorId(id);
				if (locadora == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(locadora, json);
					locadoraService.salvar(locadora);
					return ResponseEntity.ok(locadora);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/locadoras/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {

		Locadora locadora = locadoraService.buscarPorId(id);
		if (locadora == null) {
			return ResponseEntity.notFound().build();
		} else {

			if(locadoraService.locadoraTemLocacao(id)) {
				return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
			} else {
				locadoraService.excluirPorId(id);
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		}
	}
}