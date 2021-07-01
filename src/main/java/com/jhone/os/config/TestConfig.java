package com.jhone.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jhone.os.services.DBServices;


@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBServices dbservice;
	@Bean
	public void instanciaDB() {
		this.dbservice.instanciaDB();
	}
}
