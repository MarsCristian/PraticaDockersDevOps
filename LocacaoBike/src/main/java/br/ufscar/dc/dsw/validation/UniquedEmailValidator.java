package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.ILocadoraDAO;
import br.ufscar.dc.dsw.domain.Locadora;

@Component
public class UniquedEmailValidator implements ConstraintValidator<UniquedEmail, String> {

	@Autowired
	private ILocadoraDAO LocadoraDao;

	@Override
	public boolean isValid(String Email, ConstraintValidatorContext context) {
		if (LocadoraDao != null) {
			Locadora locadora = LocadoraDao.findByEmail(Email);
			return locadora == null;
		} else {
			// Durante a execução da classe LocadoraMvcApplication
			// não há injeção de dependência
			return true;
		}

	}
}