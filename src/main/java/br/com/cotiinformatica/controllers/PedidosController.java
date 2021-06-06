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

import br.com.cotiinformatica.interfaces.IPedidoRepository;

@Controller
@Transactional
public class PedidosController {

	@Autowired
	private IPedidoRepository pedidoRepository;
	
	private static final String URL = "/api/pedidos";

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> post() {
		return null; // TODO
	}

	@CrossOrigin
	@RequestMapping(value = URL + "/{idCliente}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> get(@PathVariable("idCliente") Integer email) {
		return null; // TODO
	}
}
