package br.com.cotiinformatica.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Endereco;

public interface IEnderecoRepository extends CrudRepository<Endereco, Integer> {

	@Query("from Endereco e join e.cliente c where e.cliente.idCliente = :idCliente")
	List<Endereco> findAllByCliente(@Param("idCliente") Integer idCliente);
}
