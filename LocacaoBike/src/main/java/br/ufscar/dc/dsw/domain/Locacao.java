package br.ufscar.dc.dsw.domain;

import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "Locacao")
public class Locacao extends AbstractEntity<Long> {

	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false, length = 256)
    private String dataLocacao;

	@DateTimeFormat(pattern = "HH:mm")
    @Column(nullable = false, length = 256)
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
		System.out.println("Entrou no set hora");
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
