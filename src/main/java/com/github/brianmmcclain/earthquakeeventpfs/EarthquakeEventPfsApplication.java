package com.github.brianmmcclain.earthquakeeventpfs;

import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EarthquakeEventPfsApplication {

	@Bean
    public Function<String, String> getevents() {
        return s -> {
			return s.toUpperCase();
		};
    }
	public static void main(String[] args) {
		SpringApplication.run(EarthquakeEventPfsApplication.class, args);
	}
}
