package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;

@Component
public class UniquedTelefoneValidator implements ConstraintValidator<UniquedTelefone, String> {

	@Autowired
	private IUsuarioDAO UsuarioDao;

	@Override
	public boolean isValid(String telefone, ConstraintValidatorContext context) {
		if (UsuarioDao != null) {
			System.out.println("Entrou no validador de telefone");
			Usuario usuario = UsuarioDao.findByTelefone(telefone);
			return usuario == null;
		} else {
			// Durante a execução da classe LocadoraMvcApplication
			// não há injeção de dependência
			return true;
		}

	}
}