package com.spring.data.mongodb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.spring.data.mongodb.domain.FlightInformation;

@Component
@Order(2)
public class ApplicationRunner implements CommandLineRunner {

	private MongoTemplate mongoTemplate;
	
	public ApplicationRunner(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public void run(String... args) throws Exception {
		markAllFlightsToRomeAsDelayed();
		//removeFlightsWithDurationLessThanTwoHours();
	}

	void markAllFlightsToRomeAsDelayed(){
		Query romeFlights = new Query()
				.addCriteria(Criteria.where("destination")
				.is("Rome"));
		Update update = new Update()
				.set("isDelayed", true);
		this.mongoTemplate.updateMulti(romeFlights, update, FlightInformation.class);
	}

	void removeFlightsWithDurationLessThanTwoHours(){
		Query withMinDuration = Query.query(Criteria.where("durationMin").lt(60 * 2));
		this.mongoTemplate.remove(withMinDuration, FlightInformation.class);
	}

}
