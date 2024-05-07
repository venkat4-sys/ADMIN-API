package com.tcs.config;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "IES-ADMIN")
@Data
public class AppProperties {
	
	Map<String,String> messages=new HashMap<>();

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
