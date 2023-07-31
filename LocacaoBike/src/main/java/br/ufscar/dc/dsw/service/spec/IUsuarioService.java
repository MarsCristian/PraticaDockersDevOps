package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public interface IUsuarioService {

	Usuario buscarPorId(Long id);

	Usuario buscarPorTelefone(String telefone);

	Usuario buscarPorEmail(String email);

	Usuario buscarPorNome(String nome);

	List<Usuario> buscarTodos();

	void salvar(Usuario editora);

	void excluirPorId(Long id);
}
