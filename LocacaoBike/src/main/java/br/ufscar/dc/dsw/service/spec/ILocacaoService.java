package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Locacao;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;

public interface ILocacaoService {

	Locacao buscarPorId(Long id);

	Locacao buscarPorDataHora(String dataHora);

	List<Locacao> buscarTodosPorCliente(Cliente cliente);

	List<Locacao> buscarTodosPorLocadora(Locadora locadora);

	List<Locacao> buscarTodos();

	void salvar(Locacao editora);

	void excluirPorId(Long id);
}
