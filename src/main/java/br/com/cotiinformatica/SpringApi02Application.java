package br.com.cotiinformatica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "br.com.cotiinformatica" })
public class SpringApi02Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringApi02Application.class, args);
	}

	private static Class<SpringApi02Application> applicationClass = SpringApi02Application.class;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(applicationClass);
	}

}
