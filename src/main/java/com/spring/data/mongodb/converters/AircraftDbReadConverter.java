package com.spring.data.mongodb.converters;

import com.spring.data.mongodb.domain.Aircraft;
import org.springframework.core.convert.converter.Converter;

public class AircraftDbReadConverter implements Converter<String, Aircraft> {

    @Override
    public Aircraft convert(String s) {
        String[] parts = s.split("/");
        Aircraft aircraft = new Aircraft(parts[0], Integer.parseInt(parts[1]));
        return aircraft;
    }
}
