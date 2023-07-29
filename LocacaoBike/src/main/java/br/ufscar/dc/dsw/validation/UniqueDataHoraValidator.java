package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;

@Component
public class UniqueDataHoraValidator implements ConstraintValidator<UniqueDataHora, String> {

	@Autowired
	private ILocacaoDAO LocacaoDao;

	@Override
	public boolean isValid(String dataHora, ConstraintValidatorContext context) {
		if (LocacaoDao != null) {
			Locacao locacao = LocacaoDao.findByDataHora(dataHora);
			return locacao == null;
		} else {
			// Durante a execução da classe LocadoraMvcApplication
			// não há injeção de dependência
			return true;
		}

	}
}