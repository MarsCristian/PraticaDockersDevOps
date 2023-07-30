package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Locacao;

@SuppressWarnings("unchecked")
public interface ILocacaoDAO extends CrudRepository<Locacao, Long>{

	Locacao findById(long id);

	Locacao findByDataHora(String dataHora);

	List<Locacao> findAll();
	
	Locacao save(Locacao locacao);

	void deleteById(Long id);
}
