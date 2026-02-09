package com.eureca.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: this class is responsible for TODO
 * vikas
 * @created on 14 Dec 2025
 * @version 1.0
 */
@Configuration
public class MapperConfig {

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}