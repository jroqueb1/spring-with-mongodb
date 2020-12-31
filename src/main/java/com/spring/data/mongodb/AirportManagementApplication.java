package com.spring.data.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.mongobee.Mongobee;

@SpringBootApplication
public class AirportManagementApplication {

	private final Environment environment;
	private final MongoTemplate mongoTemplate;
	
	public AirportManagementApplication(Environment environment, MongoTemplate mongoTemplate) {
		this.environment = environment;
		this.mongoTemplate = mongoTemplate;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AirportManagementApplication.class, args);
	}
	
	@Bean
	public Mongobee mongoBee() {
		String mongoUri = environment.getProperty("spring.data.mongodb.uri");
		boolean migrationEnabled = Boolean.parseBoolean(environment.getProperty("app.db.migrations.enabled"));
		
		Mongobee runner = new Mongobee(mongoUri);
		runner.setEnabled(migrationEnabled);
		runner.setChangeLogsScanPackage("com.spring.data.mongodb.db.migrations");
		runner.setChangelogCollectionName("migrations");
		runner.setLockCollectionName("migrations_lock");
		runner.setMongoTemplate(mongoTemplate);
		
		return runner;
	}

//	@Bean
//	public MongoCustomConversions customConversions() {
//		List<Converter<?, ?>> converters = new ArrayList<>();
//		converters.add(new AircraftDbWriteConverter());
//		converters.add(new AircraftDbReadConverter());
//		return new MongoCustomConversions(converters);
//	}
	
}
