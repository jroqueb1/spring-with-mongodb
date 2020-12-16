package com.spring.data.mongodb;

import com.spring.data.mongodb.domain.Aircraft;
import com.spring.data.mongodb.domain.FlightType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.spring.data.mongodb.domain.FlightInformation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@Order(1)
public class DatabaseSeederRunner implements CommandLineRunner {
	
	private MongoTemplate mongoTemplate;
	
	public DatabaseSeederRunner(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	public void run(String... args) throws Exception {
		empty();
		seed();
	}
	
	private void seed() {
		FlightInformation f1 = new FlightInformation();
		f1.setDelayed(false);
		f1.setDepartureCity("Rome");
		f1.setDestinationCity("Paris");
		f1.setDepartureDate(LocalDate.of(2020, 12, 14));
		f1.setType(FlightType.International);
		f1.setDurationMin(80);
		f1.setAircraft(new Aircraft("737", 180));

		FlightInformation f2 = new FlightInformation();
		f2.setDelayed(false);
		f2.setDepartureCity("New York");
		f2.setDestinationCity("Copenhagen");
		f2.setDepartureDate(LocalDate.of(2020, 12, 16));
		f2.setType(FlightType.International);
		f2.setDurationMin(600);
		f2.setAircraft(new Aircraft("747", 300));

		FlightInformation f3 = new FlightInformation();
		f3.setDelayed(false);
		f3.setDepartureCity("Bruxellas");
		f3.setDestinationCity("Bucharest");
		f3.setDepartureDate(LocalDate.of(2021, 1, 12));
		f3.setType(FlightType.International);
		f3.setDurationMin(150);
		f3.setAircraft(new Aircraft("A320", 160));

		FlightInformation f4 = new FlightInformation();
		f4.setDelayed(false);
		f4.setDepartureCity("Las Vegas");
		f4.setDestinationCity("Washington");
		f4.setDepartureDate(LocalDate.of(2021, 6, 10));
		f4.setType(FlightType.International);
		f4.setDurationMin(400);
		f4.setAircraft(new Aircraft("A321", 150));

		FlightInformation f5 = new FlightInformation();
		f5.setDelayed(false);
		f5.setDepartureCity("Bucharest");
		f5.setDestinationCity("Rome");
		f5.setDepartureDate(LocalDate.of(2021, 6, 13));
		f5.setType(FlightType.International);
		f5.setDurationMin(110);
		f5.setAircraft(new Aircraft("A321", 200));

		this.mongoTemplate.insertAll(Arrays.asList(f1, f2, f3, f4, f5));
	}
	
	private void empty() {
		this.mongoTemplate.dropCollection(FlightInformation.class);
	}

}
