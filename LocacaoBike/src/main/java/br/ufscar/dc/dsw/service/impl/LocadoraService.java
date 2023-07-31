package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;
import br.ufscar.dc.dsw.service.spec.ILocadoraService;

@Service
@Transactional(readOnly = false)
public class LocadoraService implements ILocadoraService {

	@Autowired
	ILocadoraDAO dao;

	@Transactional(readOnly = true)
	public Locadora buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

    @Transactional(readOnly = true)
	public Locadora buscarPorCNPJ(String CNPJ) {
		System.out.println("Entrou no buscar por CNPJ");
		return dao.findByCNPJ(CNPJ);
	}

	@Transactional(readOnly = true)
	public List<Locadora> buscarTodos() {
		return dao.findAll();
	}

	public void salvar(Locadora locadora) {
		dao.save(locadora);
	}

	public void excluirPorId(Long id) {
		dao.deleteById(id);
	}

	public void excluirPorCNPJ(String CNPJ) {
		dao.deleteByCNPJ(CNPJ);
	}

	//@Transactional(readOnly = true)
	//public boolean locadoraTemLocacao(Long id) {
	//	return !dao.findById(id.longValue()).getLocacoes().isEmpty(); 
	//}
}
