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
@Table(name = "CLIENTE")
public class Cliente {

	// atributos (dados)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCLIENTE")
	private Integer idCliente;

	@Column(name = "NOME", length = 150, nullable = false)
	private String nome;

	@Column(name = "EMAIL", length = 100, nullable = false, unique = true)
	private String email;

	@Column(name = "SENHA", length = 50, nullable = false)
	private String senha;

	@Column(name = "CPF", length = 11, nullable = false, unique = true)
	private String cpf;

	// Cliente TEM Muitos Endereços
	@OneToMany(mappedBy = "cliente") //atributo 'cliente' da classe Endereco
	private List<Endereco> enderecos;

	// Cliente TEM Muitos Pedidos
	@OneToMany(mappedBy = "cliente") //atributo 'cliente' da classe Pedido
	private List<Pedido> pedidos;

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(Integer idCliente, String nome, String email, String senha, String cpf, List<Endereco> enderecos,
			List<Pedido> pedidos) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.cpf = cpf;
		this.enderecos = enderecos;
		this.pedidos = pedidos;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf="
				+ cpf + "]";
	}

}

