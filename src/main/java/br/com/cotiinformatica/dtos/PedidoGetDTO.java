package br.com.cotiinformatica.dtos;

import java.util.Date;
import java.util.List;

public class PedidoGetDTO {

	private Integer idPedido;
	private Date dataPedido;
	private Double valorPedido;

	private ClienteGetDTO cliente;
	private EnderecoGetDTO endereco;
	private List<ProdutoGetDTO> produtos;

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

	public ClienteGetDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteGetDTO cliente) {
		this.cliente = cliente;
	}

	public EnderecoGetDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoGetDTO endereco) {
		this.endereco = endereco;
	}

	public List<ProdutoGetDTO> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ProdutoGetDTO> produtos) {
		this.produtos = produtos;
	}

}

