package br.com.cotiinformatica.controllers;

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
import br.com.cotiinformatica.dtos.ClientePostDTO;
import br.com.cotiinformatica.dtos.ClientePutDTO;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.interfaces.IClienteRepository;
import br.com.cotiinformatica.security.Criptografia;
import br.com.cotiinformatica.senders.MailSender;

@Controller
@Transactional
public class ClientesController {

	@Autowired
	private IClienteRepository clienteRepository;
	
	@Autowired
	private MailSender mailSender;

	private static final String URL = "/api/clientes";

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> post(@RequestBody ClientePostDTO dto) {

		try {
			
			//verificar se o email informado já existe no banco de dados..
			if(clienteRepository.findByEmail(dto.getEmail()) != null) {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST) //HTTP 400
						.body("O email informado já encontra-se cadastrado.");
			}
			
			Cliente cliente = new Cliente();
			
			cliente.setNome(dto.getNome());
			cliente.setEmail(dto.getEmail());
			cliente.setCpf(dto.getCpf());
			cliente.setSenha(Criptografia.criptografar(dto.getSenha()));
			
			//gravar o cliente no banco de dados
			clienteRepository.save(cliente);
			
			//enviar um email de confirmação para o cliente
			mailSender.sendMessage(cliente.getEmail(), 
					"Conta de cliente cadastrada com sucesso", 
					"Olá, " + cliente.getNome() + "\n\nSua conta foi criada com sucesso em COTI Informática.");
			
			return ResponseEntity
					.status(HttpStatus.CREATED) //HTTP 201
					.body("Cliente cadastrado com sucesso.");			
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> put(@RequestBody ClientePutDTO dto) {
		
		try {
			
			//buscar o cliente no banco de dados atraves do ID..
			Cliente cliente = clienteRepository.findById(dto.getIdCliente()).get();
			
			//modificando os dados do cliente
			cliente.setNome(dto.getNome());
			cliente.setCpf(dto.getCpf());
			
			//atualizando no banco de dados..
			clienteRepository.save(cliente);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Cliente atualizado com sucesso.");
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

	@CrossOrigin
	@RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		
		try {
			
			//buscar o cliente no banco de dados atraves do ID..
			Cliente cliente = clienteRepository.findById(id).get();
						
			//excluindo no banco de dados..
			clienteRepository.delete(cliente);
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body("Cliente excluído com sucesso.");
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}

	@CrossOrigin
	@RequestMapping(value = URL + "/{email}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ClienteGetDTO> get(@PathVariable("email") String email) {

		try {
			
			//consultar os dados do cliente baseado no email
			Cliente cliente = clienteRepository.findByEmail(email);
			
			//verificar se o cliente não foi encontrado..
			if(cliente == null) {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST) //HTTP 400
						.body(null); 
			}
			
			//transferir os dados do cliente para o DTO..
			ClienteGetDTO dto = new ClienteGetDTO();
			
			dto.setIdCliente(cliente.getIdCliente());
			dto.setNome(cliente.getNome());
			dto.setEmail(cliente.getEmail());
			dto.setCpf(cliente.getCpf());
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(dto);
		}
		catch(Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}
	}
}
