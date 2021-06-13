package br.com.cotiinformatica.dtos;

import java.util.Date;
import java.util.List;

public class PedidoPostDTO {

	private Date dataPedido;
	private Double valorPedido;
	private Integer idCliente;
	private Integer idEndereco;
	private List<ItemPedidoPostDTO> itensPedido;

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

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public List<ItemPedidoPostDTO> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedidoPostDTO> itensPedido) {
		this.itensPedido = itensPedido;
	}

}

