package br.ufscar.dc.dsw.validation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.ILocacaoDAO;
import br.ufscar.dc.dsw.domain.Locacao;

@Component
public class UniqueLocacaoValidator implements ConstraintValidator<UniqueLocacao, Locacao> {

	@Autowired
	private ILocacaoDAO LocacaoDAO;
    
	@Override
	public boolean isValid(Locacao locacao, ConstraintValidatorContext context) {
		if (LocacaoDAO != null) {
			System.out.println("Entrou no validador de locacao");
            System.out.println(locacao.getDataHora());

            List<Locacao> locacoesCliente = locacao.getCliente().getLocacoes();
            if (locacoesCliente == null) 
                return true;
                
            for (Locacao atual : locacoesCliente) {
                //System.out.println("Locacao KKKKKKKKKKKKKKKKKKKKK");
                //System.out.println(atual.getCliente());
                //System.out.println(atual.getLocadora());
                //System.out.println(atual.getDataHora() + " = " + locacao.getDataHora());  

                if (atual.getDataHora().equals(locacao.getDataHora())) {
                    System.out.println("Esse cliente já possui essa data/hora reservada");
                    return false;
                }            
            }

            List<Locacao> locacoesLocadora = locacao.getLocadora().getLocacoes();
            if (locacoesLocadora == null) 
                return true;
                
            for (Locacao atual : locacoesLocadora) {
                //System.out.println("Locacao KKKKKKKKKKKKKKKKKKKKK");
                //System.out.println(atual.getCliente());
                //System.out.println(atual.getLocadora());
                //System.out.println(atual.getDataHora() + " = " + locacao.getDataHora());  

                if (atual.getDataHora().equals(locacao.getDataHora())) {
                    System.out.println("Essa locadora já possui essa data/hora reservada");
                    return false;
                }            
            }

            return true;
		} else {
			// Durante a execução da classe LocadoraMvcApplication
			// não há injeção de dependência
			return true;
		}

	}
}