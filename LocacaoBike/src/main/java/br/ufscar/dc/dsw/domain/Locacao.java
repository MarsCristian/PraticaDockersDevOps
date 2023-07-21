package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniquedCPF;
import br.ufscar.dc.dsw.validation.UniquedCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name = "Locacao")
public class Locacao extends AbstractEntity<Long> {

    @NotBlank(message = "{NotNull.locacao.dataLocacao}")
    @Size(min = 3, max = 256)
    @Column(nullable = false, unique = false, length = 256)
    private String dataLocacao;

    @NotBlank(message = "{NotNull.locacao.horaLocacao}")
    @Size(min = 3, max = 256)
    @Column(nullable = false, unique = false, length = 256)
    private String horaLocacao;

    @NotBlank(message = "{NotNull.locacao.locadora}")
	@ManyToMany
    @JoinColumn(name = "locadora_id")
	private Locadora locadora;

    @NotBlank()
	@ManyToMany
    @JoinColumn(name = "cliente_id")
	private Cliente cliente;

    public String getDataLocacao() {
		return dataLocacao;
	}

    public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

    public String getHoraLocacao() {
		return horaLocacao;
	}

    public void setHoraLocacao(String horaLocacao) {
		this.horaLocacao = horaLocacao;
	}

	public List<Locadora> getLocadoras() {
		return locadoras;
	}

	public void setLocadoras(List<Locadora> locadoras) {
		this.locadoras = locadoras;
	}

    public List<Cliente> getClientes() {
		return cliente;
	}

	public void setClientes(List<Cliente> cliente) {
		this.cliente = cliente;
	}
}
