package br.com.cotiinformatica.entities;

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

//Javabean
@Entity
@Table(name = "ENDERECO")
public class Endereco {

	// Atributos (dados)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENDERECO")
	private Integer idEndereco;

	@Column(name = "LOGRADOURO", length = 250, nullable = false)
	private String logradouro;

	@Column(name = "NUMERO", length = 15, nullable = false)
	private String numero;

	@Column(name = "COMPLEMENTO", length = 100, nullable = false)
	private String complemento;

	@Column(name = "BAIRRO", length = 150, nullable = false)
	private String bairro;

	@Column(name = "CIDADE", length = 150, nullable = false)
	private String cidade;

	@Column(name = "ESTADO", length = 50, nullable = false)
	private String estado;

	@Column(name = "CEP", length = 8, nullable = false)
	private String cep;

	// Endereço PERTENCE a 1 Cliente
	@ManyToOne
	@JoinColumn(name = "IDCLIENTE", nullable = false) // chave estrangeira
	private Cliente cliente;

	// Endereco POSSUI Muitos Pedidos
	@OneToMany(mappedBy = "endereco") // Atributo 'endereco' da classe Pedido
	private List<Pedido> pedidos;

	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(Integer idEndereco, String logradouro, String numero, String complemento, String bairro,
			String cidade, String estado, String cep, Cliente cliente, List<Pedido> pedidos) {
		super();
		this.idEndereco = idEndereco;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.cliente = cliente;
		this.pedidos = pedidos;
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Endereco [idEndereco=" + idEndereco + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", complemento=" + complemento + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado
				+ ", cep=" + cep + "]";
	}

}

