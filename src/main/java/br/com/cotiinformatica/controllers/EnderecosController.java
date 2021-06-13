package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.ClienteGetDTO;
import br.com.cotiinformatica.dtos.EnderecoGetDTO;
import br.com.cotiinformatica.dtos.EnderecoPostDTO;
import br.com.cotiinformatica.dtos.EnderecoPutDTO;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Endereco;
import br.com.cotiinformatica.interfaces.IClienteRepository;
import br.com.cotiinformatica.interfaces.IEnderecoRepository;

@Controller
@Transactional
public class EnderecosController {

	@Autowired
	private IEnderecoRepository enderecoRepository;

	@Autowired
	private IClienteRepository clienteRepository;

	private static final String URL = "/api/enderecos";

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> post(@RequestBody EnderecoPostDTO dto) {

		try {

			Endereco endereco = new Endereco();

			endereco.setLogradouro(dto.getLogradouro());
			endereco.setNumero(dto.getNumero());
			endereco.setComplemento(dto.getComplemento());
			endereco.setBairro(dto.getBairro());
			endereco.setCidade(dto.getCidade());
			endereco.setEstado(dto.getEstado());
			endereco.setCep(dto.getCep());

			Cliente cliente = clienteRepository.findById(dto.getIdCliente()).get();
			endereco.setCliente(cliente); // Relacionando o cliente ao endereço

			// gravando o endereço..
			enderecoRepository.save(endereco);

			return ResponseEntity.status(HttpStatus.CREATED) // HTTP 201
					.body("Endereço cadastrado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao cadastrar endereço: " + e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> put(@RequestBody EnderecoPutDTO dto) {

		try {

			// buscando o endereço na base de dados atraves do ID..
			Endereco endereco = enderecoRepository.findById(dto.getIdEndereco()).get();

			endereco.setLogradouro(dto.getLogradouro());
			endereco.setNumero(dto.getNumero());
			endereco.setComplemento(dto.getComplemento());
			endereco.setBairro(dto.getBairro());
			endereco.setCidade(dto.getCidade());
			endereco.setEstado(dto.getEstado());
			endereco.setCep(dto.getCep());

			// atualizando o endereço..
			enderecoRepository.save(endereco);

			return ResponseEntity.status(HttpStatus.OK) // HTTP 200
					.body("Endereço atualizado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao cadastrar endereço: " + e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {

		try {

			// buscando o endereço na base de dados atraves do ID..
			Endereco endereco = enderecoRepository.findById(id).get();

			// excluindo o endereço..
			enderecoRepository.delete(endereco);

			return ResponseEntity.status(HttpStatus.OK) // HTTP 200
					.body("Endereço excluído com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao cadastrar endereço: " + e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping(value = URL + "/{idCliente}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EnderecoGetDTO>> get(@PathVariable("idCliente") Integer idCliente) {

		try {
		
			List<EnderecoGetDTO> result = new ArrayList<EnderecoGetDTO>();
			
			for(Endereco endereco : enderecoRepository.findAllByCliente(idCliente)) {
				
				EnderecoGetDTO dto = new EnderecoGetDTO();
				
				dto.setIdEndereco(endereco.getIdEndereco());
				dto.setLogradouro(endereco.getLogradouro());
				dto.setNumero(endereco.getNumero());
				dto.setComplemento(endereco.getComplemento());
				dto.setBairro(endereco.getBairro());
				dto.setCidade(endereco.getCidade());
				dto.setEstado(endereco.getEstado());
				dto.setCep(endereco.getCep());
				
				dto.setCliente(new ClienteGetDTO());
				dto.getCliente().setIdCliente(endereco.getCliente().getIdCliente());
				dto.getCliente().setNome(endereco.getCliente().getNome());
				dto.getCliente().setEmail(endereco.getCliente().getEmail());
				dto.getCliente().setCpf(endereco.getCliente().getCpf());
				
				result.add(dto);
			}
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(result);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
}

