package br.com.cotiinformatica.interfaces;

import org.springframework.data.repository.CrudRepository;

import br.com.cotiinformatica.entities.ItemPedido;

public interface IItemPedidoRepository extends CrudRepository<ItemPedido, Integer> {

}
