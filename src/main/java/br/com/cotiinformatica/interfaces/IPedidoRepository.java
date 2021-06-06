package br.com.cotiinformatica.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Endereco;
import br.com.cotiinformatica.entities.Pedido;

public interface IPedidoRepository extends CrudRepository<Pedido, Integer> {

	@Query("from Pedido p where p.cliente.idCliente = :idCliente")
	List<Endereco> findAllByCliente(@Param("idCliente") Integer idCliente);
}
