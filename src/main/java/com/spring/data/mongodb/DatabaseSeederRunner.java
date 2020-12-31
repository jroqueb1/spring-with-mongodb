package com.spring.data.mongodb;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.spring.data.mongodb.domain.Aircraft;
import com.spring.data.mongodb.domain.Airport;
import com.spring.data.mongodb.domain.Engine;
import com.spring.data.mongodb.domain.FlightInformation;
import com.spring.data.mongodb.domain.FlightType;
import com.spring.data.mongodb.repository.AirportRepository;
import com.spring.data.mongodb.repository.EngineRepository;
import com.spring.data.mongodb.repository.FlightInformationRepository;
import com.spring.data.mongodb.utils.FlightPrinter;

@Component
@Order(1)
public class DatabaseSeederRunner implements CommandLineRunner {
	
//	private MongoTemplate mongoTemplate;
//	
//	public DatabaseSeederRunner(MongoTemplate mongoTemplate) {
//		this.mongoTemplate = mongoTemplate;
//	}

	private final FlightInformationRepository flightRepository;
	private final EngineRepository engineRepository;
	private final AirportRepository airportRepository;
	
	public DatabaseSeederRunner(AirportRepository airportRepository, FlightInformationRepository flightRepository, EngineRepository engineRepository) {
		this.airportRepository = airportRepository;
		this.flightRepository = flightRepository;
		this.engineRepository = engineRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		empty();
		seed();
	}
	
	private void seed() {
		Airport newYork = new Airport("eo3994-2dkjd-d3e2", "John F Kenedy", "New York", Integer.MAX_VALUE);
		Airport rome = new Airport("duie-2dkjd-3ueis", "Leonardo da Vinci", "Rome", 14933388);
		Airport paris = new Airport("e4iroo-49eed-4e23iie", "Charles de Gaulle", "Paris", 4328883);
		Airport copenhagen = new Airport("43oeii-294ieo-3496ee", "Copenhagen Airport", "copenhagen", 49388);
		
//		this.airportRepository.saveAll(Arrays.asList(newYork, rome, paris, copenhagen));
		
		Engine rollroyce600 = new Engine();
		rollroyce600.setType("rollroyce600");
		rollroyce600.setMaxPower(20000);

		Engine boing5000 = new Engine();
		boing5000.setType("boing5000");
		boing5000.setMaxPower(24000);
		
		//Saving all engines...
//		this.engineRepository.insert(Arrays.asList(rollroyce600, boing5000));
		
		
		FlightInformation f1 = new FlightInformation();
		f1.setDelayed(false);
		f1.setDepartureCity("Rome");
		f1.setDepartureAirport(rome);		
		f1.setDestinationCity("Paris");
		f1.setDestinationAirport(paris);
		f1.setDepartureDate(LocalDate.of(2020, 12, 14));
		f1.setType(FlightType.International);
		f1.setDurationMin(80);
		f1.setAircraft(new Aircraft("737", 180, rollroyce600));

		FlightInformation f2 = new FlightInformation();
		f2.setDelayed(false);
		f2.setDepartureCity("New York");
		f2.setDepartureAirport(newYork);
		f2.setDestinationCity("Copenhagen");
		f2.setDestinationAirport(copenhagen);
		f2.setDepartureDate(LocalDate.of(2020, 12, 16));
		f2.setType(FlightType.International);
		f2.setDurationMin(600);
		f2.setAircraft(new Aircraft("747", 300, boing5000));

		/*
		 * FlightInformation f3 = new FlightInformation(); f3.setDelayed(false);
		 * f3.setDepartureCity("Bruxellas"); f3.setDestinationCity("Bucharest");
		 * f3.setDepartureDate(LocalDate.of(2021, 1, 12));
		 * f3.setType(FlightType.International); f3.setDurationMin(150);
		 * f3.setAircraft(new Aircraft("A320", 160, boing5000));
		 * 
		 * FlightInformation f4 = new FlightInformation(); f4.setDelayed(false);
		 * f4.setDepartureCity("Las Vegas"); f4.setDestinationCity("Washington");
		 * f4.setDepartureDate(LocalDate.of(2021, 6, 10));
		 * f4.setType(FlightType.International); f4.setDurationMin(400);
		 * f4.setAircraft(new Aircraft("A321", 150, rollroyce600));
		 * 
		 * FlightInformation f5 = new FlightInformation(); f5.setDelayed(false);
		 * f5.setDepartureCity("Bucharest"); f5.setDestinationCity("Rome");
		 * f5.setDepartureDate(LocalDate.of(2021, 6, 13));
		 * f5.setType(FlightType.International); f5.setDurationMin(110);
		 * f5.setAircraft(new Aircraft("A321", 200, boing5000));
		 */
//		this.mongoTemplate.insertAll(Arrays.asList(f1, f2, f3, f4, f5));
		this.flightRepository.insert(Arrays.asList(f1, f2 /*, f3, f4, f5 */));
		
		long count = this.flightRepository.count();
		System.out.println("Total flights in database: " + count);
		
		List<FlightInformation> flightsInDb = this.flightRepository.findAll(Sort.by("departureCity").ascending());
		FlightPrinter.print(flightsInDb);
//		for(FlightInformation f: flightsInDb) {			
//			System.out.println(f.getAircraft().getEngine().getType());
//			System.out.println("No. Vuelo: " + f.getId());
//		}
	}
	
	private void empty() {
//		this.mongoTemplate.dropCollection(FlightInformation.class);
		this.airportRepository.deleteAll();
		this.engineRepository.deleteAll();
		this.flightRepository.deleteAll();
	}

}
