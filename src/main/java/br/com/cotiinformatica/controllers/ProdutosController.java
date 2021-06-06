package br.com.cotiinformatica.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public ResponseEntity<String> get() {
		return null; // TODO
	}
}

