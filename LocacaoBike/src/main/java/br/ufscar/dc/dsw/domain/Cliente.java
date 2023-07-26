package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniquedCPF;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario {

	@UniquedCPF (message = "{Unique.cliente.CPF}")
	@NotBlank
	@Size(min = 14, max = 14, message = "{Size.cliente.CPF}")
	@Column(nullable = true, unique = true, length = 14)
	private String CPF;

    @NotBlank(message = "{NotNull.cliente.sexo}")
    @Size(min = 8, max = 10)
    @Column(nullable = false, unique = false, length = 10)
    private String sexo;

    @NotBlank(message = "{NotNull.cliente.dataNascimento}")
    @Size(min = 3, max = 256)
    @Column(nullable = false, unique = false, length = 256)
    private String dataNascimento;

	@OneToMany(mappedBy = "cliente")
	private List<Locacao> locacoes;
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

    public String getSexo() {
		return sexo;
	}

    public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

    public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Locacao> getLocacoes() {
		return locacoes;
	}

	public void setLocacoes(List<Locacao> locacoes) {
		this.locacoes = locacoes;
	}
}
