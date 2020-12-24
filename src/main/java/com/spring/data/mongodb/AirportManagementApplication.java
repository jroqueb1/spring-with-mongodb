package com.spring.data.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.spring.data.mongodb.converters.AircraftDbReadConverter;
import com.spring.data.mongodb.converters.AircraftDbWriteConverter;

@SpringBootApplication
public class AirportManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirportManagementApplication.class, args);
	}

//	@Bean
//	public MongoCustomConversions customConversions() {
//		List<Converter<?, ?>> converters = new ArrayList<>();
//		converters.add(new AircraftDbWriteConverter());
//		converters.add(new AircraftDbReadConverter());
//		return new MongoCustomConversions(converters);
//	}
	
}
