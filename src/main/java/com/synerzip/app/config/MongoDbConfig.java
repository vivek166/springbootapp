package com.synerzip.app.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

@Configuration
@PropertySource("classpath:application.properties")
public class MongoDbConfig {

	@Value("${spring.data.mongodb.host}")
	private String host;
	
	@Value("${spring.data.mongodb.db}")
	private String db;

	@Bean
	public Mongo mongo() throws UnknownHostException {
		return new MongoClient(host);
	}

	public @Bean
	MongoTemplate mongoTemplate() throws UnknownHostException {
		return new MongoTemplate(mongo(), db);
	}
}
