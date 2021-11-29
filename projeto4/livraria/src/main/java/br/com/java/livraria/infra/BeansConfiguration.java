package br.com.java.livraria.infra;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
