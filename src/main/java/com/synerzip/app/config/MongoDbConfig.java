package com.synerzip.app.config;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Configuration
public class MongoDbConfig extends AbstractMongoConfiguration {

	@Value("${spring.data.mongodb.host}")
	private String host;

	@Value("${spring.data.mongodb.port}")
	private int port;

	@Value("${spring.data.mongodb.db}")
	private String db;

	@Bean
	public Mongo mongo() throws UnknownHostException {
		ServerAddress serverAdress = new ServerAddress(host, port);
		return new MongoClient(serverAdress);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		return new MongoTemplate(mongo(), getDatabaseName());
	}

	@Override
	protected String getDatabaseName() {
		return db;
	}
}
