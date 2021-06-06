package br.com.cotiinformatica.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.interfaces.IEnderecoRepository;

@Controller
@Transactional
public class EnderecosController {

	@Autowired
	private IEnderecoRepository enderecoRepository;

	private static final String URL = "/api/enderecos";

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> post() {
		return null; // TODO
	}

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<String> put() {
		return null; // TODO
	}

	@CrossOrigin
	@RequestMapping(value = URL + "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		return null; // TODO
	}

	@CrossOrigin
	@RequestMapping(value = URL + "/{idCliente}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> get(@PathVariable("idCliente") Integer idCliente) {
		return null; // TODO
	}
}

