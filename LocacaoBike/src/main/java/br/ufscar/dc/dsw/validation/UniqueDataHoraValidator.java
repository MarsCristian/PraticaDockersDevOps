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
		System.out.println("ENtrou no validador de data e hora");
		if (LocacaoDao != null) {
			Locacao locacao = LocacaoDao.findByDataHora(dataHora);
			System.out.println(locacao);
			if (locacao != null) {
				System.out.println("A locacao do find é: ");
				System.out.println("	" + locacao.getCliente());
				System.out.println("	" + locacao.getLocadora());
				System.out.println("	" + locacao.getDataHora());
			} 
			return locacao == null;
		} else {
			// Durante a execução da classe LocacaoMvcApplication
			// não há injeção de dependência
			return true;
		}

	}
}