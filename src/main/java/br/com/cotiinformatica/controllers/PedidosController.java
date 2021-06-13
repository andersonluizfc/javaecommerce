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
import br.com.cotiinformatica.dtos.ItemPedidoPostDTO;
import br.com.cotiinformatica.dtos.PedidoGetDTO;
import br.com.cotiinformatica.dtos.PedidoPostDTO;
import br.com.cotiinformatica.dtos.ProdutoGetDTO;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.entities.Endereco;
import br.com.cotiinformatica.entities.ItemPedido;
import br.com.cotiinformatica.entities.Pedido;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.interfaces.IClienteRepository;
import br.com.cotiinformatica.interfaces.IEnderecoRepository;
import br.com.cotiinformatica.interfaces.IPedidoRepository;
import br.com.cotiinformatica.interfaces.IProdutoRepository;
import br.com.cotiinformatica.senders.MailSender;

@Controller
@Transactional
public class PedidosController {

	@Autowired
	private IPedidoRepository pedidoRepository;

	@Autowired
	private IClienteRepository clienteRepository;

	@Autowired
	private IEnderecoRepository enderecoRepository;

	@Autowired
	private IProdutoRepository produtoRepository;

	@Autowired
	private MailSender mailSender;

	private static final String URL = "/api/pedidos";

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> post(@RequestBody PedidoPostDTO dto) {

		try {

			// buscar o cliente relacionado ao pedido..
			Cliente cliente = clienteRepository.findById(dto.getIdCliente()).get();

			if (cliente == null) { // se o cliente não foi encontrado
				return ResponseEntity.status(HttpStatus.BAD_REQUEST) // HTTP 400
						.body("Cliente não encontrado.");
			}

			// buscar o endereco..
			Endereco endereco = enderecoRepository.findById(dto.getIdEndereco()).get();

			// verificar se o endereço não foi encontrado ou se não pertence ao cliente
			if (endereco == null || endereco.getCliente().getIdCliente() != cliente.getIdCliente()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST) // HTTP 400
						.body("Endereço inválido.");
			}

			// buscar os itens do pedido
			List<ItemPedido> itensPedido = new ArrayList<ItemPedido>();

			for (ItemPedidoPostDTO item : dto.getItensPedido()) {

				// obter o produto no banco de dados para o item do pedido
				Produto produto = produtoRepository.findById(item.getIdProduto()).get();

				// verificar se o produto não foi encontrado
				if (produto == null) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST) // HTTP 400
							.body("Item de Pedido inválido.");
				}

				ItemPedido itemPedido = new ItemPedido();
				itemPedido.setProduto(produto);
				itemPedido.setQuantidade(item.getQuantidade());

				itensPedido.add(itemPedido);
			}

			// cadastrar o pedido..
			Pedido pedido = new Pedido();

			pedido.setDataPedido(dto.getDataPedido());
			pedido.setValorPedido(dto.getValorPedido());
			pedido.setCliente(cliente);
			pedido.setEndereco(endereco);
			pedido.setItensPedido(itensPedido);

			// gravar o pedido no banco de dados
			pedidoRepository.save(pedido);

			// enviando email de confirmação
			enviarConfirmacaoPedido(pedido);

			return ResponseEntity.status(HttpStatus.CREATED) // HTTP 201
					.body("Pedido realizado com sucesso.");
		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Erro ao cadastrar pedido: " + e.getMessage());
		}
	}

	@CrossOrigin
	@RequestMapping(value = URL + "/{email}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<PedidoGetDTO>> get(@PathVariable("email") String email) {

		try {
						
			List<PedidoGetDTO> result = new ArrayList<PedidoGetDTO>();
			
			//buscar o pedido no banco de dados atraves do id do cliente
			for(Pedido pedido : pedidoRepository.findAllByCliente(email)) {
				
				PedidoGetDTO dto = new PedidoGetDTO();
				
				//dados do pedido
				dto.setIdPedido(pedido.getIdPedido());
				dto.setDataPedido(pedido.getDataPedido());
				dto.setValorPedido(pedido.getValorPedido());

				//dados do cliente do pedido
				dto.setCliente(new ClienteGetDTO());
				dto.getCliente().setIdCliente(pedido.getCliente().getIdCliente());
				dto.getCliente().setNome(pedido.getCliente().getNome());
				dto.getCliente().setEmail(pedido.getCliente().getEmail());
				dto.getCliente().setCpf(pedido.getCliente().getCpf());
				
				//dados do endereço
				dto.setEndereco(new EnderecoGetDTO());
				dto.getEndereco().setIdEndereco(pedido.getEndereco().getIdEndereco());
				dto.getEndereco().setLogradouro(pedido.getEndereco().getLogradouro());
				dto.getEndereco().setComplemento(pedido.getEndereco().getComplemento());
				dto.getEndereco().setNumero(pedido.getEndereco().getNumero());
				dto.getEndereco().setBairro(pedido.getEndereco().getBairro());
				dto.getEndereco().setCidade(pedido.getEndereco().getCidade());
				dto.getEndereco().setEstado(pedido.getEndereco().getEstado());
				dto.getEndereco().setCep(pedido.getEndereco().getCep());
				
				dto.setProdutos(new ArrayList<ProdutoGetDTO>());
				
				//produtos do pedido (itens do pedido)
				for(ItemPedido item : pedido.getItensPedido()) {
					
					ProdutoGetDTO produto = new ProdutoGetDTO();
					
					produto.setIdProduto(item.getProduto().getIdProduto());
					produto.setNome(item.getProduto().getNome());
					produto.setPreco(item.getProduto().getPreco());
					produto.setQuantidade(item.getQuantidade());
					produto.setDescricao(item.getProduto().getDescricao());
					produto.setFoto(item.getProduto().getFoto());
					
					dto.getProdutos().add(produto);
				}
				
				result.add(dto);
			}
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(result);
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}		
	}

	// método para enviar o email com os dados do pedido..
	private void enviarConfirmacaoPedido(Pedido pedido) {

		String mensagem = "Ola, " + pedido.getCliente().getNome() + "\n\n";
		mensagem += "Seu pedido foi realizado com sucesso!\n\n";

		mensagem += "Código do pedido: " + pedido.getIdPedido() + "\n";
		mensagem += "Data: " + pedido.getDataPedido() + "\n";
		mensagem += "Valor: " + pedido.getValorPedido() + "\n\n";

		mensagem += "Cliente: " + pedido.getCliente().getNome() + "\n";
		mensagem += "Email: " + pedido.getCliente().getEmail() + "\n";
		mensagem += "Cpf: " + pedido.getCliente().getCpf() + "\n\n";

		mensagem += "Itens do Pedido:\n\n";

		for (ItemPedido item : pedido.getItensPedido()) {

			mensagem += "Produto: " + item.getProduto().getNome() + "\n";
			mensagem += "Preço: " + item.getProduto().getPreco() + "\n";
			mensagem += "Quantidade: " + item.getQuantidade() + "\n";
			mensagem += "Descrição: " + item.getProduto().getDescricao() + "\n\n";
		}

		mailSender.sendMessage(pedido.getCliente().getEmail(), "Pedido realizado com sucesso", mensagem);
	}

}

