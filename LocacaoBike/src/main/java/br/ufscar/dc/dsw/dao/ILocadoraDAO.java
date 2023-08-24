package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Locadora;

@SuppressWarnings("unchecked")
public interface ILocadoraDAO extends CrudRepository<Locadora, Long>{

	Locadora findById(long id);
	
	Locadora findByCNPJ(String CNPJ);

	List<Locadora> findAll();
	
	@Query("SELECT locadora FROM Locadora locadora WHERE locadora.cidade = :cidade")
	List<Locadora> findAllByCidade(String cidade);

	Locadora save(Locadora locadora);

	void deleteById(Long id);

	void deleteByCNPJ(String CNPJ);
}
