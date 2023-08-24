package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Locacao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.domain.Cliente;

@SuppressWarnings("unchecked")
public interface ILocacaoDAO extends CrudRepository<Locacao, Long>{

	Locacao findById(long id);

	Locacao findByDataHora(String dataHora);

	List<Locacao> findAll();
	
	Locacao save(Locacao locacao);

	void deleteById(Long id);


	@Query("SELECT locacao FROM Locacao locacao WHERE locacao.cliente = :cliente")
	public List<Locacao> findByIdByCliente(@Param("cliente") Cliente cliente);

	@Query("SELECT locacao FROM Locacao locacao WHERE locacao.locadora = :locadora")
	public List<Locacao> findByIdByLocadora(@Param("locadora") Locadora locadora);
}
