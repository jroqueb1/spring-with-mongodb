package com.spring.data.mongodb;

import com.spring.data.mongodb.domain.queries.FlightInformacionQueries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

	private FlightInformacionQueries flightInformacionQueries;
	
	public ApplicationRunner(FlightInformacionQueries flightInformacionQueries) {
		this.flightInformacionQueries = flightInformacionQueries;
	}
	
	@Override
	public void run(String... args) throws Exception {

	}

}
