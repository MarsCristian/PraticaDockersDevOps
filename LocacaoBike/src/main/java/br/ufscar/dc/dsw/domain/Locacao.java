package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    //@NotBlank(message = "{NotNull.locacao.locadora}")
	@ManyToOne
	@JoinColumn(name = "locadora_id")
	private Locadora locadora;	

    //@NotBlank()
	@ManyToOne
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

	public Locadora getLocadora() {
		return locadora;
	}

	public void setLocadora(Locadora locadora) {
		this.locadora = locadora;
	}

    public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
