package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniquedCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name = "Locadora")
public class Locadora extends Usuario {

	@UniquedCNPJ (message = "{Unique.locadora.CNPJ}")
	@NotBlank
	@Size(min = 18, max = 18, message = "{Size.locadora.CNPJ}")
	@Column(nullable = false, unique = true, length = 18)
	private String CNPJ;
	
    @NotBlank(message = "{NotNull.locadora.cidade}")
    @Size(min = 3, max = 256)
    @Column(nullable = false, unique = false, length = 256)
    private String cidade;

	@OneToMany(mappedBy = "locadora")
	private List<Locacao> locacoes;
	
	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		System.out.println("set CNPJ");
		this.CNPJ = CNPJ;
	}

    public String getCidade() {
		return cidade;
	}

    public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}
}
