package br.ufscar.dc.dsw.domain;

import java.util.List;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import br.ufscar.dc.dsw.validation.UniqueDataHora;

@SuppressWarnings("serial")
@Entity
@Table(name = "Locacao")
public class Locacao extends AbstractEntity<Long> {

	@UniqueDataHora (message = "{Unique.locacao.DataHora}")
    @Column(nullable = false, length = 256)
    private String dataHoraLocacao;

	//@DateTimeFormat(pattern = "HH:mm")
    //@Column(nullable = false, length = 256)
    //private Date horaLocacao;

    //@NotBlank(message = "{NotNull.locacao.locadora}")
	@ManyToOne
	@JoinColumn(name = "locadora_id")
	private Locadora locadora;	

    //@NotBlank()
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

    public String getDataHoraLocacao() {
		return dataHoraLocacao;
	}

    public void setDataHoraLocacao(String dataHoraLocacao) {
		this.dataHoraLocacao = dataHoraLocacao;
	}

	//public Date getHoraLocacao() {
	//	return horaLocacao;
	//}
//
    //public void setHoraLocacao(Date horaLocacao) {
	//	System.out.println("Entrou no set hora");
	//	this.horaLocacao = horaLocacao;
	//}

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
