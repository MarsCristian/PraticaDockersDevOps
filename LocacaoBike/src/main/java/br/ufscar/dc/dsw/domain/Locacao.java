package br.ufscar.dc.dsw.domain;

import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import br.ufscar.dc.dsw.validation.UniqueDataHora;
import br.ufscar.dc.dsw.validation.UniqueLocacao;

@SuppressWarnings("serial")
@Entity
@Table(name = "Locacao")
@UniqueLocacao (message = "{Unique.locacao.clientedatahora}")
public class Locacao extends AbstractEntity<Long> {

	//@UniqueDataHora (message = "{Unique.locacao.dataHora}")
    @Column(nullable = false, length = 256)
    private String dataHora;

    //@NotBlank(message = "{NotNull.locacao.locadora}")
	@ManyToOne
	@JoinColumn(name = "locadora_id")
	private Locadora locadora;	

    //@NotBlank()
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

    public String getDataHora() {
		return dataHora;
	}

    public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
		System.out.println("entrou no set data hora");
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
