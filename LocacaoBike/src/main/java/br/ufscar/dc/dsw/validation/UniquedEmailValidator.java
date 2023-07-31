package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;

@Component
public class UniquedEmailValidator implements ConstraintValidator<UniquedEmail, String> {

	@Autowired
	private IUsuarioDAO UsuarioDao;

	@Override
	public boolean isValid(String Email, ConstraintValidatorContext context) {
		if (UsuarioDao != null) {
			System.out.println("Entrou no validador de email");
			Usuario usuario = UsuarioDao.findByEmail(Email);
			return usuario == null;
		} else {
			// Durante a execução da classe LocadoraMvcApplication
			// não há injeção de dependência
			return true;
		}
	}
}