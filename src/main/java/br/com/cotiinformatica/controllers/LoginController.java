package br.com.cotiinformatica.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.cotiinformatica.dtos.LoginPostDTO;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.filters.JwtAuthorizationFilter;
import br.com.cotiinformatica.interfaces.IClienteRepository;
import br.com.cotiinformatica.security.Criptografia;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
@Transactional
public class LoginController {

	@Autowired
	private IClienteRepository clienteRepository;

	private static final String URL = "/api/login";

	@CrossOrigin
	@RequestMapping(value = URL, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> post(@RequestBody LoginPostDTO dto) {

		try {

			// buscar o cliente no banco de dados atraves do email e da senha
			Cliente cliente = clienteRepository.findByEmailAndSenha(dto.getEmail(),
					Criptografia.criptografar(dto.getSenha()));

			// verificar se o cliente não foi encontrado..
			if (cliente == null) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED) // HTTP 401
						.body("Acesso negado. Usuário inválido.");
			} else {

				return ResponseEntity.status(HttpStatus.OK).body(getJWTToken(cliente.getEmail()));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	// método para gerar o TOKEN de autenticação do usuário..
	private String getJWTToken(String email) {

		// para gerarmos o TOKEN, precisamos usar uma palavra secreta (secret key)
		// garante que os TOKENS gerados pelo nosso projeto são unicos, ou seja,
		// não podem ser falsificados.

		String secretKey = JwtAuthorizationFilter.SECRET;
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		String token = Jwts.builder().setId("COTI_JWT").setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return token;
	}
}
