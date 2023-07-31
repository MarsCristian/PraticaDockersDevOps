package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Locacao;

public interface ILocacaoService {

	Locacao buscarPorId(Long id);

	Locacao buscarPorDataHora(String dataHora);

	List<Locacao> buscarTodos();

	void salvar(Locacao editora);

	void excluirPorId(Long id);
}
