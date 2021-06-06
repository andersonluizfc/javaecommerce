package br.com.cotiinformatica.interfaces;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.cotiinformatica.entities.Cliente;

public interface IClienteRepository extends CrudRepository<Cliente, Integer> {

	@Query("from Cliente c where c.email = :email")
	Cliente findByEmail(@Param("email") String email) throws Exception;

	@Query("from Cliente c where c.cpf = :cpf")
	Cliente findByCpf(@Param("cpf") String cpf) throws Exception;

	@Query("from Cliente c where c.email = :email and c.senha = :senha")
	Cliente findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha) throws Exception;
}

