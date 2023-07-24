package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Cliente;

@Component
public class UniquedCPFValidator implements ConstraintValidator<UniquedCPF, String> {

	@Autowired
	private IClienteDAO clienteDao;

	@Override
	public boolean isValid(String CPF, ConstraintValidatorContext context) {
		if (clienteDao != null) {
			Cliente cliente = clienteDao.findByCPF(CPF);
			return cliente == null;
		} else {
			// Durante a execução da classe LocadoraMvcApplication
			// não há injeção de dependência
			return true;
		}

	}
}