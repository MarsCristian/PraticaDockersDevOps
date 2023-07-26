package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Service
@Transactional(readOnly = false)
public class ClienteService implements IClienteService {

	@Autowired
	IClienteDAO dao;
	
	@Transactional(readOnly = true)
	public Cliente buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

    @Transactional(readOnly = true)
	public Cliente buscarPorCPF(String CPF) {
		return dao.findByCPF(CPF);
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscarTodos() {
		return dao.findAll();
	}

	public void salvar(Cliente cliente) {
		dao.save(cliente);
	}

	public void excluirPorId(Long id) {
		dao.deleteById(id);
	}

	public void excluirPorCPF(String CPF) {
		dao.deleteByCPF(CPF);
	}


}
