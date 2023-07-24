package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;

@Component
public class UniquedTelefoneValidator implements ConstraintValidator<UniquedTelefone, String> {

	@Autowired
	private ILocadoraDAO locadoraDao;

	@Override
	public boolean isValid(String telefone, ConstraintValidatorContext context) {
		if (locadoraDao != null) {
			Locadora locadora = locadoraDao.findByTelefone(telefone);
			return locadora == null;
		} else {
			// Durante a execução da classe LocadoraMvcApplication
			// não há injeção de dependência
			return true;
		}

	}
}