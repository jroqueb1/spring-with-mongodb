package com.spring.data.mongodb.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("aircrafts")
public class Aircraft {

	@Id
	private String id;
	private String model;
	private int nbSeats;
	@DBRef(lazy = true)
	private Engine engine;
	
	public Aircraft() {}
	
	public Aircraft(String model, int nbSeats) {
		super();
		this.model = model;
		this.nbSeats = nbSeats;
	}

	public Aircraft(String model, int nbSeats, Engine engine) {
		super();
		this.model = model;
		this.nbSeats = nbSeats;
		this.engine = engine;
	}	
	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getNbSeats() {
		return nbSeats;
	}

	public void setNbSeats(int nbSeats) {
		this.nbSeats = nbSeats;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
		
}
