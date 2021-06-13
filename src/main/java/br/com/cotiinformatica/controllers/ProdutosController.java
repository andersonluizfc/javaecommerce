package br.com.cotiinformatica.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.ProdutoGetDTO;
import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.interfaces.IProdutoRepository;

@Controller
@Transactional
public class ProdutosController {

	@Autowired
	private IProdutoRepository produtoRepository;

	private static final String URL = "/api/produtos";

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<ProdutoGetDTO>> get() {
		
		try {
			
			List<ProdutoGetDTO> result = new ArrayList<ProdutoGetDTO>();
			
			for(Produto produto : produtoRepository.findAll()) {
				ProdutoGetDTO dto = new ProdutoGetDTO();
				
				dto.setIdProduto(produto.getIdProduto());
				dto.setNome(produto.getNome());
				dto.setPreco(produto.getPreco());
				dto.setQuantidade(produto.getQuantidade());
				dto.setFoto(produto.getFoto());
				dto.setDescricao(produto.getDescricao());
				
				result.add(dto);
			}
			
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(result);
		}
		catch(Exception e) {
			
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);
		}		
	}
}

