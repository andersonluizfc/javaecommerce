package br.com.cotiinformatica.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//Javabean
@Entity
@Table(name = "ITEMPEDIDO")
public class ItemPedido {

	// Atributos (dados)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDITEMPEDIDO")
	private Integer idItemPedido;

	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;

	// Item Pedido PERTENCE a 1 Pedido
	@ManyToOne
	@JoinColumn(name = "IDPEDIDO", nullable = false)
	private Pedido pedido;

	// Item Pedido POSSUI 1 Produto
	@ManyToOne
	@JoinColumn(name = "IDPRODUTO", nullable = false)
	private Produto produto;

	public ItemPedido() {
		// TODO Auto-generated constructor stub
	}

	public ItemPedido(Integer idItemPedido, Integer quantidade, Pedido pedido, Produto produto) {
		super();
		this.idItemPedido = idItemPedido;
		this.quantidade = quantidade;
		this.pedido = pedido;
		this.produto = produto;
	}

	public Integer getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Integer idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemPedido [idItemPedido=" + idItemPedido + ", quantidade=" + quantidade + "]";
	}

}

