package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Locadora;

public interface ILocadoraService {

	Locadora buscarPorId(Long id);

	Locadora buscarPorCNPJ(String CNPJ);

	List<Locadora> buscarTodos();

	void salvar(Locadora editora);

	void excluirPorId(Long id);

	void excluirPorCNPJ(String CNPJ);

	//boolean locadoraTemLocacao(Long id);
}
