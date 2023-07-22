package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Locacao;

@SuppressWarnings("unchecked")
public interface ILocacaoDAO extends CrudRepository<Locacao, Long>{

	Locacao findById(long id);
	
	List<Locacao> findByCPF(String CPF);

	List<Locacao> findByCNPJ(String CNPJ);

	List<Locacao> findAll();
	
	Locacao save(Locacao locacao);

	void deleteById(Long id);
}
