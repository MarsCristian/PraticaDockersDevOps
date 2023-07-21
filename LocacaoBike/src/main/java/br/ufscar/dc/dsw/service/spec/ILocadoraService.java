package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Locadora;

public interface ILocadoraService {

	Locadora buscarPorId(Long id);

	List<Locadora> buscarTodos();

	void salvar(Locadora editora);

	void excluir(Long id);
}
