package br.com.cotiinformatica.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Javabean
@Entity
@Table(name = "PRODUTO")
public class Produto {

	// Atributos (dados)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDPRODUTO")
	private Integer idProduto;

	@Column(name = "NOME", length = 150, nullable = false)
	private String nome;

	@Column(name = "PRECO", nullable = false)
	private Double preco;

	@Column(name = "QUANTIDADE", nullable = false)
	private Integer quantidade;

	@Column(name = "DESCRICAO", length = 250, nullable = false)
	private String descricao;

	@Column(name = "FOTO", length = 100, nullable = false)
	private String foto;

	// Produto POSSUI Muitos Itens de Pedido
	@OneToMany(mappedBy = "produto") // atributo 'produto' da classe ItemPedido
	private List<ItemPedido> itensPedido;

	public Produto() {
		// TODO Auto-generated constructor stub
	}

	public Produto(Integer idProduto, String nome, Double preco, Integer quantidade, String descricao, String foto,
			List<ItemPedido> itensPedido) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.foto = foto;
		this.itensPedido = itensPedido;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<ItemPedido> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedido> itensPedido) {
		this.itensPedido = itensPedido;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", nome=" + nome + ", preco=" + preco + ", quantidade=" + quantidade
				+ ", descricao=" + descricao + ", foto=" + foto + "]";
	}

}

