package br.com.cotiinformatica.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//Javabean
@Entity
@Table(name = "PEDIDO")
public class Pedido {

	// Atributos (dados)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPEDIDO")
	private Integer idPedido;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATAPEDIDO", nullable = false)
	private Date dataPedido;

	@Column(name = "VALORPEDIDO", nullable = false)
	private Double valorPedido;

	// Pedido PERTENCE a 1 Cliente
	@ManyToOne
	@JoinColumn(name = "IDCLIENTE", nullable = false)
	private Cliente cliente;

	// Pedido POSSUI 1 Endereco
	@ManyToOne
	@JoinColumn(name = "IDENDERECO", nullable = false)
	private Endereco endereco;

	// Pedido POSSUI Muitos Itens do Pedido
	@OneToMany(mappedBy = "pedido") // atributo 'pedido' na classe ItemPedido
	private List<ItemPedido> itensPedido;

	public Pedido() {
		// TODO Auto-generated constructor stub
	}

	public Pedido(Integer idPedido, Date dataPedido, Double valorPedido, Cliente cliente, Endereco endereco,
			List<ItemPedido> itensPedido) {
		super();
		this.idPedido = idPedido;
		this.dataPedido = dataPedido;
		this.valorPedido = valorPedido;
		this.cliente = cliente;
		this.endereco = endereco;
		this.itensPedido = itensPedido;
	}

	public Integer getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(Double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", dataPedido=" + dataPedido + ", valorPedido=" + valorPedido + "]";
	}

}

